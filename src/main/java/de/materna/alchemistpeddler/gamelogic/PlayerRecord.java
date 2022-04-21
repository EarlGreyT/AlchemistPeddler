package de.materna.alchemistpeddler.gamelogic;
import java.util.List;

public record PlayerRecord(int currency, int potionCapacity, CityRecord location, List<Integer> inventory,
                           boolean hasWon) {

  public PlayerRecord(Player player) {
    this(player.getCurrency(), player.getPotionCapacity(), new CityRecord(player.getLocation()),
        player.getInventoryAsList(),
        player.hasWon());
  }

}
