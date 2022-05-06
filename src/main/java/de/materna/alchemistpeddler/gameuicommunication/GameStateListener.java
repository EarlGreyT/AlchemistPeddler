package de.materna.alchemistpeddler.gameuicommunication;

import de.materna.alchemistpeddler.gamelogic.GameEvent.EventName;
import de.materna.alchemistpeddler.gamelogic.GameState;
import java.util.HashMap;

/**
 * Handles updates from the Game.
 * <p> This is used to update the UI accordingly</p>
 */
public interface GameStateListener {
  default GameState onGameStateChange(GameState gameState){
    return gameState;
  }
  default HashMap<EventName,String> onGameEvent(EventName event){
    return onGameEvent(event, "");
  }

  default HashMap<EventName, String> onGameEvent(EventName event, String msg){
    HashMap<EventName, String> eventHashMap = new HashMap<>();
    eventHashMap.put(event,msg);
    return eventHashMap;
  }
}
