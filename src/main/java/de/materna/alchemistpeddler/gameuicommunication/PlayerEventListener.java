package de.materna.alchemistpeddler.gameuicommunication;


/**
 * The PlayerEventListener can react to PlayerEvents send by the UI
 */
public interface PlayerEventListener {
  default void subscribeTo(PlayerEventGenerator playerEventGenerator){
    playerEventGenerator.addSubscriber(this);
  }
  void getUpdate(PlayerEvent event);

}
