package de.materna.alchemistpeddler.tui.gamepanels;

import static de.materna.alchemistpeddler.tui.TUIApp.gameController;
import static de.materna.alchemistpeddler.tui.TUIApp.gameWindow;
import static de.materna.alchemistpeddler.tui.TUIApp.gui;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.Panel;
import de.materna.alchemistpeddler.gamelogic.Game;
import de.materna.alchemistpeddler.tui.GameController;

public class GamePanel extends Panel {
  private PlayerPanel playerPanel;
  private LocationPanel locationPanel;
  private Panel infoField;
  private EventPanel eventPanel;

  /**
   * holds every Element that makes up the Game UI
   */
  public GamePanel() {
    setLayoutManager(new LinearLayout(Direction.VERTICAL));
    infoField = new Panel(new LinearLayout(Direction.HORIZONTAL));
    locationPanel = new LocationPanel();
    playerPanel = new PlayerPanel();
    eventPanel = new EventPanel("""
        This is the first Day for you as a travelling potion peddler.
        
        Will you become rich or will your business fall victim 
        to the whims of an ever changing market?
        """);
    infoField.addComponent(locationPanel);
    infoField.addComponent(eventPanel);
    addComponent(infoField);
    addComponent(playerPanel);
  }

  public void gameLost() {
    setEventPanel(
        new EventPanel("""
        You Lost!
                  
        You should play again!
        """));
    eventPanel.addComponent(new Button("exit", () -> {
      System.exit(0);
    }));
    removeComponent(playerPanel);
  }

  public void gameWon(){
    setEventPanel(
    new EventPanel("""
        You won!
                  
        You should play again!
        """));
    eventPanel.addComponent(new Button("new game", () -> {
      System.exit(0);
    }));
    removeComponent(playerPanel);
  }
  public void setEventPanel(EventPanel eventPanel) {
    infoField.removeComponent(this.eventPanel);
    this.eventPanel = eventPanel;
    infoField.addComponent(this.eventPanel);
  }

  public PlayerPanel getPlayerPanel() {
    return playerPanel;
  }

  public LocationPanel getLocationPanel() {
    return locationPanel;
  }
}


