package de.materna.alchemistpeddler.tui.gamepanels.shops;

import de.materna.alchemistpeddler.gamelogic.CityRecord;
import de.materna.alchemistpeddler.gameuicommunication.Potion;
import de.materna.alchemistpeddler.tui.gamepanels.AmountLabel;
import de.materna.alchemistpeddler.tui.gamepanels.NameLabel;

public class PotionPanel extends BuyablePanel {
  private final AmountLabel amountLabel;
  private final AmountLabel priceLabel;
  public PotionPanel(NameLabel nameLabel, CityRecord cityRecord, AmountLabel amountLabel, AmountLabel priceLabel){
    super(nameLabel, cityRecord,amountLabel, priceLabel);
    this.amountLabel = amountLabel;
    this.priceLabel = priceLabel;
  }


  @Override
  public void update() {
    String potionName = nameLabel.getText();
    int potionAmount = cityRecord.potionAmounts().get(Potion.valueOf(potionName).ordinal());
    int potionPrice = cityRecord.prices().get(Potion.valueOf(potionName).ordinal());
    amountLabel.setText(potionAmount);
    priceLabel.setText(potionPrice);
  }

  public AmountLabel getAmountLabel() {
    return amountLabel;
  }

  public AmountLabel getPriceLabel() {
    return priceLabel;
  }
}


