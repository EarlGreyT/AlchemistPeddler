package de.materna.alchemistpeddler.tui.gamepanels.navigationbuttons;

import static de.materna.alchemistpeddler.tui.TUIApp.gameController;
import static de.materna.alchemistpeddler.tui.TUIApp.gameWindow;

import com.googlecode.lanterna.gui2.Button;

import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.Panel;
import de.materna.alchemistpeddler.gamelogic.PlayerRecord;
import de.materna.alchemistpeddler.gameuicommunication.CITY_NAME;
import de.materna.alchemistpeddler.gameuicommunication.PlayerAction;
import de.materna.alchemistpeddler.tui.gamepanels.LocationPanel;
import de.materna.alchemistpeddler.tui.gamepanels.shops.AbstractShopFactory;


public class ActionBuyButton extends Button {

  public ActionBuyButton() {
    super("Buy potions",
        () -> {
          PlayerRecord playerRecord = gameController.getLastGameState().playerRecord();
          String playerLocationName = playerRecord.location().name();
          AbstractShopFactory shopFactory = gameController.shopFactories.get(PlayerAction.BUY);
          LocationPanel locationPanel = gameWindow.getGamePanel().getLocationPanel();
          locationPanel.setInfoPanel(
              shopFactory.getShop(
                      CITY_NAME.valueOf(
                          playerLocationName.toUpperCase())
                  )
              , playerLocationName
          );
          Panel buySellPanel = new Panel();
          buySellPanel.setLayoutManager(new LinearLayout(Direction.HORIZONTAL));
          buySellPanel.addComponent(shopFactory.getShopActionPanel());
          buySellPanel.addComponent(shopFactory.getSellShopActionPanel());

          locationPanel
              .setInteractionPanel(
                  buySellPanel
                  , "");
        }
    );
  }

}
