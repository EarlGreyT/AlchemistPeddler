package de.materna.alchemistpeddler.gameuicommunication;

/**
 * informs the Game about actions the UI performs for the player
 */
public interface PlayerEventGenerator {
  default void inform(PlayerEventListener subscriber, PlayerEvent event){
    subscriber.receivePlayerEvent(event);
  }


}
