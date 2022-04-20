package de.materna.alchemistpeddler.gamelogic;

import java.util.ArrayList;

public interface PlayerEventListener {
  default void subscribeTo(PlayerEventGenerator playerEventGenerator){
    playerEventGenerator.addSubscriber(this);
  }
  void getUpdate(PlayerEvent event);

}
