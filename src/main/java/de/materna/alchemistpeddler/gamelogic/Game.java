package de.materna.alchemistpeddler.gamelogic;

import de.materna.alchemistpeddler.gamelogic.GameEvent.EventName;
import de.materna.alchemistpeddler.gameuicommunication.CITY_NAMES;
import de.materna.alchemistpeddler.gameuicommunication.PlayerEvent;
import de.materna.alchemistpeddler.gameuicommunication.PlayerEventGenerator;
import de.materna.alchemistpeddler.gameuicommunication.PlayerEventListener;
import de.materna.alchemistpeddler.gameuicommunication.GameStateListener;
import de.materna.alchemistpeddler.gameuicommunication.Potion;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class that will handle Game rules and communication with the UI
 */
public class Game implements PlayerEventListener {

  public static final int MAX_DAYS = 30;
  static final Player player = new Player();
  private static final int sumToWin = player.getCurrency() * 10;
  private int gameDay = 0;
  private final GameStateListener gameStateListener;
  static final HashMap<String, City> cities = new HashMap<>();

  public Game(PlayerEventGenerator generator) {
    gameStateListener = (GameStateListener) generator;
    subscribeTo(generator);
    for (CITY_NAMES value : CITY_NAMES.values()) {
      cities.put(value.cityName, new City(value));
    }
    player.setLocation(cities.get(
        CITY_NAMES.values()[
            ThreadLocalRandom.current().nextInt(0, cities.size())].cityName)
    );
    updateGameState();
  }

  @Override
  public void getUpdate(PlayerEvent event) {
    switch (event.action()) {
      case BUY -> {
        System.out.println(Potion.values()[event.what()] +" " +event.amount());
        System.out.println("Bought: "+player.buy(Potion.values()[event.what()], event.amount()));
        System.out.println(player.getLocation().getName()+ " has left: "+ player.getLocation().getPotionAmounts()[event.what()]);
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

  private void fireEvent() {
    int randomEventIndex = ThreadLocalRandom.current()
        .nextInt(EventName.values().length + (MAX_DAYS - gameDay));
    if (randomEventIndex > EventName.values().length - 1) {
      randomEventIndex = EventName.NULL.ordinal();
    }
    String msg="";
    EventName eventName = EventName.values()[randomEventIndex];
    GameEvent event = GameEventFactory.buildGameEvent(eventName);
    switch (eventName) {
      case CITY_POTION -> {
        City randomCity = cities.get(CITY_NAMES.values()[
            ThreadLocalRandom.current().nextInt(CITY_NAMES.values().length)
            ].cityName);
        ((GameEvent<City>) event).process(randomCity);
        msg = "Consumption: " + Arrays.toString(randomCity.getPotionConsumptions()) + ", Production: "
                + Arrays.toString(
                randomCity.getPotionProductions());
      }
      case POTION -> {
        Potion randomPotion = Potion.values()[ThreadLocalRandom.current().nextInt(Potion.values().length)];
       ((GameEvent<Potion>) event).process(randomPotion);
      }
      case ROB -> {
        GameEvent<Player> playerGameEvent = (GameEvent<Player>) event;
        playerGameEvent.process(player);
        msg = String.valueOf(player.getCurrency());

      }
      default -> {
        ((GameEvent<Void>) event).process(null);
      }
    }
    gameStateListener.getGameEventNotification(event,msg);
  }

  private void updateGameState() {
    ArrayList<CityRecord> cityRecords = new ArrayList<>();
    cities.forEach((name, city) -> cityRecords.add(new CityRecord(city)));
    PlayerRecord playerRecord = new PlayerRecord(player);
    gameStateListener.getGameState(
        new GameState(cityRecords, playerRecord, gameDay));
  }

  private void nextDay() {
    gameDay++;
    cities.forEach((name, city) -> city.update());
    fireEvent();
    checkWinCondition();
  }

  private void checkWinCondition() {
    player.setWon(gameDay > MAX_DAYS && player.getCurrency() >= sumToWin && player.getDebt()<=0);
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
