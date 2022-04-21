package de.materna.alchemistpeddler.gameuicommunication;

import de.materna.alchemistpeddler.gamelogic.GameEvent;
import de.materna.alchemistpeddler.gamelogic.GameState;
import java.util.AbstractMap;
import java.util.HashMap;

public interface GameStateListener {
  default GameState getGameState(GameState gameState){
    return gameState;
  }
  default HashMap<GameEvent,String> getGameEventNotification(GameEvent event){
    return getGameEventNotification(event, "");
  }

  default HashMap<GameEvent, String>  getGameEventNotification(GameEvent event, String msg){
    HashMap<GameEvent, String> eventHashMap = new HashMap<>();
    eventHashMap.put(event,msg);
    return eventHashMap;
  }
}
