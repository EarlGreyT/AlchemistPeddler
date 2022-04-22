package de.materna.alchemistpeddler.tui.gamepanels;

import com.googlecode.lanterna.gui2.Label;

public class AmountLabel extends Label {
  private String type;
  public AmountLabel(Integer potionAmount) {
    this("Amount");
    setText(potionAmount);

  }
  public AmountLabel(String text) {
    super(text);
    type = text;
  }


  public void setText(int potionAmount) {
    setText(type+" "+ potionAmount);
  }
}
