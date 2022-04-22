package de.materna.alchemistpeddler.tui.gamePanels;


import com.googlecode.lanterna.gui2.Borders;
import com.googlecode.lanterna.gui2.Component;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.GridLayout;

import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.Panel;
import de.materna.alchemistpeddler.gamelogic.CityRecord;
import de.materna.alchemistpeddler.gamelogic.PlayerRecord;
import de.materna.alchemistpeddler.gamelogic.Potion;
import de.materna.alchemistpeddler.gameuicommunication.CITY_NAMES;
import de.materna.alchemistpeddler.tui.TUIApp;
import java.util.HashMap;

public class ShopPanel extends Panel {
  private CityRecord cityRecord;
  public static class ShopFactory {
    static HashMap<String, ShopPanel> shops = new HashMap<>();
    public static ShopPanel getShop(String name) {
      ShopPanel existingPanel = shops.get(name);
      if (existingPanel == null) {
        CityRecord cityRecord = (CityRecord) TUIApp.gameController.getLastGameState()
            .cityRecords().stream().filter(city -> city.name().equals(name)).toArray()[0];
        existingPanel = new ShopPanel(cityRecord);
      } else updateShopLabels();
      return existingPanel;
    }
    public static void updateShopLabels() {
      for (ShopPanel shopPanel : shops.values()) {
        PotionPanel potionPanel= (PotionPanel) shopPanel.getChildrenList().get(0);
        String potionName = ((NameLabel) potionPanel.getChildrenList().get(0)).getText();
        int potionAmount = shopPanel.cityRecord.potionAmounts().get(Potion.valueOf(potionName).ordinal());
        int potionPrice = shopPanel.cityRecord.prices().get(Potion.valueOf(potionName).ordinal());
        AmountLabel potionAmountLabel = (AmountLabel) potionPanel.getChildrenList().get(1);
        AmountLabel potionPriceLabel = (AmountLabel) potionPanel.getChildrenList().get(2);
        potionAmountLabel.setText("Amount " + potionAmount);
        potionPriceLabel.setText("Price "+ potionPrice);
      }

    }
  }



  private ShopPanel(CityRecord cityRecord) {
    super();
    this.cityRecord = cityRecord;
    setLayoutManager(new GridLayout(Potion.values().length / 3));
    for (Potion potion : Potion.values()) {
      int potionAmount = cityRecord.potionAmounts().get(potion.ordinal());
      int potionPrice = cityRecord.prices().get(potion.ordinal());
      PotionPanel potionPanel = new PotionPanel(new LinearLayout(Direction.VERTICAL));
      potionPanel.addComponent(new NameLabel(potion.name()));
      potionPanel.addComponent(new AmountLabel("Amount "+potionAmount));
      potionPanel.addComponent(new AmountLabel("Price "+potionPrice));
      addComponent(potionPanel);
    }
  }
}
