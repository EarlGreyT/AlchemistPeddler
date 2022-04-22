package de.materna.alchemistpeddler.tui.gamePanels;


import com.googlecode.lanterna.gui2.GridLayout;

import com.googlecode.lanterna.gui2.Panel;
import de.materna.alchemistpeddler.gamelogic.CityRecord;
import de.materna.alchemistpeddler.gameuicommunication.CITY_NAMES;
import de.materna.alchemistpeddler.gameuicommunication.Potion;
import de.materna.alchemistpeddler.tui.TUIApp;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;

public class ShopPanel extends Panel {
  private CityRecord cityRecord;
  private ArrayList<PotionPanel> potionPanels = new ArrayList<>();
  public static class ShopFactory {
    static ShopActionPanel shopActionPanel;
    static EnumMap<CITY_NAMES, ShopPanel> shops = new EnumMap<CITY_NAMES, ShopPanel>(CITY_NAMES.class);
    public static ShopPanel getShop(CITY_NAMES cityName) {
      ShopPanel existingPanel = shops.get(cityName);
      if (existingPanel == null) {
        CityRecord cityRecord = (CityRecord) TUIApp.gameController.getLastGameState()
            .cityRecords().stream().filter(city -> city.name().equals(cityName.cityName)).toArray()[0];
        existingPanel = new ShopPanel(cityRecord);
      } else updateShopLabels();
      return existingPanel;
    }
    public static ShopActionPanel getShopActionPanel(){
      if (shopActionPanel == null){
        shopActionPanel = new ShopActionPanel();
      }
      return shopActionPanel;
    }
    public static void updateShopLabels() {
      for (ShopPanel shopPanel : shops.values()) {
        for (PotionPanel potionPanel : shopPanel.potionPanels) {
          String potionName = ((NameLabel) potionPanel.getChildrenList().get(0)).getText();
          int potionAmount = shopPanel.cityRecord.potionAmounts().get(Potion.valueOf(potionName).ordinal());
          int potionPrice = shopPanel.cityRecord.prices().get(Potion.valueOf(potionName).ordinal());
          AmountLabel potionAmountLabel = potionPanel.getAmountLabel();
          AmountLabel potionPriceLabel = potionPanel.getPriceLabel();
          potionAmountLabel.setText(potionAmount);
          potionPriceLabel.setText(potionPrice);
        }
      }

    }
  }

  private ShopPanel(CityRecord cityRecord) {
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
