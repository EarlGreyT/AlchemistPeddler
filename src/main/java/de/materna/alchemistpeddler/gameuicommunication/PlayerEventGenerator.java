package de.materna.alchemistpeddler.gameuicommunication;

import java.util.ArrayList;

/**
 * informs the Game about actions the UI performs for the player
 */
public interface PlayerEventGenerator {
  ArrayList<PlayerEventListener> subscribers = new ArrayList<>();
  default void addSubscriber(PlayerEventListener listener){
    subscribers.add(listener);
  }
  default void inform(PlayerEventListener subscriber, PlayerEvent event){
    subscriber.getUpdate(event);
  }


}
