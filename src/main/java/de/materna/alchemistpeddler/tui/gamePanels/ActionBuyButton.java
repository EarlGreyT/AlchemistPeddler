package de.materna.alchemistpeddler.tui.gamePanels;

import com.googlecode.lanterna.gui2.Borders;
import com.googlecode.lanterna.gui2.Button;
import de.materna.alchemistpeddler.tui.TUIApp;
import java.io.IOException;

public class ActionBuyButton extends Button {
  public ActionBuyButton() {
    super("Buy potions",
        () -> {
          TUIApp.gameWindow.getGamePanel().getDataPanel().setLeftPanel(
              ShopPanel
                  .ShopFactory
                  .getShop(
                      TUIApp.gameController.getLastGameState().playerRecord().location().name()),
              TUIApp.gameController.getLastGameState().playerRecord().location().name()
          );
          TUIApp.gameWindow.getGamePanel().getDataPanel().setRightPanel(new ShopActionPanel(), "What do you want to buy");
        }
    );
  }

}
