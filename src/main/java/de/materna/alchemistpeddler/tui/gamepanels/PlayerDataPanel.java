package de.materna.alchemistpeddler.tui.gamepanels;

import static de.materna.alchemistpeddler.tui.TUIApp.gameController;

import com.googlecode.lanterna.gui2.Borders;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.Panel;
import de.materna.alchemistpeddler.gamelogic.PlayerRecord;
import de.materna.alchemistpeddler.gamelogic.Potion;
import de.materna.alchemistpeddler.tui.gamepanels.shops.shoppanels.AmountLabel;
import de.materna.alchemistpeddler.tui.gamepanels.shops.shoppanels.NameLabel;
import de.materna.alchemistpeddler.tui.gamepanels.shops.shoppanels.PotionPanel;
import java.util.EnumMap;

/**
 * holds information about the player
 * <p> how much  gold does the player have?</p>
 * <p> how much debt does the player have?</p>
 * <p> which city is the current location of the player?</p>
 * <p> which and how much potions does the player carry?</p>
 */
public class PlayerDataPanel extends Panel {
  Panel playerData = new Panel(new LinearLayout(Direction.VERTICAL)
      .setSpacing(1));
  AmountLabel goldLabel = new AmountLabel("Gold");
  AmountLabel debtLabel = new AmountLabel("Debt");
  AmountLabel currentDay = new AmountLabel("Day");
  NameLabel cityLabel = new NameLabel();
  Panel playerPotions = new Panel(new GridLayout(Potion.values().length/3)
      .setHorizontalSpacing(2));
  private final EnumMap<Potion, PotionPanel> potionPanels = new EnumMap<>(Potion.class);
  public PlayerDataPanel(){
    super();
    PlayerRecord playerRecord = gameController.getLastGameState().playerRecord();
    setLayoutManager(new LinearLayout(Direction.HORIZONTAL));
    cityLabel.setText("Location "+playerRecord.location().name());
    goldLabel.setText(playerRecord.currency());
    debtLabel.setText(playerRecord.debt());
    currentDay.setText(0);
    playerData.addComponent(cityLabel);
    playerData.addComponent(goldLabel);
    playerData.addComponent(debtLabel);
    playerData.addComponent(currentDay);
    playerData.addComponent(new AmountLabel("Max. Capacity "+playerRecord.potionCapacity()));
    addComponent(playerPotions.withBorder(Borders.singleLineBevel("Your Potions")));
    addComponent(playerData.withBorder(Borders.singleLineBevel()));
    for (Potion potion : Potion.values()) {
      int potionAmount = playerRecord.inventory().get(potion.ordinal());
      NameLabel nameLabel = new NameLabel(potion.name());
      AmountLabel amountLabel = new AmountLabel(potionAmount);
      AmountLabel priceLabel = new AmountLabel("");
      PotionPanel potionPanel = new PotionPanel(nameLabel, playerRecord.location(),amountLabel,priceLabel);
      potionPanel.removeComponent(potionPanel.getPriceLabel());
      potionPanels.put(potion,potionPanel);
      playerPotions.addComponent(potionPanel);
    }
  }
  public void update(PlayerRecord playerRecord){
    playerRecord.debt();
    goldLabel.setText(playerRecord.currency());
    currentDay.setText(gameController.getLastGameState().gameDay());
    cityLabel.setText("Location "+playerRecord.location().name());
    for (Potion potion : Potion.values()) {
      int potionAmount = playerRecord.inventory().get(potion.ordinal());
      PotionPanel potionPanel = potionPanels.get(potion);
      potionPanel.getAmountLabel().setText(potionAmount);
    }
  }
}
