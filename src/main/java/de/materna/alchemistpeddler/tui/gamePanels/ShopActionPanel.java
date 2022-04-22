package de.materna.alchemistpeddler.tui.gamePanels;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.dialogs.TextInputDialogBuilder;
import de.materna.alchemistpeddler.gamelogic.Potion;
import de.materna.alchemistpeddler.gameuicommunication.PlayerAction;
import de.materna.alchemistpeddler.gameuicommunication.PlayerEvent;
import de.materna.alchemistpeddler.tui.GameController;
import de.materna.alchemistpeddler.tui.TUIApp;
import de.materna.alchemistpeddler.tui.gamePanels.ShopPanel.ShopFactory;
import java.util.regex.Pattern;


public class ShopActionPanel extends Panel {

  public ShopActionPanel() {
    setLayoutManager(new LinearLayout());
    for (Potion potion : Potion.values()) {
      Button buyPotionButton = new Button("buy " + potion.name(),
          () -> {
            String input = new TextInputDialogBuilder()
                .setTitle("How much?")
                .setDescription("Enter the number of Potions you want to buy")
                .setValidationPattern(Pattern.compile("[0-9]*"), "You didn't enter a single number!")
                .build()
                .showDialog(TUIApp.gui);
            int amount = !input.equals("") ? Integer.parseInt(input) : 0;
            GameController gameController =TUIApp.gameController;
            gameController.inform(GameController.subscribers.get(0), new PlayerEvent(PlayerAction.BUY, potion.ordinal(),amount));
            String city = gameController.getLastGameState().playerRecord().location().name();
            TUIApp.gameWindow.getGamePanel().getDataPanel().setLeftPanel(ShopFactory.getShop(city), city);
          }
      );
      addComponent(buyPotionButton);
    }
  }
}
