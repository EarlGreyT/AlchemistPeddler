package de.materna.alchemistpeddler.gameuicommunication;

import java.util.ArrayList;

public interface PlayerEventGenerator extends GameStateListener {
  ArrayList<PlayerEventListener> subscribers = new ArrayList<>();
  default void addSubscriber(PlayerEventListener listener){
    subscribers.add(listener);
  }
  default void inform(PlayerEventListener subscriber, PlayerEvent event){
    subscriber.getUpdate(event);
  }


}
