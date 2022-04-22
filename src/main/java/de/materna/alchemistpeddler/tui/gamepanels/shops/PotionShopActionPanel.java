package de.materna.alchemistpeddler.tui.gamepanels.shops;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.dialogs.TextInputDialogBuilder;
import de.materna.alchemistpeddler.gamelogic.PlayerRecord;
import de.materna.alchemistpeddler.gameuicommunication.CITY_NAME;
import de.materna.alchemistpeddler.gameuicommunication.Potion;
import de.materna.alchemistpeddler.gameuicommunication.PlayerAction;
import de.materna.alchemistpeddler.gameuicommunication.PlayerEvent;
import de.materna.alchemistpeddler.tui.GameController;
import de.materna.alchemistpeddler.tui.TUIApp;
import de.materna.alchemistpeddler.tui.gamepanels.shops.ShopFactory;
import java.util.regex.Pattern;


public class PotionShopActionPanel extends Panel {

  public PotionShopActionPanel() {
    setLayoutManager(new LinearLayout().setSpacing(1));
    for (Potion potion : Potion.values()) {
      Button buyPotionButton = new Button("buy " + potion.name(),
          () -> {
            String input = new TextInputDialogBuilder()
                .setTitle("How much?")
                .setDescription("Enter the number of Potions you want to buy?")
                .setValidationPattern(Pattern.compile("[0-9]*"), "You didn't enter a single number!")
                .build()
                .showDialog(TUIApp.gui);
            int amount = !input.equals("") ? Integer.parseInt(input) : 0;
            GameController gameController =TUIApp.gameController;
            PlayerRecord playerRecord = gameController.getLastGameState().playerRecord();
            gameController.inform(GameController.subscribers.get(0), new PlayerEvent(PlayerAction.BUY, potion.ordinal(),amount));
            CITY_NAME city = CITY_NAME.valueOf(playerRecord.location().name().toUpperCase());
            TUIApp.gameWindow.getGamePanel().getLocationPanel().setInfoPanel(ShopFactory.getPotionShop(city), city.cityName);
          }
      );
      addComponent(buyPotionButton);
    }
  }
}