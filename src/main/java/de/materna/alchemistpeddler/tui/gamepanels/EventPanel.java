package de.materna.alchemistpeddler.tui.gamepanels;

import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.Panel;

/**
 * this holds Information about the last GameEvent that happened.
 */
public class EventPanel extends Panel {

  public EventPanel() {
    setLayoutManager(new LinearLayout());
  }
  public EventPanel(String text){
    this();
    Label textLabel = new Label(text);
    addComponent(textLabel);
  }
}
