package de.materna.alchemistpeddler.tui.gamepanels.shops;

import com.googlecode.lanterna.gui2.Panel;
import de.materna.alchemistpeddler.gamelogic.CityRecord;
import java.util.ArrayList;
import java.util.List;

/**
 * Every shop is made up of Panels that hold information about the sold goods.
 */
public abstract class ShopPanel extends Panel {
  protected CityRecord cityRecord;
  protected List<BuyablePanel> buyablePanels = new ArrayList<>();
  public void update(){
    for (BuyablePanel buyablePanel : buyablePanels) {
      buyablePanel.update();
    }
  }
}
