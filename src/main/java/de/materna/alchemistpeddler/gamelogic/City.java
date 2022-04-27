package de.materna.alchemistpeddler.gamelogic;

import de.materna.alchemistpeddler.gameuicommunication.CITY_NAME;
import de.materna.alchemistpeddler.gameuicommunication.Potion;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A City keeps track of how many potions are available to buy, how many are produced and consumed on
 * a daily basis. This is used to calculate the local price of a potion
 */
class City {
  public static final int MIN_AMOUNT = 5;
  public static final int MAX_AMOUNT = 50;
  public static final int NUMBER_OF_POTION_KINDS = Potion.values().length;
  public static final int MIN_PRICE = 10;
  public static final int RUNOUT_FACTOR = 2;
  private final int[] potionsBought = new int[NUMBER_OF_POTION_KINDS];
  private final int[] potionProductions = new int[NUMBER_OF_POTION_KINDS];
  private final int[] potionConsumptions = new int[NUMBER_OF_POTION_KINDS];
  private final int[] potionAmounts = new int[NUMBER_OF_POTION_KINDS];
  private final String name;

  City(CITY_NAME name) {
    this.name = name.cityName;
    for (int i = 0; i < potionAmounts.length; i++) {
      potionAmounts[i] = ThreadLocalRandom.current().nextInt(MIN_AMOUNT, MAX_AMOUNT + 1);
      potionProductions[i] = ThreadLocalRandom.current().nextInt(potionAmounts[i]/4, potionAmounts[i]/2);
      potionConsumptions[i] = ThreadLocalRandom.current().nextInt(potionAmounts[i]/4, potionAmounts[i]/2);
    }
  }

  String getName() {
    return name;
  }

  /**
   * Gives the amount of Potions available for purchase.
   *
   * @return int[] potionAmounts with the amount of a specific potion  available at potionAmounts[potion.ordinal()]
   * @see Potion
   */
  int[] getPotionAmounts() {
    return potionAmounts;
  }

  /**
   * Calculates the price of a potion.
   *
   * <p> A potion can't cost less than a predefined MIN_PRICE</p>
   *
   * @param potion a specific Potion kind.
   * @return Price of a Potion at least MIN_PRICE.
   */
   int price(Potion potion) {
    int potionPrice = potion.getBasePrice();
    int potionAmount = potionAmounts[potion.ordinal()];
    int potionProduction = potionProductions[potion.ordinal()];
    int potionConsumption = potionConsumptions[potion.ordinal()];
    int potionAmountDelta = potionProduction - potionConsumption;
    double potionLastsForDays = potionAmountDelta < 0 ?
            potionAmount / Math.max(Math.abs(potionAmountDelta),0.1)
            : 5;
    double productionConsumptionRatio = (double) potionConsumption
        / Math.max (0.1,potionProduction) ;
    potionPrice = (int) Math.floor(potionPrice * (productionConsumptionRatio + 1/Math.max(0.3, potionLastsForDays)));
    return Math.max(potionPrice, MIN_PRICE);
  }

  /**
   * Reduces the amount of Potions in the City and returns the difference between the old and the
   * new value.
   *
   * @param potion Which kind of potion to sell
   * @param amount How many potions to sell
   * @return The Number of potions sold
   * @see Player
   */
  int sellPotion(Potion potion, int amount) {
    if (amount <= potionAmounts[potion.ordinal()]) {
      potionAmounts[potion.ordinal()] = potionAmounts[potion.ordinal()] - amount;
      return amount;
    }
    int amountSold = potionAmounts[potion.ordinal()];
    potionAmounts[potion.ordinal()] = 0;
    return amountSold;
  }

  /**
   * Increases the amount of Potions in the City and returns how mane have been bought.
   *
   * @param potion Which Kind of potion is bought.
   * @param amount How many are bought.
   * @return The Number of Potions bought.
   * @see Player
   */
  int buyPotion(Potion potion, int amount){
    potionsBought[potion.ordinal()] = potionsBought[potion.ordinal()] + Math.max(amount, 0);
    return Math.max(amount, 0);
  }

  /**
   * Increases or decreases the daily production rate of a specific Potion.
   * <p> a production rate is at least 0</p>
   * @param potion The potion which production rate should be changed
   * @param productionDelta The value by which the current production rate is to be modified
   */
  void modifyPotionProduction(Potion potion, int productionDelta) {
    int newProductionValue = potionProductions[potion.ordinal()] + productionDelta;
    potionProductions[potion.ordinal()] = Math.max(newProductionValue, 0);
  }

  /**
   * Increases or decreases the daily consumption rate of a specific Potion.
   * <p> a consumption rate is at least 0</p>
   * @param potion The potion which consumption rate should be changed
   * @param productionDelta The value by which the current consumption rate is to be modified
   */
  void modifyPotionConsumption(Potion potion, int productionDelta) {
    int newConsumptionValue = potionConsumptions[potion.ordinal()] + productionDelta;
    potionConsumptions[potion.ordinal()] = Math.max(newConsumptionValue, 0);
  }
  /**
   * updates the amount of available potions according to the production rate, consumption rate
   * and the amount of potions sold by the player.
   */
  void update(){
    for (int i = 0; i < potionAmounts.length; i++) {
      potionAmounts[i] += potionsBought[i];
      potionsBought[i] = 0;
      potionAmounts[i] += potionProductions[i];
      potionAmounts[i] -= potionConsumptions[i];
      potionAmounts[i] = Math.max(potionAmounts[i], 0);
    }
  }

  int[] getPotionProductions() {
    return potionProductions;
  }

  int[] getPotionConsumptions() {
    return potionConsumptions;
  }

  public static List<Integer> getArrAsList(int[] arr) {
    ArrayList<Integer> productionList = new ArrayList<>(arr.length);
    Arrays.stream(arr).forEach(productionList::add);
    return productionList;
  }

  public List<Integer> getPriceList() {
    ArrayList<Integer> priceList = new ArrayList<>();
    for (Potion potion : Potion.values()) {
      priceList.add(price(potion));
    }
    return priceList;
  }
}
