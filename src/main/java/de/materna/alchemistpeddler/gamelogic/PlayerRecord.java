package de.materna.alchemistpeddler.gamelogic;

import de.materna.alchemistpeddler.gamelogic.City;
import de.materna.alchemistpeddler.gamelogic.Player;

public record PlayerRecord(int currency, int potionCapacity, City location, int[] inventory,
                           boolean hasWon) {

  public PlayerRecord(Player player) {
    this(player.getCurrency(), player.getPotionCapacity(), player.getLocation(),
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
