package de.materna.alchemistpeddler.gamelogic;

public class NullEvent extends GameEvent {

  @Override
  GameEvent process() {
    msg = "";
    return this;
  }
}
