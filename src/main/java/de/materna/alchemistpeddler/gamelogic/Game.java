package de.materna.alchemistpeddler.gamelogic;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class that will handle communication between all GameObjects
 */
public class Game implements PlayerEventListener {
  private final Player player = new Player();
  private final int sumToWin = player.getCurrency()*10;
  private int gameDay = 0;

  private final HashMap<String, City> cities = new HashMap<>();

  public Game(PlayerEventGenerator generator) {
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
      case BUY -> player.buy(Potion.values()[Integer.parseInt(event.msg())], event.amount());
      case SELL -> player.sell(Potion.values()[Integer.parseInt(event.msg())], event.amount());
      case TRAVEL -> {
        gameDay++;
        cities.forEach((name, city) -> city.update());
        player.travel(cities.get(event.msg()));
        player.setCurrency(player.getCurrency() - event.amount());
      }
    }
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
