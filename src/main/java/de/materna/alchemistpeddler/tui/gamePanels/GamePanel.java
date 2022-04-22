package de.materna.alchemistpeddler.tui.gamePanels;

import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.Panel;

public class GamePanel extends Panel {

  private PlayerPanel playerPanel;
  private LocationPanel locationPanel;

  public GamePanel() {
    setLayoutManager(new LinearLayout());
    locationPanel = new LocationPanel();
    playerPanel = new PlayerPanel();
    addComponent(locationPanel);
    addComponent(playerPanel);
  }

  public PlayerPanel getPlayerPanel() {
    return playerPanel;
  }

  public LocationPanel getDataPanel() {
    return locationPanel;
  }
}


