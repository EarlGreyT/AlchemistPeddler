package de.materna.alchemistpeddler.tui.gamepanels.navigationbuttons;

import static de.materna.alchemistpeddler.tui.TUIApp.gameController;
import static de.materna.alchemistpeddler.tui.TUIApp.gameWindow;

import com.googlecode.lanterna.gui2.Button;
import de.materna.alchemistpeddler.gamelogic.PlayerRecord;
import de.materna.alchemistpeddler.gameuicommunication.CITY_NAME;
import de.materna.alchemistpeddler.gameuicommunication.PlayerAction;
import de.materna.alchemistpeddler.tui.gamepanels.LocationPanel;
import de.materna.alchemistpeddler.tui.gamepanels.shops.AbstractShopFactory;

public class ActionSellButton extends Button {
  public ActionSellButton(){
    super("sell potions",
        () -> {
          PlayerRecord playerRecord = gameController.getLastGameState().playerRecord();
          String playerLocationName = playerRecord.location().name();
          AbstractShopFactory shopFactory = gameController.shopFactories.get(PlayerAction.SELL);
          LocationPanel locationPanel = gameWindow.getGamePanel().getLocationPanel();
          locationPanel.setInfoPanel(
              shopFactory.getShop(
                  CITY_NAME.valueOf(
                      playerLocationName.toUpperCase())
              )
              , playerLocationName
          );
          locationPanel
              .setInteractionPanel(shopFactory.getSellShopActionPanel()
                  , "What do you want to sell?");
        }
    );
  }
}
