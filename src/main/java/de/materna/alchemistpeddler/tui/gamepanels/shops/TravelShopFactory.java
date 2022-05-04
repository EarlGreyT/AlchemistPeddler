package de.materna.alchemistpeddler.tui.gamepanels.shops;

import de.materna.alchemistpeddler.gamelogic.CityRecord;
import de.materna.alchemistpeddler.gameuicommunication.CITY_NAME;
import de.materna.alchemistpeddler.tui.TUIApp;
import de.materna.alchemistpeddler.tui.gamepanels.shops.shoppanels.ShopActionPanel;
import de.materna.alchemistpeddler.tui.gamepanels.shops.shoppanels.TravelActionPanel;
import de.materna.alchemistpeddler.tui.gamepanels.shops.shoppanels.TravelShopPanel;

/**
 * Creates the UI-Elements that make up a TravelShop
 */
public class TravelShopFactory extends AbstractShopFactory{

  @Override
  public ShopPanel getShop(CITY_NAME cityName) {
    ShopPanel existingPanel = shops.get(cityName);
    if (existingPanel == null) {
      CityRecord cityRecord = (CityRecord) TUIApp.gameController.getLastGameState()
          .cityRecords().stream().filter(city -> city.name().equals(cityName.cityName))
          .toArray()[0];
      existingPanel = new TravelShopPanel(cityRecord);
      shops.put(cityName,existingPanel);
    } else {
      updateShopLabels();
    }
    return existingPanel;
  }

  @Override
  public ShopActionPanel getShopActionPanel() {
    if (shopActionPanel == null) {
      shopActionPanel = new TravelActionPanel();
    }
    return shopActionPanel;
  }

  @Override
  public ShopActionPanel getSellShopActionPanel() {
    return getShopActionPanel();
  }
}
