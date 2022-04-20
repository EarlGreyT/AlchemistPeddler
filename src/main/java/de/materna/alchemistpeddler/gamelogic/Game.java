package de.materna.alchemistpeddler.gamelogic;

import de.materna.alchemistpeddler.gameuicommunication.PlayerEvent;
import de.materna.alchemistpeddler.gameuicommunication.PlayerEventGenerator;
import de.materna.alchemistpeddler.gameuicommunication.PlayerEventListener;
import de.materna.alchemistpeddler.gameuicommunication.PlayerRecord;
import de.materna.alchemistpeddler.gameuicommunication.PlayerRecordListener;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class that will handle communication between all GameObjects
 */
public class Game implements PlayerEventListener {

  public static final int MAX_DAYS = 30;
  private final Player player = new Player();
  private final int sumToWin = player.getCurrency()*10;
  private int gameDay = 0;
  private final PlayerRecordListener playerRecordListener;
  private final HashMap<String, City> cities = new HashMap<>();

  public Game(PlayerEventGenerator generator) {
    playerRecordListener = generator;
    subscribeTo(generator);
    for (CITY_NAMES value : CITY_NAMES.values()) {
      cities.put(value.cityName, new City(value.cityName));
    }
    player.setLocation(cities.get(
        CITY_NAMES.values()[
            ThreadLocalRandom.current().nextInt(0, cities.size())].cityName)
    );
  }

  @Override
  public void getUpdate(PlayerEvent event) {
    switch (event.action()) {
      case BUY -> {
        player.buy(Potion.values()[Integer.parseInt(event.msg())], event.amount());
        playerRecordListener.getPlayerRecord(new PlayerRecord(player));
      }
      case SELL -> {
        player.sell(Potion.values()[Integer.parseInt(event.msg())], event.amount());
        playerRecordListener.getPlayerRecord(new PlayerRecord(player));
      }
      case TRAVEL -> {
        nextDay();
        player.travel(cities.get(event.msg()));
        player.setCurrency(player.getCurrency() - event.amount());
        playerRecordListener.getPlayerRecord(new PlayerRecord(player));
      }
    }
  }
  private void nextDay(){
    gameDay++;
    cities.forEach((name, city) -> city.update());
    checkWinCondition();
  }

  private void checkWinCondition() {
    player.setWon(gameDay> MAX_DAYS && player.getCurrency() >= sumToWin);
  }

  public int getGameDay() {
    return gameDay;
  }

  public void setGameDay(int gameDay) {
    this.gameDay = gameDay;
  }

  public Player getPlayer() {
    return player;
  }

  public HashMap<String, City> getCities() {
    return cities;
  }

  public int getSumToWin() {
    return sumToWin;
  }
}
