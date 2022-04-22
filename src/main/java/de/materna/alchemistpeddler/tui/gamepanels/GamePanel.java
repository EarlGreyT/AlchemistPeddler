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
    eventPanel = new EventPanel();
    addComponent(playField);
    addComponent(eventPanel);
    playField.addComponent(locationPanel);
    playField.addComponent(playerPanel);
  }

  public PlayerPanel getPlayerPanel() {
    return playerPanel;
  }

  public LocationPanel getLocationPanel() {
    return locationPanel;
  }
}


