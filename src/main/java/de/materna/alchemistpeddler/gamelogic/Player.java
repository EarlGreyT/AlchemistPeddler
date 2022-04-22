package de.materna.alchemistpeddler.gamelogic;

import de.materna.alchemistpeddler.gameuicommunication.CityGraph;
import de.materna.alchemistpeddler.gameuicommunication.Potion;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Player {
  private City location;
  private int potionCapacity = 10;
  private int currency = 500;
  private final int[] inventory = new int[Potion.values().length];
  private boolean won = false;
  private int debt = 0;

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
  void takeLoan(int amount){
    debt++;
  }
  int payLoan(int amount){
    if (currency >= amount){
      debt -= amount;
      currency -= amount;
      return amount;
    } return 0;
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
    inventory[potion.ordinal()] -= location.buyPotion(potion,amount);
    return inventory[potion.ordinal()];
  }

  void setLocation(City location) {
    this.location = location;
  }

  public List<Integer> getInventoryAsList() {
    ArrayList<Integer> inventoryList = new ArrayList<>(inventory.length);
    Arrays.stream(inventory).forEach(inventoryList::add);
    return inventoryList;
  }

  public int getDebt() {
    return debt;
  }
}
