package de.materna.alchemistpeddler.tui.gamepanels;


import com.googlecode.lanterna.gui2.GridLayout;

import com.googlecode.lanterna.gui2.Panel;
import de.materna.alchemistpeddler.gamelogic.CityRecord;

import de.materna.alchemistpeddler.gameuicommunication.Potion;

import java.util.ArrayList;


class PotionShopPanel extends Panel {
  private CityRecord cityRecord;
  private ArrayList<PotionPanel> potionPanels = new ArrayList<>();

  protected void update(){
    for (PotionPanel potionPanel : potionPanels) {
      String potionName = ((NameLabel) potionPanel.getChildrenList().get(0)).getText();
      int potionAmount = cityRecord.potionAmounts().get(Potion.valueOf(potionName).ordinal());
      int potionPrice = cityRecord.prices().get(Potion.valueOf(potionName).ordinal());
      AmountLabel potionAmountLabel = potionPanel.getAmountLabel();
      AmountLabel potionPriceLabel = potionPanel.getPriceLabel();
      potionAmountLabel.setText(potionAmount);
      potionPriceLabel.setText(potionPrice);
    }
  }
  public PotionShopPanel(CityRecord cityRecord) {
    super();
    this.cityRecord = cityRecord;
    setLayoutManager(new GridLayout(Potion.values().length / 3)
        .setHorizontalSpacing(2)
        .setVerticalSpacing(1)
    );
    for (Potion potion : Potion.values()) {
      int potionAmount = cityRecord.potionAmounts().get(potion.ordinal());
      int potionPrice = cityRecord.prices().get(potion.ordinal());
      NameLabel nameLabel = new NameLabel(potion.name());
      AmountLabel amountLabel = new AmountLabel(potionAmount);
      AmountLabel priceLabel = new AmountLabel("Price");
      priceLabel.setText(potionPrice);
      PotionPanel potionPanel = new PotionPanel(nameLabel,amountLabel,priceLabel);
      potionPanels.add(potionPanel);
      addComponent(potionPanel);
    }
  }
}
