package de.materna.alchemistpeddler.tui.gamepanels.shops.shoppanels;

import com.googlecode.lanterna.gui2.GridLayout;
import de.materna.alchemistpeddler.gamelogic.CityRecord;
import de.materna.alchemistpeddler.gameuicommunication.CITY_NAME;
import de.materna.alchemistpeddler.gameuicommunication.CityGraph;
import de.materna.alchemistpeddler.tui.gamepanels.shops.ShopPanel;

public class TravelShopPanel extends ShopPanel {
  public TravelShopPanel(CityRecord cityRecord){
    super();
    this.cityRecord = cityRecord;
    setLayoutManager(new GridLayout(CITY_NAME.values().length / 2)
        .setHorizontalSpacing(2)
        .setVerticalSpacing(1)
    );
    for (CITY_NAME city_name : CITY_NAME.values()) {
      int travelCost = CityGraph.routes
          .get(CITY_NAME.valueOf(cityRecord.name().toUpperCase()))
          .get(city_name).cost();
      NameLabel nameLabel = new NameLabel(city_name.cityName);
      AmountLabel priceLabel = new AmountLabel("Price");
      priceLabel.setText(travelCost);
      DestinationPanel destinationPanel = new DestinationPanel(nameLabel,cityRecord,priceLabel);
      buyablePanels.add(destinationPanel);
      addComponent(destinationPanel);
    }
  }
}
