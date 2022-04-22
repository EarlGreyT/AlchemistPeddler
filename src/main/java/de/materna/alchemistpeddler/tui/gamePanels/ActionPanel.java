package de.materna.alchemistpeddler.tui.gamePanels;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.TextColor.ANSI;
import com.googlecode.lanterna.bundle.LanternaThemes;
import com.googlecode.lanterna.graphics.Theme;
import com.googlecode.lanterna.graphics.ThemeDefinition;
import com.googlecode.lanterna.graphics.ThemeStyle;
import com.googlecode.lanterna.gui2.Borders;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Component;
import com.googlecode.lanterna.gui2.ComponentRenderer;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.WindowDecorationRenderer;
import com.googlecode.lanterna.gui2.WindowPostRenderer;
import de.materna.alchemistpeddler.gameuicommunication.PlayerAction;
import java.util.ArrayList;
import java.util.EnumSet;

public class ActionPanel extends Panel {
  private ArrayList<Button> myLabels = new ArrayList<>();
  public ActionPanel() {
    super();
    setFillColorOverride(ANSI.BLACK);
    setLayoutManager(new LinearLayout(Direction.HORIZONTAL));
    for (PlayerAction action : PlayerAction.values()) {
      switch (action){
        case BUY -> {
          ActionBuyButton actionButton = new ActionBuyButton();
          actionButton.addTo(this);
          myLabels.add(actionButton);
        }
        default -> {
          Button actionButton = new Button(action.name());
          actionButton.addTo(this);
          myLabels.add(actionButton);
        }
      }

    }


  }


  public void setMyLabel(String labelText, int labelIndex) {
    myLabels.get(labelIndex).setLabel(labelText);
  }
}
