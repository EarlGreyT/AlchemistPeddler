package de.materna.alchemistpeddler.tui.gamepanels.navigationbuttons;

import static de.materna.alchemistpeddler.tui.TUIApp.gameController;
import static de.materna.alchemistpeddler.tui.TUIApp.gameWindow;

import com.googlecode.lanterna.gui2.Button;
import de.materna.alchemistpeddler.gamelogic.PlayerRecord;
import de.materna.alchemistpeddler.gameuicommunication.CITY_NAME;
import de.materna.alchemistpeddler.gameuicommunication.PlayerAction;
import de.materna.alchemistpeddler.tui.gamepanels.LocationPanel;
import de.materna.alchemistpeddler.tui.gamepanels.shops.AbstractShopFactory;


public class ActionTravelButton extends Button {


  public ActionTravelButton() {
    super("travel", () -> {
      AbstractShopFactory shopFactory = gameController.shopFactories.get(PlayerAction.TRAVEL);
      PlayerRecord playerRecord = gameController.getLastGameState().playerRecord();
      CITY_NAME playerLocationName = CITY_NAME.valueOf(playerRecord.location().name().toUpperCase());
      LocationPanel locationPanel = gameWindow.getGamePanel().getLocationPanel();
      locationPanel.setInfoPanel(
          shopFactory.getShop(playerLocationName), playerRecord.location().name());
      locationPanel.setInteractionPanel(shopFactory.getShopActionPanel(),"Where do you want to go?");
    });
  }
}
