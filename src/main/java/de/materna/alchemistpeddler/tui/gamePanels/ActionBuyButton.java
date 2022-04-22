package de.materna.alchemistpeddler.tui.gamePanels;

import com.googlecode.lanterna.gui2.Borders;
import com.googlecode.lanterna.gui2.Button;
import de.materna.alchemistpeddler.gamelogic.PlayerRecord;
import de.materna.alchemistpeddler.gameuicommunication.CITY_NAMES;
import de.materna.alchemistpeddler.tui.TUIApp;
import java.io.IOException;

public class ActionBuyButton extends Button {

  public ActionBuyButton() {
    super("Buy potions",
        () -> {
          PlayerRecord playerRecord = TUIApp.gameController.getLastGameState().playerRecord();
          TUIApp.gameWindow.getGamePanel().getDataPanel().setLeftPanel(
              ShopPanel
                  .ShopFactory
                  .getShop(
                      CITY_NAMES.valueOf(playerRecord.location().name().toUpperCase())),
              playerRecord.location().name()
          );
          TUIApp.gameWindow.getGamePanel().getDataPanel()
              .setRightPanel(ShopPanel.ShopFactory.getShopActionPanel(), "What do you want to buy?");
        }
    );
  }

}
