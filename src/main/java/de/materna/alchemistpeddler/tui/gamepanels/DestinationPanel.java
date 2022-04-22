package de.materna.alchemistpeddler.tui.gamepanels;

import com.googlecode.lanterna.gui2.LayoutManager;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.Panel;

public class DestinationPanel extends Panel {
  private NameLabel nameLabel;
  private AmountLabel priceLabel;
  public DestinationPanel(NameLabel nameLabel, AmountLabel priceLabel) {
    this(new LinearLayout());
    this.nameLabel = nameLabel;
    this.priceLabel = priceLabel;
    addComponent(nameLabel);
    addComponent(priceLabel);

  }

  private DestinationPanel(LayoutManager layoutManager) {
    super(layoutManager);
  }

  public NameLabel getNameLabel() {
    return nameLabel;
  }

  public void setNameLabel(NameLabel nameLabel) {
    this.nameLabel = nameLabel;
  }

  public AmountLabel getPriceLabel() {
    return priceLabel;
  }

  public void setPriceLabel(AmountLabel priceLabel) {
    this.priceLabel = priceLabel;
  }
}
