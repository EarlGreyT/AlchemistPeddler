package de.materna.alchemistpeddler.gamelogic;

import java.util.Collection;

/**
 * Class that will handle communication between all GameObjects
 */
public class Game implements PlayerEventListener {

  @Override
  public void getUpdate(PlayerEvent event) {

  }

  private enum CITY_NAMES {
    GOLDSHIRE("Goldshire"),
    HOBBINGEN("Hobbingen"),
    TRISTRAM("Tristram"),
    CAMELOT("Camelot"),
    ATLANTIS("Atlantis");
    private final String cityName;
    CITY_NAMES(String cityName) {
      this.cityName = cityName;
    }
  }
  private City[] cities = new City[CITY_NAMES.values().length];
  public Game(){
    for (CITY_NAMES value : CITY_NAMES.values()) {
      cities[value.ordinal()] = new City(value.cityName);
    }
  }
}
