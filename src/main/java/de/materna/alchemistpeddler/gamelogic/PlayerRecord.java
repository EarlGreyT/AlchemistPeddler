package de.materna.alchemistpeddler.gamelogic;

import de.materna.alchemistpeddler.gamelogic.City;
import de.materna.alchemistpeddler.gamelogic.Player;

public record PlayerRecord(int currency, int potionCapacity, CityRecord location, int[] inventory,
                           boolean hasWon) {

  public PlayerRecord(Player player) {
    this(player.getCurrency(), player.getPotionCapacity(), new CityRecord(player.getLocation()),
        player.getInventory(),
        player.hasWon());
  }

  @Override
  public boolean equals(Object obj) {
    throw new RuntimeException(new NoSuchMethodException());
  }
  @Override
  public String toString(){
    throw new RuntimeException(new NoSuchMethodException());
  }

  @Override
  public int hashCode() {
    throw new RuntimeException(new NoSuchMethodException());
  }
}
