package de.materna.alchemistpeddler.gamelogic;

public abstract class GameEvent {


  public String getMsg() {
    return msg;
  }

  protected String msg;

  abstract GameEvent process();
}
