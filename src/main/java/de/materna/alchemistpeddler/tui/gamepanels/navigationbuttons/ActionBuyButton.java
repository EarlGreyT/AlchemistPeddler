package de.materna.alchemistpeddler.tui.gamepanels.navigationbuttons;

import static de.materna.alchemistpeddler.tui.TUIApp.gameController;
import static de.materna.alchemistpeddler.tui.TUIApp.gameWindow;

import com.googlecode.lanterna.gui2.Button;

import de.materna.alchemistpeddler.gamelogic.PlayerRecord;
import de.materna.alchemistpeddler.gameuicommunication.CITY_NAME;
import de.materna.alchemistpeddler.tui.gamepanels.LocationPanel;
import de.materna.alchemistpeddler.tui.gamepanels.shops.ShopFactory;


public class ActionBuyButton extends Button {

  public ActionBuyButton() {
    super("Buy potions",
        () -> {
          PlayerRecord playerRecord = gameController.getLastGameState().playerRecord();
          String playerLocationName = playerRecord.location().name();
          LocationPanel locationPanel = gameWindow.getGamePanel().getLocationPanel();
          locationPanel.setInfoPanel(
              ShopFactory.getPotionShop(
                      CITY_NAME.valueOf(
                          playerLocationName.toUpperCase())
                  )
              , playerLocationName
          );
          locationPanel
              .setInteractionPanel(ShopFactory.getPotionShopActionPanel()
                  , "What do you want to buy?");
        }
    );
  }

}
