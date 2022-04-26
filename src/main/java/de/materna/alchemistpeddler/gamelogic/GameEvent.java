package de.materna.alchemistpeddler.gamelogic;

@FunctionalInterface
public interface GameEvent<T> {
  enum EventName {
    CITY_POTION,
    POTION,
    ROB,
    NULL
  }

  EventName process(T target);
}
