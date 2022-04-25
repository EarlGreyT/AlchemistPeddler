package de.materna.alchemistpeddler.gameuicommunication;

import java.util.ArrayList;

/**
 * informs the Game about actions the UI performs for the player
 */
public interface PlayerEventGenerator {
  default void inform(PlayerEventListener subscriber, PlayerEvent event){
    subscriber.getUpdate(event);
  }


}
