package de.materna.alchemistpeddler.gameuicommunication;

import de.materna.alchemistpeddler.gamelogic.GameEvent;
import de.materna.alchemistpeddler.gamelogic.GameState;

public interface GameStateListener {
  default GameState getGameState(GameState gameState){
    return gameState;
  }
  default GameEvent getGameEventNotification(GameEvent event){
    return event;
  }
}
