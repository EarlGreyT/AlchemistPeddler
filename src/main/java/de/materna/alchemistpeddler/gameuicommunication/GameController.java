package de.materna.alchemistpeddler.gameuicommunication;


import static de.materna.alchemistpeddler.tui.TUIApp.gameWindow;
import static de.materna.alchemistpeddler.tui.TUIApp.gui;

import com.googlecode.lanterna.gui2.dialogs.ActionListDialogBuilder;
import de.materna.alchemistpeddler.gamelogic.Game;
import de.materna.alchemistpeddler.gamelogic.GameEvent.EventName;
import de.materna.alchemistpeddler.gamelogic.GameState;
import de.materna.alchemistpeddler.tui.gamepanels.EventPanel;
import de.materna.alchemistpeddler.tui.gamepanels.GamePanel;
import de.materna.alchemistpeddler.tui.gamepanels.shops.AbstractShopFactory;
import de.materna.alchemistpeddler.tui.gamepanels.shops.PotionShopFactory;
import de.materna.alchemistpeddler.tui.gamepanels.shops.TravelShopFactory;
import java.util.EnumMap;
import java.util.HashMap;

/**
 * This Listens for GameState changes and GameEvents, it also sends PlayerEvents to the Game.
 *
 * @see Game
 */
public class GameController implements PlayerEventGenerator, GameStateListener {

  public static final String ROB_TEXT = """
      Your legs feel a little shaky as you set foot into the harbour.
                  
      Another passenger forces his way to the exit and pushes you aside, but he is long gone
      when you notice that you  are missing some of your hard earned gold.
                  
      You have to make due with: 
      """;
  public static final String STRANGE_MARKET_TEXT = """
      A newspaper stand catches your attention, as most papers headlines look 
      similar to: 
      """;
  public static final String POTION_CRAZE_TEXT = """
      You already heard a lot of chatter during your travels, 
      but as you set foot into the City you realize just how many people are consuming 
      """;
  public static final String POTION_CRAZE_TEXT_PART_2 = " potions.\n This might be a great business opportunity.";
  public static final String BORING_TRAVEL_TEXT = """
      Your travels where without any troubles.
            
      You feel well rested as you enter the city
      """;
  public final EnumMap<PlayerAction, AbstractShopFactory> shopFactories = new EnumMap<>(
      PlayerAction.class);
  private GameState lastGameState;
  private Game game;

  public GameController() {
    shopFactories.put(PlayerAction.TRAVEL, new TravelShopFactory());
    shopFactories.put(PlayerAction.BUY, new PotionShopFactory());
    shopFactories.put(PlayerAction.SELL, shopFactories.get(PlayerAction.BUY));
  }

  /**
   * Receives the new GameState and triggers an update of UI elements
   *
   * @param gameState - the new GameState
   */
  @Override
  public GameState onGameStateChange(GameState gameState) {
    this.lastGameState = gameState;
    for (AbstractShopFactory shopFactory : shopFactories.values()) {
      // a new GameState can lead to new Prices, so we have to update our shops
      shopFactory.updateShopLabels();
    }
    if (gameWindow != null) {
      GamePanel gamePanel = gameWindow.getGamePanel();
      gamePanel.getPlayerPanel().getPlayerDataPanel().update(lastGameState.playerRecord());
      if (gameState.playerRecord().hasLost()) {
        gamePanel.gameLost();
        new ActionListDialogBuilder().setTitle("Do you wish to play a new Game?")
            .addAction("Yes", () -> {
              startNewGame();
              gamePanel.setEventPanel(new EventPanel("""
                  This your first day, again!
                  """));
            }).addAction("No", () -> System.exit(0)).build().showDialog(gui);
      }
      if (gameState.playerRecord().hasWon()) {
        gamePanel.gameWon();

        new ActionListDialogBuilder().setTitle("Do you wish to play a new Game?")
            .addAction("Yes", () -> {
              startNewGame();
            }).addAction("No", () -> System.exit(0)).build().showDialog(gui);

      }
    }
    return gameState;
  }

  /**
   * Receives Notifications about GameEvents and updates UI elements accordingly
   *
   * @param event
   * @param msg
   * @return
   */
  @Override
  public HashMap<EventName, String> onGameEvent(EventName event, String msg) {
    switch (event) {
      case ROB -> {
        EventPanel eventPanel = new EventPanel(ROB_TEXT + msg);
        gameWindow.getGamePanel().setEventPanel(eventPanel);
      }
      case CITY_POTION -> {
        EventPanel eventPanel = new EventPanel(STRANGE_MARKET_TEXT + msg);
        gameWindow.getGamePanel().setEventPanel(eventPanel);
      }
      case POTION -> {
        EventPanel eventPanel = new EventPanel(POTION_CRAZE_TEXT + msg + POTION_CRAZE_TEXT_PART_2);
        gameWindow.getGamePanel().setEventPanel(eventPanel);
      }
      default -> {
        EventPanel eventPanel = new EventPanel(BORING_TRAVEL_TEXT);
        gameWindow.getGamePanel().setEventPanel(eventPanel);
      }
    }

    return GameStateListener.super.onGameEvent(event, msg);
  }

  public GameState getLastGameState() {
    return lastGameState;
  }


  public void startNewGame() {
    this.game = new Game(this);
  }

  public Game getGame() {
    return game;
  }
}
