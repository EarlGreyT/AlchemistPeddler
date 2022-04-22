package de.materna.alchemistpeddler.tui.gamePanels;

import com.googlecode.lanterna.gui2.Component;
import com.googlecode.lanterna.gui2.Label;

public class AmountLabel extends Label {

  public AmountLabel(Integer potionAmount) {
    this(String.valueOf(potionAmount));
  }
  public AmountLabel(String text) {
    super(text);
  }

  public void setText(int potionAmount) {
    setText(String.valueOf(potionAmount));
  }
}
