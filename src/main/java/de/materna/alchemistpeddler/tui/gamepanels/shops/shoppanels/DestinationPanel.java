package de.materna.alchemistpeddler.tui.gamepanels.shops.shoppanels;


import de.materna.alchemistpeddler.gamelogic.CityRecord;
import de.materna.alchemistpeddler.gameuicommunication.CITY_NAME;
import de.materna.alchemistpeddler.gameuicommunication.CityGraph;
import de.materna.alchemistpeddler.tui.gamepanels.AmountLabel;
import de.materna.alchemistpeddler.tui.gamepanels.NameLabel;
import de.materna.alchemistpeddler.tui.gamepanels.shops.BuyablePanel;


public class DestinationPanel extends BuyablePanel {
  private AmountLabel priceLabel;
  public DestinationPanel(NameLabel nameLabel, CityRecord cityRecord, AmountLabel priceLabel) {
    super(nameLabel, cityRecord, priceLabel);
    this.priceLabel = priceLabel;
  }


  @Override
  public void update() {
    CITY_NAME location = CITY_NAME.valueOf(cityRecord.name().toUpperCase());
    CITY_NAME destination = CITY_NAME.valueOf(nameLabel.getText().toUpperCase());
    int travelCost = CityGraph.routes.get(location).get(destination).cost();
    priceLabel.setText(travelCost);
  }
}
