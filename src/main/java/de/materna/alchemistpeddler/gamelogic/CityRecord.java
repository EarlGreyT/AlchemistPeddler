package de.materna.alchemistpeddler.gamelogic;

import java.util.List;

public record CityRecord(List<Integer> potionProductions, List<Integer> potionConsumptions, List<Integer> potionAmounts) {
  public CityRecord(City city){
    this(
        City.getArrAsList(city.getPotionProductions()),
        City.getArrAsList(city.getPotionConsumptions()),
        City.getArrAsList(city.getPotionAmounts())
    );
  }
}
