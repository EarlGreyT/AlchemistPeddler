package de.materna.alchemistpeddler.tui.gamepanels.shops.shoppanels;

import static de.materna.alchemistpeddler.tui.TUIApp.gameController;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.Panel;
import de.materna.alchemistpeddler.gamelogic.PlayerRecord;
import de.materna.alchemistpeddler.gameuicommunication.Buyable;
import de.materna.alchemistpeddler.gameuicommunication.CITY_NAME;
import de.materna.alchemistpeddler.gameuicommunication.CityGraph;
import de.materna.alchemistpeddler.gameuicommunication.PlayerAction;
import de.materna.alchemistpeddler.gameuicommunication.PlayerEvent;
import de.materna.alchemistpeddler.tui.GameController;
import de.materna.alchemistpeddler.tui.TUIApp;
import de.materna.alchemistpeddler.tui.gamepanels.shops.AbstractShopFactory;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;

public class ShopActionPanel extends Panel {
  protected PlayerAction action;
  protected AbstractShopFactory shopFactory;
  ShopActionPanel(PlayerAction action){
    setLayoutManager(new LinearLayout().setSpacing(1));
     shopFactory = gameController.shopFactories.get(action);
    this.action=action;
  }


}
