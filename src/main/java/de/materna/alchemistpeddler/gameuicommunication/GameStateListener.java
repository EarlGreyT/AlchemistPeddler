package de.materna.alchemistpeddler.gameuicommunication;

import de.materna.alchemistpeddler.gamelogic.GameState;

public interface GameStateListener {
  default GameState getGameState(GameState gameState){
    return gameState;
  }
}
