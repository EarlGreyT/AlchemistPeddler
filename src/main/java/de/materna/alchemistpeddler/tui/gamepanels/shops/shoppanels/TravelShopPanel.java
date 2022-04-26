package de.materna.alchemistpeddler.tui.gamepanels.shops.shoppanels;

import static de.materna.alchemistpeddler.tui.TUIApp.gameController;

import com.googlecode.lanterna.gui2.GridLayout;
import de.materna.alchemistpeddler.gamelogic.CityRecord;
import de.materna.alchemistpeddler.gameuicommunication.CITY_NAME;
import de.materna.alchemistpeddler.gameuicommunication.CityGraph;
import de.materna.alchemistpeddler.tui.gamepanels.shops.ShopPanel;

public class TravelShopPanel extends ShopPanel {
  public TravelShopPanel(CityRecord cityRecord){
    super();
    this.cityRecord = cityRecord;
    CityGraph cityGraph = gameController.getLastGameState().cityGraph();
    setLayoutManager(new GridLayout(CITY_NAME.values().length / 2)
        .setHorizontalSpacing(2)
        .setVerticalSpacing(1)
    );
    for (CITY_NAME city_name : CITY_NAME.values()) {
      CITY_NAME from = CITY_NAME.valueOf(cityRecord.name().toUpperCase());
      int travelCost = cityGraph.getPrice(from,city_name);
      NameLabel nameLabel = new NameLabel(city_name.cityName);
      AmountLabel priceLabel = new AmountLabel("Price");
      priceLabel.setText(travelCost);
      DestinationPanel destinationPanel = new DestinationPanel(nameLabel,cityRecord,priceLabel);
      buyablePanels.add(destinationPanel);
      addComponent(destinationPanel);
    }
  }
}
