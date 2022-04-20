package de.materna.alchemistpeddler.gamelogic;

import de.materna.alchemistpeddler.gameuicommunication.CityGraph;
import java.util.Arrays;

class Player {
  private City location;
  private int potionCapacity = 10;
  private int currency = 500;
  private final int[] inventory = new int[Potion.values().length];
  private boolean won = false;
  City getLocation() {
    return location;
  }

  boolean hasWon() {
    return won;
  }

  void setWon(boolean won) {
    this.won = won;
  }

  void travel(City dest){
    if (CityGraph.routes.get(dest.getName()).get(location.getName()).cost()<= currency){
      location = dest;
      currency -= CityGraph.routes.get(dest.getName()).get(location.getName()).cost();
    }
  }

  int getPotionCapacity() {
    return potionCapacity;
  }

  void setPotionCapacity(int potionCapacity) {
    this.potionCapacity = potionCapacity;
  }

  int getCurrency() {
    return currency;
  }

  void setCurrency(int currency) {
    this.currency = currency;
  }

  int[] getInventory() {
    return inventory;
  }

  private boolean checkInventoryCapacity(int amount){
    int current = Arrays.stream(inventory).sum();
    return current+amount <= potionCapacity;
  }

  int buy(Potion potion, int amount) {
    int price = location.price(potion);
    if (checkInventoryCapacity(amount) && price <= currency) {
      currency -= price;
      inventory[potion.ordinal()] += location.sellPotion(potion, amount);
      return inventory[potion.ordinal()];
    } return 0;
  }

  int sell(Potion potion, int amount) {
    currency += location.price(potion)*amount;
    return location.buyPotion(potion,amount);

  }

  void setLocation(City location) {
    this.location = location;
  }
}
