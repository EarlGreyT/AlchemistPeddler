package de.materna.alchemistpeddler.gamelogic;

public interface PlayerEventListener {
  default void subscribeTo(PlayerEventGenerator playerEventGenerator){
    playerEventGenerator.addSubscriber(this);
  }
  void getUpdate(PlayerEvent event);
}
