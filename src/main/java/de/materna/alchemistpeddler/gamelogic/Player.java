package de.materna.alchemistpeddler.gamelogic;

import java.util.Arrays;

public class Player {
  private City location;
  private int potionCapacity = 10;
  private int currency = 500;
  private final int[] inventory = new int[Potion.values().length];
  private boolean won = false;
  public City getLocation() {
    return location;
  }

  public boolean hasWon() {
    return won;
  }

  public void setWon(boolean won) {
    this.won = won;
  }

  public boolean travel(City dest){
    if (CityGraph.routes.get(dest).get(location).cost()<= currency){
      location = dest;
      currency -= CityGraph.routes.get(dest).get(location).cost();
      return true;
    } return false;
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

  public int[] getInventory() {
    return inventory;
  }

  private boolean checkInventoryCapacity(int amount){
    int current = Arrays.stream(inventory).sum();
    return current+amount <= potionCapacity;
  }

  public int buy(Potion potion, int amount) {
    int price = location.price(potion);
    if (checkInventoryCapacity(amount) && price <= currency) {
      currency -= price;
      inventory[potion.ordinal()] += location.sellPotion(potion, amount);
      return inventory[potion.ordinal()];
    } return 0;
  }

  public int sell(Potion potion, int amount) {
    currency += location.price(potion)*amount;
    return location.buyPotion(potion,amount);

  }

  public void setLocation(City location) {
    this.location = location;
  }
}
