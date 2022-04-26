package de.materna.alchemistpeddler.gamelogic;

import de.materna.alchemistpeddler.gameuicommunication.CITY_NAME;
import de.materna.alchemistpeddler.gameuicommunication.Potion;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Player {

  private City location;
  private int potionCapacity = 10;
  private int currency = 500;
  final int[] inventory = new int[Potion.values().length];
  private boolean won = false;
  private int debt = 0;
  private boolean lost = false;
  private CityGraph cityGraph;

  Player() {
  }

  Player(CityGraph cityGraph) {
    this();
    this.cityGraph = cityGraph;
  }

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
    int travelCost = cityGraph.getPrice(locationName,destName);
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



  int getCurrency() {
    return currency;
  }

  void setCurrency(int currency) {
    this.currency = currency;
  }


  private boolean checkInventoryCapacity(int amount) {
    int current = Arrays.stream(inventory).sum();
    return current + amount <= potionCapacity;
  }

  /**
   * adds the specified amount of a potion to the inventory, if the player can pay for it.
   * @param potion
   * @param amount
   */
  void buy(Potion potion, int amount) {
    int price = location.price(potion);
    if (checkInventoryCapacity(amount) && (price*amount) <= currency) {
      int amountBought = location.sellPotion(potion, amount);
      currency -= price*amountBought;
      inventory[potion.ordinal()] += amountBought;
    }
  }

  /**
   * decreases the amount of a potion in the inventory and raises the amount of currency the player,
   * according to the potion price in the current city.
   * @param potion
   * @param amount
   * @return
   */
  void sell(Potion potion, int amount) {
    int amountToSell = Math.min(amount,inventory[potion.ordinal()]);
    int amountSold = location.buyPotion(potion, amountToSell);
    currency += location.price(potion) * amountSold;
    inventory[potion.ordinal()] -= amountSold;
  }

  void setLocation(City location) {
    this.location = location;
  }

  public List<Integer> getInventoryAsList() {
    ArrayList<Integer> inventoryList = new ArrayList<>(inventory.length);
    Arrays.stream(inventory).forEach(inventoryList::add);
    return inventoryList;
  }

  public CityGraph getCityGraph() {
    return cityGraph;
  }

  public void setCityGraph(CityGraph cityGraph) {
    this.cityGraph = cityGraph;
  }

  public int getDebt() {
    return debt;
  }
}
