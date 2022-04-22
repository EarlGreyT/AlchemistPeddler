package de.materna.alchemistpeddler.tui.gamePanels;

import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.Panel;

public class GamePanel extends Panel {

  private ActionPanel actionPanel;
  private LocationPanel locationPanel;

  public GamePanel() {
    setLayoutManager(new LinearLayout());
    locationPanel = new LocationPanel();
    actionPanel = new ActionPanel();
    addComponent(locationPanel);
    addComponent(actionPanel);
  }

  public ActionPanel getActionPanel() {
    return actionPanel;
  }

  public void setActionPanel(ActionPanel actionPanel) {
    this.actionPanel = actionPanel;
  }

  public LocationPanel getDataPanel() {
    return locationPanel;
  }

  public void setDataPanel(LocationPanel locationPanel) {
    this.locationPanel = locationPanel;
  }
}
