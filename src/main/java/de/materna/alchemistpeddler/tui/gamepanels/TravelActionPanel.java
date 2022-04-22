package de.materna.alchemistpeddler.tui.gamepanels;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.Panel;
import de.materna.alchemistpeddler.gamelogic.PlayerRecord;
import de.materna.alchemistpeddler.gameuicommunication.CITY_NAME;
import de.materna.alchemistpeddler.gameuicommunication.CityGraph;
import de.materna.alchemistpeddler.gameuicommunication.PlayerAction;
import de.materna.alchemistpeddler.gameuicommunication.PlayerEvent;
import de.materna.alchemistpeddler.tui.GameController;
import de.materna.alchemistpeddler.tui.TUIApp;
public class TravelActionPanel extends Panel {

  public TravelActionPanel() {
    setLayoutManager(new LinearLayout().setSpacing(1));
    for (CITY_NAME city_name : CITY_NAME.values()) {
      Button travelButton = new Button("travel to " + city_name.name(),
          () -> {
            GameController gameController =TUIApp.gameController;
            PlayerRecord playerRecord = gameController.getLastGameState().playerRecord();
            int amount = CityGraph.routes.get(CITY_NAME.valueOf(playerRecord.location().name().toUpperCase())).get(city_name).cost();
            gameController.inform(GameController.subscribers.get(0), new PlayerEvent(PlayerAction.TRAVEL, city_name.ordinal(),amount));
            TUIApp.gameWindow.getGamePanel().getLocationPanel().setInfoPanel(new Panel(), city_name.cityName);
          }
      );
      addComponent(travelButton);
    }
  }
}
