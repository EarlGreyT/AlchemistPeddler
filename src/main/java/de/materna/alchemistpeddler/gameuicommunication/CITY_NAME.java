package de.materna.alchemistpeddler.gameuicommunication;

import java.util.Collection;
import java.util.List;

public enum CITY_NAME implements Buyable{
  GOLDSHIRE("Goldshire"),
  HOBBINGEN("Hobbingen"),
  TRISTRAM("Tristram"),
  CAMELOT("Camelot"),
  ATLANTIS("Atlantis");

  @Override
  public Collection getValues() {
    return List.of(values());
  }

  public final String cityName;

  CITY_NAME(String cityName) {
    this.cityName = cityName;
  }
}
