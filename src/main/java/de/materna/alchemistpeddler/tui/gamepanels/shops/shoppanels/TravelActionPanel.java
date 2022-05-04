package de.materna.alchemistpeddler.tui.gamepanels.shops.shoppanels;

import com.googlecode.lanterna.gui2.Button;
import de.materna.alchemistpeddler.gamelogic.CityGraph;
import de.materna.alchemistpeddler.gamelogic.PlayerRecord;
import de.materna.alchemistpeddler.gameuicommunication.CITY_NAME;
import de.materna.alchemistpeddler.gameuicommunication.PlayerAction;
import de.materna.alchemistpeddler.gameuicommunication.PlayerEvent;
import de.materna.alchemistpeddler.gameuicommunication.GameController;
import de.materna.alchemistpeddler.tui.TUIApp;

public class TravelActionPanel extends ShopActionPanel {

  public TravelActionPanel() {
    super(PlayerAction.TRAVEL);
    for (CITY_NAME city_name : CITY_NAME.values()) {
      Button travelButton = new Button("travel to " + city_name.cityName,
          () -> {
            GameController gameController =TUIApp.gameController;
            CityGraph cityGraph = gameController.getLastGameState().cityGraph();
            PlayerRecord playerRecord = gameController.getLastGameState().playerRecord();
            CITY_NAME location = CITY_NAME.valueOf(playerRecord.location().name().toUpperCase());
            int amount = cityGraph.getPrice(location,city_name);
            gameController.inform(gameController.getGame(), new PlayerEvent(action, city_name.ordinal(),amount));
            TUIApp.gameWindow.getGamePanel().getLocationPanel().setInfoPanel(shopFactory.getShop(city_name), city_name.cityName);
          }
      );
      addComponent(travelButton);
    }
  }
}
