package de.materna.alchemistpeddler.tui;

import de.materna.alchemistpeddler.gamelogic.GameEvent;
import de.materna.alchemistpeddler.gamelogic.GameState;
import de.materna.alchemistpeddler.gameuicommunication.GameStateListener;
import de.materna.alchemistpeddler.gameuicommunication.PlayerEvent;
import de.materna.alchemistpeddler.gameuicommunication.PlayerEventGenerator;
import de.materna.alchemistpeddler.gameuicommunication.PlayerEventListener;
import de.materna.alchemistpeddler.tui.gamePanels.ShopPanel;
import java.io.IOException;
import java.util.HashMap;

public class GameController implements PlayerEventGenerator, GameStateListener {
  private GameState lastGameState;
  @Override
  public GameState getGameState(GameState gameState) {
    this.lastGameState = gameState;
    ShopPanel.ShopFactory.updateShopLabels();
    if (TUIApp.gameWindow != null){
      TUIApp.gameWindow.getGamePanel().getPlayerPanel().getPlayerDataPanel().update(gameState.playerRecord());
    }
    return GameStateListener.super.getGameState(gameState);
  }

  public GameState getLastGameState() {
    return lastGameState;
  }

  @Override
  public HashMap<GameEvent, String> getGameEventNotification(GameEvent event,String  msg) {
    System.out.println(msg);
    return GameStateListener.super.getGameEventNotification(event);
  }
}
