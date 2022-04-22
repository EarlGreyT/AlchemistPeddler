package de.materna.alchemistpeddler.tui.gamePanels;

import com.googlecode.lanterna.gui2.Borders;
import com.googlecode.lanterna.gui2.Component;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.Panel;

public class LocationPanel extends Panel {
  Component leftPanel = new Panel();
  Component rightPanel = new Panel();
  public LocationPanel() {
    super();
    addComponent(leftPanel);
    addComponent(rightPanel);
    setLayoutManager(new LinearLayout(Direction.HORIZONTAL));

  }

  public void setLeftPanel(Panel leftPanel, String text) {
    removeComponent(this.leftPanel);
    this.leftPanel = leftPanel.withBorder(Borders.singleLineBevel(text));
    addComponent(0, this.leftPanel);
  }

  public void setRightPanel(Panel rightPanel, String text) {
    removeComponent(this.rightPanel);
    this.rightPanel = rightPanel.withBorder(Borders.singleLineBevel(text));
    addComponent(1, this.rightPanel);
  }
}
