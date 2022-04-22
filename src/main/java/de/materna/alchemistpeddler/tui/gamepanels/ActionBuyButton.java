package de.materna.alchemistpeddler.tui.gamepanels;

import static de.materna.alchemistpeddler.tui.TUIApp.gameController;
import static de.materna.alchemistpeddler.tui.TUIApp.gameWindow;

import com.googlecode.lanterna.gui2.Button;

import de.materna.alchemistpeddler.gamelogic.PlayerRecord;
import de.materna.alchemistpeddler.gameuicommunication.CITY_NAMES;


public class ActionBuyButton extends Button {

  public ActionBuyButton() {
    super("Buy potions",
        () -> {
          PlayerRecord playerRecord = gameController.getLastGameState().playerRecord();
          String playerLocationName = playerRecord.location().name();
          LocationPanel locationPanel = gameWindow.getGamePanel().getLocationPanel();
          locationPanel.setInfoPanel(
              ShopPanel
                  .ShopFactory.getShop(
                      CITY_NAMES.valueOf(
                          playerLocationName.toUpperCase())
                  )
              , playerLocationName
          );
          locationPanel
              .setInteractionPanel(ShopPanel.ShopFactory.getShopActionPanel()
                  , "What do you want to buy?");
        }
    );
  }

}
