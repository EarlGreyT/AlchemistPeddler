package de.materna.alchemistpeddler.gamelogic;


import de.materna.alchemistpeddler.gamelogic.GameEvent.EventName;
import de.materna.alchemistpeddler.gameuicommunication.Potion;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class GameEventFactory {

  static GameEvent buildGameEvent(EventName eventName) {
    switch (eventName) {
      case ROB -> {
        return (GameEvent<Player>) (Player player) -> {
          double stealPercent = ThreadLocalRandom.current().nextDouble(0, 0.26);
          int targetCurrency = (int) (player.getCurrency() * (1.0 - stealPercent));
          player.setCurrency(targetCurrency > 0 ?  targetCurrency : 0);
          return EventName.ROB;
        };
      }
      case POTION -> {
        return (GameEvent<Potion>) potion -> {
          HashMap<String, City> cities = Game.cities;
          double consumptionDeltaPercent = ThreadLocalRandom.current().nextDouble(0.3, 1.0);
          for (City city : cities.values()) {
            int consumptionDelta = (int) Math.round(
                city.getPotionConsumptions()[potion.ordinal()] * consumptionDeltaPercent);
            city.modifyPotionConsumption(potion, consumptionDelta);
          }
          return EventName.POTION;
        };
      }
      case CITY_POTION -> {
        return (GameEvent<City>) city -> {
          double LOWERBOUND = -0.75;
          double UPPERBOUND = 0.75;
          for (Potion potion : Potion.values()) {
            double productionDeltaPercent = ThreadLocalRandom.current()
                .nextDouble(LOWERBOUND, UPPERBOUND);
            double consumptionDeltaPercent = ThreadLocalRandom.current()
                .nextDouble(LOWERBOUND, UPPERBOUND);
            int consumptionDelta = (int) (city.getPotionConsumptions()[potion.ordinal()]
                * consumptionDeltaPercent);
            int productionDelta = (int) (city.getPotionProductions()[potion.ordinal()]
                * productionDeltaPercent);
            Integer modify = ThreadLocalRandom.current().nextInt(0, 6);
            switch (modify) {
              case 5 -> {
                city.modifyPotionProduction(potion, productionDelta);
                city.modifyPotionConsumption(potion, consumptionDelta);
              }
              case 3, 4 -> {
                city.modifyPotionProduction(potion, productionDelta);
              }
              case 1, 2 -> {
                city.modifyPotionConsumption(potion, consumptionDelta);
              }
              default -> {
              }
            }

          }
          return EventName.CITY_POTION;
        };
      }

      default -> {
        return (GameEvent<Void>) (unused) -> EventName.NULL;
      }
    }
  }
}
