package de.materna.alchemistpeddler.tui.gamepanels;

import com.googlecode.lanterna.TextColor.ANSI;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.Panel;
import de.materna.alchemistpeddler.gameuicommunication.PlayerAction;
import de.materna.alchemistpeddler.tui.gamepanels.navigationbuttons.ActionBuyButton;
import de.materna.alchemistpeddler.tui.gamepanels.navigationbuttons.ActionPayLoanButton;
import de.materna.alchemistpeddler.tui.gamepanels.navigationbuttons.ActionTakeLoanButton;
import de.materna.alchemistpeddler.tui.gamepanels.navigationbuttons.ActionTravelButton;

/**
 * This holds buttons for every action a player can take
 */
public class ActionPanel extends Panel {
  public ActionPanel() {
    super();
    setFillColorOverride(ANSI.BLACK);
    setLayoutManager(new LinearLayout(Direction.HORIZONTAL));
    for (PlayerAction action : PlayerAction.values()) {
      switch (action){
        case BUY -> {
          ActionBuyButton actionButton = new ActionBuyButton();
          actionButton.addTo(this);
        }
        case TRAVEL -> {
          ActionTravelButton travelButton = new ActionTravelButton();
          travelButton.addTo(this);
        }
        case PAYLOAN -> {
          ActionPayLoanButton payLoanButton = new ActionPayLoanButton();
          payLoanButton.addTo(this);
        }
        case TAKELOAN -> {
          ActionTakeLoanButton takeLoanButton = new ActionTakeLoanButton();
          takeLoanButton.addTo(this);
        }
      }
    }
  }
}
