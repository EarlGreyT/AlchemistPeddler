package de.materna.alchemistpeddler.gamelogic;

import java.util.List;

/**
 * Representation of the State of a City.
 * @param potionProductions
 * @param potionConsumptions
 * @param potionAmounts
 * @param prices
 * @param name
 * @see City
 * @see GameState
 */
public record CityRecord(List<Integer> potionProductions, List<Integer> potionConsumptions, List<Integer> potionAmounts, List<Integer> prices,String name) {
  public CityRecord(City city){
    this(
        City.getArrAsList(city.getPotionProductions()),
        City.getArrAsList(city.getPotionConsumptions()),
        City.getArrAsList(city.getPotionAmounts()),
        city.getPriceList(),
        city.getName()
    );
  }
}
