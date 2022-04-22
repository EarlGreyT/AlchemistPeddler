package de.materna.alchemistpeddler.tui.gamepanels.shops;

import de.materna.alchemistpeddler.gamelogic.CityRecord;
import de.materna.alchemistpeddler.gameuicommunication.CITY_NAME;
import de.materna.alchemistpeddler.tui.TUIApp;
import java.util.EnumMap;

public class ShopFactory {

  private ShopFactory() {
  }
  static TravelActionPanel travelActionPanel;
  static PotionShopActionPanel potionShopActionPanel;
  static EnumMap<CITY_NAME, TravelShopPanel> travelShops = new EnumMap<>(
      CITY_NAME.class);
  static EnumMap<CITY_NAME, PotionShopPanel> potionShops = new EnumMap<>(
      CITY_NAME.class);

  public static PotionShopPanel getPotionShop(CITY_NAME cityName) {
    PotionShopPanel existingPanel = potionShops.get(cityName);
    if (existingPanel == null) {
      CityRecord cityRecord = (CityRecord) TUIApp.gameController.getLastGameState()
          .cityRecords().stream().filter(city -> city.name().equals(cityName.cityName))
          .toArray()[0];
      existingPanel = new PotionShopPanel(cityRecord);
    } else {
      updateShopLabels();
    }
    return existingPanel;
  }

  public static TravelShopPanel getTravelShop(CITY_NAME cityName) {
    TravelShopPanel existingPanel = travelShops.get(cityName);
    if (existingPanel == null) {
      CityRecord cityRecord = (CityRecord) TUIApp.gameController.getLastGameState()
          .cityRecords().stream().filter(city -> city.name().equals(cityName.cityName))
          .toArray()[0];
      existingPanel = new TravelShopPanel(cityRecord);
    } else {
      updateShopLabels();
    }
    return existingPanel;
  }
  public static PotionShopActionPanel getPotionShopActionPanel() {
    if (potionShopActionPanel == null) {
      potionShopActionPanel = new PotionShopActionPanel();
    }
    return potionShopActionPanel;
  }
  public static TravelActionPanel getTravelActionPanel(){
    if (travelActionPanel == null) {
      travelActionPanel = new TravelActionPanel();
    }
    return travelActionPanel;
  }
  public static void updateShopLabels() {
    for (TravelShopPanel travelShopPanel: travelShops.values()){
      travelShopPanel.update();
    }
    for (PotionShopPanel potionShopPanel : potionShops.values()) {
      potionShopPanel.update();
    }

  }
}