package de.materna.alchemistpeddler.gamelogic;

import de.materna.alchemistpeddler.gameuicommunication.CITY_NAMES;
import de.materna.alchemistpeddler.gameuicommunication.PlayerEvent;
import de.materna.alchemistpeddler.gameuicommunication.PlayerEventGenerator;
import de.materna.alchemistpeddler.gameuicommunication.PlayerEventListener;
import de.materna.alchemistpeddler.gameuicommunication.GameStateListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class that will handle Game rules and communication with the UI
 */
public class Game implements PlayerEventListener {
  public static final int MAX_DAYS = 30;
  static final Player player = new Player();
  private static final int sumToWin = player.getCurrency()*10;
  private int gameDay = 0;
  private final GameStateListener gameStateListener;
  static final HashMap<String, City> cities = new HashMap<>();

  public Game(PlayerEventGenerator generator) {
    gameStateListener = (GameStateListener) generator;
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
        player.buy(Potion.values()[event.what()], event.amount());
        updateGameState();
      }
      case SELL -> {
        player.sell(Potion.values()[event.what()], event.amount());
        updateGameState();
      }
      case TRAVEL -> {
        nextDay();
        player.travel(cities.get(CITY_NAMES.values()[event.what()].cityName));
        player.setCurrency(player.getCurrency() - event.amount());
        updateGameState();
      }
    }
  }

  private void fireEvent(){
    EventName eventName = EventName.values()[ThreadLocalRandom.current().nextInt(0,EventName.values().length)];
    GameEvent event = GameEventFactory.buildGameEvent(eventName,
        ThreadLocalRandom.current().nextInt(0, Math.max(City.NUMBER_OF_POTION_KINDS, MAX_DAYS - gameDay)));
    gameStateListener.getGameEventNotification(event.process());
  }
  private void updateGameState() {
    ArrayList<CityRecord> cityRecords = new ArrayList<>();
    cities.forEach((name, city) -> cityRecords.add(new CityRecord(city)));
    PlayerRecord playerRecord = new PlayerRecord(player);
    gameStateListener.getGameState(new GameState((CityRecord[]) cityRecords.toArray(), playerRecord, gameDay));
  }

  private void nextDay(){
    gameDay++;
    cities.forEach((name, city) -> city.update());
    fireEvent();
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
