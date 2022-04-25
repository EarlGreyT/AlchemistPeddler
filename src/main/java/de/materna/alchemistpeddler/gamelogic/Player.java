package de.materna.alchemistpeddler.gamelogic;

import de.materna.alchemistpeddler.gameuicommunication.CITY_NAME;
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
  private boolean lost = false;

  City getLocation() {
    return location;
  }

  boolean hasLost() {
    return lost;
  }

  boolean hasWon() {
    return won;
  }

  void setWon(boolean won) {
    this.won = won;
  }

  void setLost(boolean lost) {
    this.lost = lost;
  }

  /**
   * Travels to another city, if the player can afford the trip.
   * @param dest - the city the player wishes to go to
   */
  void travel(City dest) {
    CITY_NAME locationName = CITY_NAME.valueOf(location.getName().toUpperCase());
    CITY_NAME destName = CITY_NAME.valueOf(dest.getName().toUpperCase());
    int travelCost = CityGraph.routes.get(locationName).get(destName).cost();
    if (travelCost <= currency) {
      location = dest;
      currency = currency - travelCost;
    }
  }

  /**
   * increases the amount of currency a player has, but also increases his debt
   * @param amount
   */
  void takeLoan(int amount) {
    currency = currency + amount;
    debt= debt + amount;
  }

  /**
   * decreases the amount of currency a player has and decreases his debt,
   * if he can spend that much money.
   * @param amount
   */
  void payLoan(int amount) {
    if (currency >= amount) {
      debt -= amount;
      currency -= amount;
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

  private boolean checkInventoryCapacity(int amount) {
    int current = Arrays.stream(inventory).sum();
    return current + amount <= potionCapacity;
  }

  /**
   * adds the specified amount of a potion to the inventory, if the player can pay for it.
   * @param potion
   * @param amount
   * @return
   */
  int buy(Potion potion, int amount) {
    int price = location.price(potion);
    if (checkInventoryCapacity(amount) && price <= currency) {
      currency -= price;
      inventory[potion.ordinal()] += location.sellPotion(potion, amount);
      return inventory[potion.ordinal()];
    }
    return 0;
  }

  /**
   * decreases the amount of a potion in the inventory and raises the amount of currency the player,
   * according to the potion price in the current city.
   * @param potion
   * @param amount
   * @return
   */
  int sell(Potion potion, int amount) {
    if (inventory[potion.ordinal()] >= amount) {
      currency += location.price(potion) * amount;
      inventory[potion.ordinal()] -= location.buyPotion(potion, amount);
    }
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
