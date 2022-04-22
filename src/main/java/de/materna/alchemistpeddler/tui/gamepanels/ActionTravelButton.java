package de.materna.alchemistpeddler.tui.gamepanels;

import static de.materna.alchemistpeddler.tui.TUIApp.gameController;
import static de.materna.alchemistpeddler.tui.TUIApp.gameWindow;

import com.googlecode.lanterna.gui2.Button;
import de.materna.alchemistpeddler.gamelogic.PlayerRecord;
import de.materna.alchemistpeddler.gameuicommunication.CITY_NAMES;
import de.materna.alchemistpeddler.gameuicommunication.CityGraph;
import java.util.EnumMap;


public class ActionTravelButton extends Button {


  public ActionTravelButton() {
    super("travel", () -> {
      PlayerRecord playerRecord = gameController.getLastGameState().playerRecord();
      CITY_NAMES playerLocationName = CITY_NAMES.valueOf(playerRecord.location().name().toUpperCase());
      LocationPanel locationPanel = gameWindow.getGamePanel().getLocationPanel();
      EnumMap<CITY_NAMES, Integer> routeCosts = new EnumMap<>(CITY_NAMES.class);
      for (CITY_NAMES city_name : CITY_NAMES.values()) {
        routeCosts.put(city_name, CityGraph.routes.get(city_name).get(playerLocationName).cost());
      }
    });
  }
}
