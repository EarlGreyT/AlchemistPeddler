package de.materna.alchemistpeddler.tui.gamepanels;

import com.googlecode.lanterna.gui2.Borders;
import com.googlecode.lanterna.gui2.Component;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.Panel;


public class PlayerPanel extends Panel {

  public static final String CHARACTER = "Character";
  public static final String CHARACTER_ACTIONS = "What do you want to Do?";
  private Component playerActionPanel = new ActionPanel();
  private Component playerDataPanel = new PlayerDataPanel();
  public PlayerPanel() {
    super();
    addComponent(playerActionPanel
        .withBorder(Borders.singleLineBevel(CHARACTER_ACTIONS)));
    addComponent(playerDataPanel
        .withBorder(Borders.singleLineBevel(CHARACTER)));
    setLayoutManager(new LinearLayout(Direction.HORIZONTAL));
  }
  public ActionPanel getPlayerActionPanel() {
    return (ActionPanel) playerActionPanel;
  }
  public void setPlayerActionPanel(ActionPanel actionPanel) {
    removeComponent(this.playerActionPanel);
    this.playerActionPanel = actionPanel.withBorder(Borders.singleLineBevel("What do you want to Do?"));
    addComponent(0, this.playerActionPanel);
  }
  public void setPlayerDataPanel(PlayerDataPanel playerDataPanel) {
    removeComponent(this.playerDataPanel);
    this.playerDataPanel = playerDataPanel
        .withBorder(Borders.singleLineBevel(CHARACTER));
    addComponent(1, this.playerDataPanel);
  }

  public PlayerDataPanel getPlayerDataPanel() {
    return (PlayerDataPanel) playerDataPanel;
  }
}
