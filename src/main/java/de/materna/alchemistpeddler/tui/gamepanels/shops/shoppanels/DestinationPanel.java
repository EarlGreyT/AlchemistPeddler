package de.materna.alchemistpeddler.tui.gamepanels.shops.shoppanels;


import static de.materna.alchemistpeddler.tui.TUIApp.gameController;

import de.materna.alchemistpeddler.gamelogic.CityGraph;
import de.materna.alchemistpeddler.gamelogic.CityRecord;
import de.materna.alchemistpeddler.gameuicommunication.CITY_NAME;
import de.materna.alchemistpeddler.tui.gamepanels.shops.BuyablePanel;


public class DestinationPanel extends BuyablePanel {
  private final AmountLabel priceLabel;
  public DestinationPanel(NameLabel nameLabel, CityRecord cityRecord, AmountLabel priceLabel) {
    super(nameLabel, cityRecord, priceLabel);
    this.priceLabel = priceLabel;
  }


  @Override
  public void update() {
    CITY_NAME location = CITY_NAME.valueOf(cityRecord.name().toUpperCase());
    CITY_NAME destination = CITY_NAME.valueOf(nameLabel.getText().toUpperCase());
    CityGraph cityGraph = gameController.getLastGameState().cityGraph();
    int travelCost = cityGraph.getPrice(location,destination);
    priceLabel.setText(travelCost);
  }
}
