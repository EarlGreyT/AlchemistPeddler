package de.materna.alchemistpeddler.tui.gamepanels;

import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.LayoutManager;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.Panel;
import de.materna.alchemistpeddler.gamelogic.CityRecord;
import de.materna.alchemistpeddler.gameuicommunication.Potion;

public class PotionPanel extends BuyablePanel {
  private AmountLabel amountLabel;
  private AmountLabel priceLabel;
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

}


