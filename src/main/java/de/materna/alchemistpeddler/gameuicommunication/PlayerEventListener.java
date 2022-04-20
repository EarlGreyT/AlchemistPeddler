package de.materna.alchemistpeddler.gameuicommunication;

public interface PlayerEventListener {
  default void subscribeTo(PlayerEventGenerator playerEventGenerator){
    playerEventGenerator.addSubscriber(this);
  }
  void getUpdate(PlayerEvent event);

}
