package de.materna.alchemistpeddler.gamelogic;

import java.util.concurrent.ThreadLocalRandom;

public class CityPotionEvent extends GameEvent {
  private final City target;
  public static final double UPPERBOUND = 0.75;
  public static final double LOWERBOUND = -0.75;


  public CityPotionEvent(City target) {
    this.target = target;
  }

  @Override
  public GameEvent process() {
    for (Potion potion : Potion.values()) {
      double productionDeltaPercent = ThreadLocalRandom.current()
          .nextDouble(LOWERBOUND, UPPERBOUND);
      double consumptionDeltaPercent = ThreadLocalRandom.current()
          .nextDouble(LOWERBOUND, UPPERBOUND);
      int consumptionDelta = (int) (target.getPotionConsumptions()[potion.ordinal()]
          * consumptionDeltaPercent);
      int productionDelta = (int) (target.getPotionProductions()[potion.ordinal()]
          * productionDeltaPercent);
      Integer modify = ThreadLocalRandom.current().nextInt(0, 6);
      switch (modify) {
        case 5 -> {
          target.modifyPotionProduction(potion, productionDelta);
          target.modifyPotionConsumption(potion, consumptionDelta);
        }
        case 3, 4 -> {
          target.modifyPotionProduction(potion, productionDelta);
        }
        case 1, 2 -> {
          target.modifyPotionConsumption(potion, consumptionDelta);
        }
        default -> {
        }
      }

    }
    msg = target.getName();
    return this;
  }
}
