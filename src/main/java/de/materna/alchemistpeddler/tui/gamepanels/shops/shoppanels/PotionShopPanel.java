package de.materna.alchemistpeddler.tui.gamepanels.shops.shoppanels;


import com.googlecode.lanterna.gui2.GridLayout;
import de.materna.alchemistpeddler.gamelogic.CityRecord;
import de.materna.alchemistpeddler.gameuicommunication.Potion;
import de.materna.alchemistpeddler.tui.gamepanels.shops.ShopPanel;


public class PotionShopPanel extends ShopPanel {

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
      PotionPanel potionPanel = new PotionPanel(nameLabel, cityRecord, amountLabel,priceLabel);
      buyablePanels.add(potionPanel);
      addComponent(potionPanel);
    }
  }
}
