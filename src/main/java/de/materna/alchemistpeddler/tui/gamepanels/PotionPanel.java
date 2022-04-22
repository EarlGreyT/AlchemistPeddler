package de.materna.alchemistpeddler.tui.gamepanels;

import com.googlecode.lanterna.gui2.LayoutManager;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.Panel;

public class PotionPanel extends Panel {
  private NameLabel nameLabel;
  private AmountLabel amountLabel;
  private AmountLabel priceLabel;

  public PotionPanel(NameLabel nameLabel, AmountLabel amountLabel, AmountLabel priceLabel){
    this(new LinearLayout());
    this.nameLabel = nameLabel;
    this.amountLabel = amountLabel;
    this.priceLabel = priceLabel;
    addComponent(nameLabel);
    addComponent(amountLabel);
    addComponent(priceLabel);
  }
  private PotionPanel(LayoutManager layoutManager) {
    super(layoutManager);
  }

  public NameLabel getNameLabel() {
    return nameLabel;
  }

  public void setNameLabel(NameLabel nameLabel) {
    this.nameLabel = nameLabel;
  }

  public AmountLabel getAmountLabel() {
    return amountLabel;
  }

  public void setAmountLabel(AmountLabel amountLabel) {
    this.amountLabel = amountLabel;
  }

  public AmountLabel getPriceLabel() {
    return priceLabel;
  }

  public void setPriceLabel(AmountLabel priceLabel) {
    this.priceLabel = priceLabel;
  }
}
