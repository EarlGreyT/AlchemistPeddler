package de.materna.alchemistpeddler.gamelogic;

import java.util.ArrayList;

public interface PlayerEventGenerator {
  ArrayList<PlayerEventListener> subscribers = new ArrayList<>();
  default void addSubscriber(PlayerEventListener listener){
    subscribers.add(listener);
  }
  default void informSubscribers(PlayerEvent event){
    for (PlayerEventListener subscriber : subscribers) {
      inform(subscriber, event);
    }
  }

  default void inform(PlayerEventListener subscriber, PlayerEvent event){
    subscriber.getUpdate(event);
  }


}
