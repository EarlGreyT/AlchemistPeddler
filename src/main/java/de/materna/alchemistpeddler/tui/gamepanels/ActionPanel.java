package de.materna.alchemistpeddler.tui.gamepanels;

import com.googlecode.lanterna.TextColor.ANSI;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.Panel;
import de.materna.alchemistpeddler.gameuicommunication.PlayerAction;
import java.util.ArrayList;

public class ActionPanel extends Panel {
  private ArrayList<Button> buttons = new ArrayList<>();
  public ActionPanel() {
    super();
    setFillColorOverride(ANSI.BLACK);
    setLayoutManager(new LinearLayout(Direction.HORIZONTAL));
    for (PlayerAction action : PlayerAction.values()) {
      switch (action){
        case BUY -> {
          ActionBuyButton actionButton = new ActionBuyButton();
          actionButton.addTo(this);
          buttons.add(actionButton);
        }
        case TRAVEL -> {
          ActionTravelButton travelButton = new ActionTravelButton();
          travelButton.addTo(this);
          buttons.add(travelButton);
        }
        default -> {
          Button actionButton = new Button(action.name());
          actionButton.addTo(this);
          buttons.add(actionButton);
        }
      }

    }


  }


  public void setMyLabel(String labelText, int labelIndex) {
    buttons.get(labelIndex).setLabel(labelText);
  }
}
