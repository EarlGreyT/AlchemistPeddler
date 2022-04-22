package de.materna.alchemistpeddler.tui;

import de.materna.alchemistpeddler.gamelogic.GameEvent;
import de.materna.alchemistpeddler.gamelogic.GameState;
import de.materna.alchemistpeddler.gameuicommunication.GameStateListener;
import de.materna.alchemistpeddler.gameuicommunication.PlayerEventGenerator;
import de.materna.alchemistpeddler.tui.gamepanels.ShopFactory;
import java.util.HashMap;

public class GameController implements PlayerEventGenerator, GameStateListener {
  private GameState lastGameState;
  @Override
  public GameState getGameState(GameState gameState) {
    this.lastGameState = gameState;
    ShopFactory.updateShopLabels();
    if (TUIApp.gameWindow != null){
      TUIApp.gameWindow.getGamePanel().getPlayerPanel().getPlayerDataPanel().update(gameState.playerRecord());
    }
    return GameStateListener.super.getGameState(gameState);
  }

  public GameState getLastGameState() {
    return lastGameState;
  }


}
