package de.materna.alchemistpeddler.tui.gamepanels;

import static de.materna.alchemistpeddler.tui.TUIApp.gameController;

import com.googlecode.lanterna.gui2.Borders;
import com.googlecode.lanterna.gui2.Component;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.Panel;
import de.materna.alchemistpeddler.gameuicommunication.CITY_NAME;
import de.materna.alchemistpeddler.gameuicommunication.PlayerAction;
import de.materna.alchemistpeddler.tui.gamepanels.shops.AbstractShopFactory;
/**
 * Holds Information about the current Location of the Player.
 * <p> eg: In a PotionShop a player will see current prices and the option to buy or sell a specific potion</p>
 */
public class LocationPanel extends Panel {
  Component infoPanel = new Panel();
  Component interactionPanel = new Panel();
  public LocationPanel() {
    super();
    addComponent(infoPanel);
    addComponent(interactionPanel);
    setLayoutManager(new LinearLayout(Direction.HORIZONTAL));
    CITY_NAME playerCity = CITY_NAME.valueOf(
        gameController.getLastGameState().playerRecord().location().name().toUpperCase());
    AbstractShopFactory shopFactory = gameController.shopFactories.get(PlayerAction.BUY);
    Panel buySellPanel = new Panel();
    buySellPanel.setLayoutManager(new LinearLayout(Direction.HORIZONTAL));
    buySellPanel.addComponent(shopFactory.getShopActionPanel());
    buySellPanel.addComponent(shopFactory.getSellShopActionPanel());
    setInfoPanel(shopFactory.getShop(playerCity),
        playerCity.cityName);
    setInteractionPanel(buySellPanel,"");
  }

  public void setInfoPanel(Panel leftPanel, String text) {
    removeComponent(this.infoPanel);
    this.infoPanel = leftPanel.withBorder(Borders.singleLineBevel(text));
    addComponent(0, this.infoPanel);
  }

  public void setInteractionPanel(Panel rightPanel, String text) {
    removeComponent(this.interactionPanel);
    this.interactionPanel = rightPanel.withBorder(Borders.singleLineBevel(text));
    addComponent(1, this.interactionPanel);
  }
}
