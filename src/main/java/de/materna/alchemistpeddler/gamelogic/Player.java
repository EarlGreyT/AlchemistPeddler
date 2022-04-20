package de.materna.alchemistpeddler.gamelogic;

import java.util.Arrays;

public class Player implements  PlayerEventGenerator{
  private City location;
  private int potionCapacity = 10;
  private int currency = 500;
  private int[] inventory = new int[Potion.values().length];
  public City getLocation() {
    return location;
  }

  public void setLocation(City location) {
    this.location = location;
  }

  public int getPotionCapacity() {
    return potionCapacity;
  }

  public void setPotionCapacity(int potionCapacity) {
    this.potionCapacity = potionCapacity;
  }

  public int getCurrency() {
    return currency;
  }

  public void setCurrency(int currency) {
    this.currency = currency;
  }

  private boolean checkInventoryCapacity(int amount){
    int current = Arrays.stream(inventory).sum();
    return current+amount <= potionCapacity;
  }

}
