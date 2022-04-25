package de.materna.alchemistpeddler.tui;

import de.materna.alchemistpeddler.gamelogic.Game;
import de.materna.alchemistpeddler.gamelogic.GameState;
import de.materna.alchemistpeddler.gameuicommunication.GameStateListener;
import de.materna.alchemistpeddler.gameuicommunication.PlayerAction;
import de.materna.alchemistpeddler.gameuicommunication.PlayerEventGenerator;
import de.materna.alchemistpeddler.tui.gamepanels.shops.AbstractShopFactory;
import de.materna.alchemistpeddler.tui.gamepanels.shops.PotionShopFactory;
import de.materna.alchemistpeddler.tui.gamepanels.shops.TravelShopFactory;
import java.util.EnumMap;

public class GameController implements PlayerEventGenerator, GameStateListener {
  public final EnumMap<PlayerAction, AbstractShopFactory> shopFactories = new EnumMap<>(PlayerAction.class);
  private GameState lastGameState;
  private Game game = new Game(this);

  public GameController() {
    shopFactories.put(PlayerAction.TRAVEL, new TravelShopFactory());
    shopFactories.put(PlayerAction.BUY, new PotionShopFactory());
    shopFactories.put(PlayerAction.SELL, shopFactories.get(PlayerAction.BUY));
  }

  @Override
  public GameState getGameState(GameState gameState) {
    this.lastGameState = gameState;
    for (AbstractShopFactory shopFactory : shopFactories.values()) {
      shopFactory.updateShopLabels();
    }
    if (TUIApp.gameWindow != null){
      TUIApp.gameWindow.getGamePanel().getPlayerPanel().getPlayerDataPanel().update(lastGameState.playerRecord());
    }
    return gameState;
  }

  public GameState getLastGameState() {
    return lastGameState;
  }


}
