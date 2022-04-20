package de.materna.alchemistpeddler.gamelogic;

import java.util.HashMap;

public class GameEventFactory {
  static GameEvent buildGameEvent(EventName eventName, int target){
    switch (eventName){
      case ROB ->{
        return new RobEvent(Game.player);
      }
      case POTION -> {
        return target < Potion.values().length ?
        new PotionEvent(Potion.values()[target])
        : new NullEvent();
      }
      case CITY_POTION -> {
        return target < Game.cities.size() ?
            new CityPotionEvent((City) Game.cities.values().toArray()[target])
            : new NullEvent();
      }
      default -> {
        return new NullEvent();
      }
    }
  }
}
