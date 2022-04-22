package de.materna.alchemistpeddler.tui.gamepanels;

import com.googlecode.lanterna.gui2.Panel;
import de.materna.alchemistpeddler.gamelogic.CityRecord;
import java.util.ArrayList;
import java.util.List;

public abstract class ShopPanel extends Panel {
  protected CityRecord cityRecord;
  protected List<BuyablePanel> buyablePanels = new ArrayList<>();
  public void update(){
    for (BuyablePanel buyablePanel : buyablePanels) {
      buyablePanel.update();
    }
  }
}
