package de.materna.alchemistpeddler.tui.gamepanels;

import com.googlecode.lanterna.gui2.Borders;
import com.googlecode.lanterna.gui2.Component;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.Panel;

public class LocationPanel extends Panel {
  Component infoPanel = new Panel();
  Component interactionPanel = new Panel();
  public LocationPanel() {
    super();
    addComponent(infoPanel);
    addComponent(interactionPanel);
    setLayoutManager(new LinearLayout(Direction.HORIZONTAL));
  }

  public void setInfoPanel(Panel leftPanel, String text) {
    removeComponent(this.infoPanel);
    this.infoPanel = leftPanel.withBorder(Borders.singleLineBevel(text));
    addComponent(0, this.infoPanel);
  }

  public void setInteractionPanel(Panel rightPanel, String text) {
    removeComponent(this.interactionPanel);
    this.interactionPanel = rightPanel.withBorder(Borders.singleLineBevel(text));
    addComponent(1, this.interactionPanel);
  }
}
