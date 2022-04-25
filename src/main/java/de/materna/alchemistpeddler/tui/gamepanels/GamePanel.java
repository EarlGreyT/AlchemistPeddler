package de.materna.alchemistpeddler.tui.gamepanels;

import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.Panel;

public class GamePanel extends Panel {

  private PlayerPanel playerPanel;
  private LocationPanel locationPanel;
  private EventPanel eventPanel;
  public GamePanel() {
    setLayoutManager(new LinearLayout(Direction.HORIZONTAL));
    Panel playField = new Panel(new LinearLayout());
    locationPanel = new LocationPanel();
    playerPanel = new PlayerPanel();
    eventPanel = new EventPanel("""
        This is the first Day for you as a travelling potion peddler.
        
        Will you become rich or will your business fall victim 
        to the whims of an ever changing market?
        """);
    addComponent(playField);
    addComponent(eventPanel);
    playField.addComponent(locationPanel);
    playField.addComponent(playerPanel);
  }

  public void setEventPanel(EventPanel eventPanel) {
    removeComponent(this.eventPanel);
    this.eventPanel = eventPanel;
    addComponent(this.eventPanel);
  }

  public PlayerPanel getPlayerPanel() {
    return playerPanel;
  }

  public LocationPanel getLocationPanel() {
    return locationPanel;
  }
}


