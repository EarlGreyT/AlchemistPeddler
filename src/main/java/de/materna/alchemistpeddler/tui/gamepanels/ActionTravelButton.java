package de.materna.alchemistpeddler.tui.gamepanels;

import static de.materna.alchemistpeddler.tui.TUIApp.gameController;
import static de.materna.alchemistpeddler.tui.TUIApp.gameWindow;

import com.googlecode.lanterna.gui2.Button;
import de.materna.alchemistpeddler.gamelogic.PlayerRecord;
import de.materna.alchemistpeddler.gameuicommunication.CITY_NAME;
import de.materna.alchemistpeddler.gameuicommunication.CityGraph;
import java.util.EnumMap;


public class ActionTravelButton extends Button {


  public ActionTravelButton() {
    super("travel", () -> {
      PlayerRecord playerRecord = gameController.getLastGameState().playerRecord();
      CITY_NAME playerLocationName = CITY_NAME.valueOf(playerRecord.location().name().toUpperCase());
      LocationPanel locationPanel = gameWindow.getGamePanel().getLocationPanel();
      locationPanel.setInfoPanel(ShopFactory.getTravelShop(playerLocationName), playerRecord.location().name());
      locationPanel.setInteractionPanel(ShopFactory.getTravelActionPanel(),"Where do you want to go?");
    });
  }
}
