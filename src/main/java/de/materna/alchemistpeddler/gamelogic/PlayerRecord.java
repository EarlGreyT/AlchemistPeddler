package de.materna.alchemistpeddler.gamelogic;
import java.util.List;

public record PlayerRecord(int currency, int debt, int potionCapacity, CityRecord location, List<Integer> inventory,
                           boolean hasWon, boolean hasLost) {

  public PlayerRecord(Player player) {
    this(player.getCurrency(), player.getDebt(), player.getPotionCapacity(), new CityRecord(player.getLocation()),
        player.getInventoryAsList(),
        player.hasWon(), player.hasLost());
  }

}
