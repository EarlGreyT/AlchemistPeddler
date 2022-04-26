package de.materna.alchemistpeddler.tui.gamepanels.shops.shoppanels;

import static de.materna.alchemistpeddler.tui.TUIApp.gameController;

import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.Panel;
import de.materna.alchemistpeddler.gameuicommunication.PlayerAction;
import de.materna.alchemistpeddler.tui.gamepanels.shops.AbstractShopFactory;

public class ShopActionPanel extends Panel {
  protected PlayerAction action;
  protected AbstractShopFactory shopFactory;
  ShopActionPanel(PlayerAction action){
    setLayoutManager(new LinearLayout().setSpacing(1));
     shopFactory = gameController.shopFactories.get(action);
    this.action=action;
  }


}
