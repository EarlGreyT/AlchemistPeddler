package de.materna.alchemistpeddler.tui;

import static de.materna.alchemistpeddler.tui.TUIApp.gameWindow;
import static de.materna.alchemistpeddler.tui.TUIApp.gui;

import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Button;
import de.materna.alchemistpeddler.gamelogic.Game;
import de.materna.alchemistpeddler.gamelogic.GameEvent;
import de.materna.alchemistpeddler.gamelogic.GameEvent.EventName;
import de.materna.alchemistpeddler.gamelogic.GameState;
import de.materna.alchemistpeddler.gameuicommunication.GameStateListener;
import de.materna.alchemistpeddler.gameuicommunication.PlayerAction;
import de.materna.alchemistpeddler.gameuicommunication.PlayerEventGenerator;
import de.materna.alchemistpeddler.tui.gamepanels.EventPanel;
import de.materna.alchemistpeddler.tui.gamepanels.GamePanel;
import de.materna.alchemistpeddler.tui.gamepanels.shops.AbstractShopFactory;
import de.materna.alchemistpeddler.tui.gamepanels.shops.PotionShopFactory;
import de.materna.alchemistpeddler.tui.gamepanels.shops.TravelShopFactory;
import java.util.EnumMap;
import java.util.HashMap;

public class GameController implements PlayerEventGenerator, GameStateListener {

  public final EnumMap<PlayerAction, AbstractShopFactory> shopFactories = new EnumMap<>(
      PlayerAction.class);
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
    if (gameWindow != null) {
      gameWindow.getGamePanel().getPlayerPanel().getPlayerDataPanel()
          .update(lastGameState.playerRecord());
      if (gameState.playerRecord().hasLost()) {
        GamePanel gamePanel = gameWindow.getGamePanel();
        gamePanel.gameLost();
      }
      if (gameState.playerRecord().hasWon()) {
        GamePanel gamePanel = gameWindow.getGamePanel();
        gamePanel.gameWon();
      }
    }

    return gameState;
  }

  @Override
  public HashMap<EventName, String> getGameEventNotification(EventName event, String msg) {
    switch (event) {
      case ROB -> {
        EventPanel eventPanel = new EventPanel("""
            Your legs feel a little shaky as you set foot into the harbour.
                        
            Another Passenger forces his way to the exit and pushes you aside, but he is long gone
            when you notice that you  are missing some of your hard earned gold.
                        
            You have to make due with: 
            """ + msg);
        gameWindow.getGamePanel().setEventPanel(eventPanel);
      }
      case CITY_POTION -> {
        EventPanel eventPanel = new EventPanel("""
            A Newspaper stand catches your attention, as most papers headlines look 
            similar to: 
            """ + msg);
        gameWindow.getGamePanel().setEventPanel(eventPanel);
      }
      case POTION -> {
        EventPanel eventPanel = new EventPanel("""
            You already heard a lot of chatter during your travels, 
            but as you set foot into the City you realize just how many People are consuming 
            """ + msg + " potions.\n This might be a great business opportunity.");
        gameWindow.getGamePanel().setEventPanel(eventPanel);
      }
      default -> {
        EventPanel eventPanel = new EventPanel("""
            Your travels where without any troubles.
                  
            You feel well rested as you enter the city
            """);
        gameWindow.getGamePanel().setEventPanel(eventPanel);
      }
    }

    return GameStateListener.super.getGameEventNotification(event, msg);
  }

  public GameState getLastGameState() {
    return lastGameState;
  }


}
