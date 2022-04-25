package de.materna.alchemistpeddler.gameuicommunication;

import de.materna.alchemistpeddler.gamelogic.GameEvent;
import de.materna.alchemistpeddler.gamelogic.GameEvent.EventName;
import de.materna.alchemistpeddler.gamelogic.GameState;
import java.util.HashMap;

public interface GameStateListener {
  default GameState getGameState(GameState gameState){
    return gameState;
  }
  default HashMap<EventName,String> getGameEventNotification(EventName event){
    return getGameEventNotification(event, "");
  }

  default HashMap<EventName, String>  getGameEventNotification(EventName event, String msg){
    HashMap<EventName, String> eventHashMap = new HashMap<>();
    eventHashMap.put(event,msg);
    return eventHashMap;
  }
}
