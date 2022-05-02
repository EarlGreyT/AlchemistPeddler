package de.materna.alchemistpeddler.tui.gamepanels.navigationbuttons;


import static de.materna.alchemistpeddler.tui.TUIApp.gameController;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.dialogs.TextInputDialogBuilder;
import de.materna.alchemistpeddler.gameuicommunication.PlayerAction;
import de.materna.alchemistpeddler.gameuicommunication.PlayerEvent;
import de.materna.alchemistpeddler.tui.TUIApp;
import java.util.regex.Pattern;

public class ActionTakeLoanButton extends Button {

  public ActionTakeLoanButton() {
    super("take a loan",
          () -> {
            String input = new TextInputDialogBuilder()
                .setTitle("How much?")
                .setDescription("Enter the Amount of Gold you wish to burrow")
                .setValidationPattern(Pattern.compile("[0-9]*"), "You didn't enter a single number!")
                .build()
                .showDialog(TUIApp.gui);
            int amount = !input.equals("") ? Integer.parseInt(input) : 0;
            gameController.inform(gameController.getGame(), new PlayerEvent(PlayerAction.TAKELOAN, 0,amount));
          }
      );

  }
}
