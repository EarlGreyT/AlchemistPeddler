package de.materna.alchemistpeddler.tui.gamepanels;


import com.googlecode.lanterna.gui2.LayoutManager;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.Panel;
import de.materna.alchemistpeddler.gamelogic.CityRecord;
import java.util.ArrayList;
import java.util.List;

public abstract class BuyablePanel extends Panel {
  protected NameLabel nameLabel;
  protected List<AmountLabel> amountLabels = new ArrayList<>();
  protected CityRecord cityRecord;
  public BuyablePanel(NameLabel nameLabel, CityRecord cityRecord,AmountLabel... amountLabels){
    this(new LinearLayout());
    this.nameLabel = nameLabel;
    addComponent(nameLabel);
    this.cityRecord = cityRecord;
    for (AmountLabel amountLabel : amountLabels) {
      this.amountLabels.add(amountLabel);
      addComponent(amountLabel);
    }
  }
  public BuyablePanel(LayoutManager layoutManager) {
    super(layoutManager);
  }
  public abstract void update();
}
