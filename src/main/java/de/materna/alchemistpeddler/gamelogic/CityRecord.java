package de.materna.alchemistpeddler.gamelogic;

import de.materna.alchemistpeddler.gamelogic.City;

public record CityRecord(int[] potionProductions, int[] potionConsumptions, int[] potionAmounts) {
  public CityRecord(City city){
    this(city.getPotionProductions(), city.getPotionConsumptions(), city.getPotionAmounts());
  }
  @Override
  public boolean equals(Object obj) {
    throw new RuntimeException(new NoSuchMethodException());
  }
  @Override
  public String toString(){
    throw new RuntimeException(new NoSuchMethodException());
  }

  @Override
  public int hashCode() {
    throw new RuntimeException(new NoSuchMethodException());
  }
}
