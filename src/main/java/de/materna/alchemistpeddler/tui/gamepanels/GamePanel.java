package de.materna.alchemistpeddler.tui.gamepanels;


import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.Panel;


public class GamePanel extends Panel {
  private final PlayerPanel playerPanel;
  private final LocationPanel locationPanel;
  private final Panel infoField;
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
  }

  public void gameWon(){
    setEventPanel(
    new EventPanel("""
        You won!
                  
        You should play again!
        """));
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


