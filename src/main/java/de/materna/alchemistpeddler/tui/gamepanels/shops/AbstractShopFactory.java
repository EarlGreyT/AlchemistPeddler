package de.materna.alchemistpeddler.tui.gamepanels.shops;

import de.materna.alchemistpeddler.gameuicommunication.CITY_NAME;
import de.materna.alchemistpeddler.tui.gamepanels.shops.shoppanels.ShopActionPanel;
import java.util.HashMap;

public abstract class AbstractShopFactory {
  protected HashMap<CITY_NAME, ShopPanel> shops= new HashMap<>();
  protected ShopActionPanel shopActionPanel;
  protected ShopActionPanel sellShopActionPanel;
  public abstract ShopPanel getShop(CITY_NAME city_name);
  public abstract ShopActionPanel getShopActionPanel();
  public abstract ShopActionPanel getSellShopActionPanel();
  public void updateShopLabels(){
    for (ShopPanel shopPanel : shops.values()) {
      shopPanel.update();
    }
  }
}
