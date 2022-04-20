package de.materna.alchemistpeddler.gamelogic;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class PotionEvent extends GameEvent{
  private final Potion target;
  public static final double LOWERBOUND = 0.3;
  public static final double UPPERBOUND = 1.0;
  protected PotionEvent(
      Potion target) {
    this.target = target;
  }

  @Override
  GameEvent process() {
    HashMap<String, City> cities = Game.cities;
    double consumptionDeltaPercent = ThreadLocalRandom.current()
        .nextDouble(LOWERBOUND, UPPERBOUND);
    for (City city : cities.values()) {
      int consumptionDelta = (int) Math.round(city.getPotionConsumptions()[target.ordinal()]
          * consumptionDeltaPercent);
      city.modifyPotionConsumption(target,consumptionDelta);
    }
    msg = consumptionDeltaPercent +","+target.toString();
    return this;
  }
}
