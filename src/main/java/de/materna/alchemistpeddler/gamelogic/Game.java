package de.materna.alchemistpeddler.gamelogic;

import de.materna.alchemistpeddler.gamelogic.GameEvent.EventName;
import de.materna.alchemistpeddler.gameuicommunication.CITY_NAME;
import de.materna.alchemistpeddler.gameuicommunication.GameStateListener;
import de.materna.alchemistpeddler.gameuicommunication.PlayerEvent;
import de.materna.alchemistpeddler.gameuicommunication.PlayerEventGenerator;
import de.materna.alchemistpeddler.gameuicommunication.PlayerEventListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class that will handle Game rules and communication with the UI
 */
public class Game implements PlayerEventListener {
  static final int MAX_DAYS = 30;
  private static final int SUM_TO_WIN = 5_000;
  private static final int MIN_TRAVEL_PRICE = 30;
  private static final int MAX_TRAVEL_PRICE = 301;
  private int gameDay;
  private Player player;
  private GameStateListener gameStateListener;
  static final HashMap<String, City> cities = new HashMap<>();
  private CityGraph cityGraph = new CityGraph();
  public Game(){
    cityGraph.buildGraph(MIN_TRAVEL_PRICE, MAX_TRAVEL_PRICE);
    player = new Player(cityGraph);
    gameDay = 0;
    for (CITY_NAME value : CITY_NAME.values()) {
      cities.put(value.cityName, new City(value));
    }
    player.setLocation(cities.get(
        CITY_NAME.values()[
            ThreadLocalRandom.current().nextInt(0, cities.size())].cityName)
    );
  }
  public Game(PlayerEventGenerator generator) {
    this();
    gameStateListener = (GameStateListener) generator;
    updateGameState();
  }

  /**
   * Reacts to events received from the gui and triggers an update of the gameState.
   *
   * This allows the player to be controlled from the gui.
   * @param event
   */
  @Override
  public void getUpdate(PlayerEvent event) {
    switch (event.action()) {
      case BUY -> {
        player.buy(Potion.values()[event.what()], event.amount());
      }
      case SELL -> {
        player.sell(Potion.values()[event.what()], event.amount());
      }
      case TRAVEL -> {
        player.travel(cities.get(CITY_NAME.values()[event.what()].cityName));
        nextDay();
      }
      case TAKELOAN -> {
        player.takeLoan(event.amount());
      }
      case PAYLOAN -> {
        player.payLoan(event.amount());
      }
    }
    updateGameState();
  }

  /**
   * sends a random GameEvent to the GameController
   *
   * <p> The closer gameDay gets to MAX_DAYS the higher the chance for a non Null-Event</p>
   */
  private void fireEvent() {
    int randomEventIndex = ThreadLocalRandom.current()
        .nextInt(EventName.values().length + (MAX_DAYS / gameDay));
    if (randomEventIndex >= EventName.values().length) {
      randomEventIndex = EventName.NULL.ordinal();
    }
    String msg="";
    EventName eventName = EventName.values()[randomEventIndex];
    GameEvent event = GameEventFactory.buildGameEvent(eventName);
    switch (eventName) {
      case CITY_POTION -> {
        City randomCity = cities.get(CITY_NAME.values()[
            ThreadLocalRandom.current().nextInt(CITY_NAME.values().length)
            ].cityName);
        ((GameEvent<City>) event).process(randomCity);
        msg = "The Market in " + randomCity.getName()  + " acts crazy.\nExperts are baffled.";
      }
      case POTION -> {
        Potion randomPotion = Potion.values()[ThreadLocalRandom.current().nextInt(Potion.values().length)];
       ((GameEvent<Potion>) event).process(randomPotion);
       msg = randomPotion.name();
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
    gameStateListener.getGameEventNotification(eventName,msg);
  }

  private void updateGameState() {
    ArrayList<CityRecord> cityRecords = new ArrayList<>();
    cities.forEach((name, city) -> cityRecords.add(new CityRecord(city)));
    PlayerRecord playerRecord = new PlayerRecord(player);
    gameStateListener.getGameState(
        new GameState(cityRecords, playerRecord, gameDay, MAX_DAYS, cityGraph));
  }

  /**
   * increases the current day by one, causes every city to update,
   * sends a GameEvent to the GameController and sets the players win/loss status according tu the current game state
   */
  void nextDay() {
    gameDay++;
    cities.forEach((name, city) -> city.update());
    fireEvent();
    checkWinCondition();
    updateGameState();
  }

  private void checkWinCondition() {
    player.setLost((gameDay > (MAX_DAYS / 2) && player.getDebt() > 0) || ((gameDay > MAX_DAYS) &&( player.getCurrency()< SUM_TO_WIN)));
    player.setWon((gameDay > MAX_DAYS) && (player.getCurrency() >= SUM_TO_WIN) && (player.getDebt()<=0));
  }

  public Player getPlayer() {
    return player;
  }

  public HashMap<String, City> getCities() {
    return cities;
  }

  public void setGameStateListener(
      GameStateListener gameStateListener) {
    this.gameStateListener = gameStateListener;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }
}
