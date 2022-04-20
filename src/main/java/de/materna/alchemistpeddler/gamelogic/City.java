package de.materna.alchemistpeddler.gamelogic;

import java.util.concurrent.ThreadLocalRandom;

/**
 * City keeps track of how many potions are available to buy, how many are produced and consumed on
 * a daily basis. This is used to calculate the local price of a potion
 */
public class City {
  public static final int MIN_AMOUNT = 5;
  public static final int MAX_AMOUNT = 200;
  public static final int NUMBER_OF_POTION_KINDS = Potion.values().length;
  public static final int MIN_PRICE = 10;
  public static final int RUNOUT_FACTOR = 2;
  private final int[] potionProductions = new int[NUMBER_OF_POTION_KINDS];
  private final int[] potionConsumptions = new int[NUMBER_OF_POTION_KINDS];
  private final int[] potionAmounts = new int[NUMBER_OF_POTION_KINDS];
  private final String name;

  public City(String name) {
    this.name = name;
    for (int i = 0; i < potionAmounts.length; i++) {
      potionAmounts[i] = ThreadLocalRandom.current().nextInt(MIN_AMOUNT, MAX_AMOUNT + 1);
      potionProductions[i] = ThreadLocalRandom.current().nextInt(potionAmounts[i]/4, potionAmounts[i]/2);
      potionConsumptions[i] = ThreadLocalRandom.current().nextInt(potionAmounts[i]/4, potionAmounts[i]/2);
    }
  }

  public String getName() {
    return name;
  }

  /**
   * Gives the amount of Potions available for purchase.
   *
   * @return int[] potionAmounts with the amount of potion available at potionAmounts[potion.ordinal()]
   * @see Potion
   */
  public int[] getPotionAmounts() {
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
  public int price(Potion potion) {
    int potionPrice = potion.basePrice;
    int potionAmount = potionAmounts[potion.ordinal()];
    int potionProduction = potionProductions[potion.ordinal()];
    int potionConsumption = potionConsumptions[potion.ordinal()];
    int potionAmountDelta = potionProduction - potionConsumption;
    int potionLastsForDays = potionAmountDelta < 0 ?
            (Math.floorDiv(potionAmount, Math.abs(potionAmountDelta)))
            : Integer.MAX_VALUE;
    double productionConsumptionRatio = (double) potionConsumption
        / (double) potionProduction;
    potionPrice = (int) Math.floor(potionPrice * productionConsumptionRatio);
    if (potionLastsForDays <= 2) {
      potionPrice = potionPrice * RUNOUT_FACTOR;
    }
    return Math.max(potionPrice, MIN_PRICE);
  }

  /**
   * Reduces the amount of Potions in the City and returns the difference between the old and the
   * new value.
   *
   * @param potion Which kind of potion to sell
   * @param amount How many potions to sell
   * @return The Number of potions sold
   */
  public int sellPotion(Potion potion, int amount) {
    if (amount <= potionAmounts[potion.ordinal()]) {
      potionAmounts[potion.ordinal()] -= amount;
      return amount;
    }
    int amountSold = amount - potionAmounts[potion.ordinal()];
    potionAmounts[potion.ordinal()] = 0;
    return amountSold;
  }

  public int modifyPotionProduction(Potion potion, int productionDelta) {
    int newProductionValue = potionProductions[potion.ordinal()] + productionDelta;
    potionProductions[potion.ordinal()] = Math.max(newProductionValue, 0);
    return potionProductions[potion.ordinal()];
  }

  public int modifyPotionConsumption(Potion potion, int productionDelta) {
    int newConsumptionValue = potionConsumptions[potion.ordinal()] + productionDelta;
    potionConsumptions[potion.ordinal()] = Math.max(newConsumptionValue, 0);
    return potionConsumptions[potion.ordinal()];
  }

  public void update(){
    for (int i = 0; i < potionAmounts.length; i++) {
      potionAmounts[i] += potionProductions[i];
      potionAmounts[i] -= potionConsumptions[i];
      potionAmounts[i] = Math.max(potionAmounts[i], 0);
    }
  }

  public int[] getPotionProductions() {
    return potionProductions;
  }

  public int[] getPotionConsumptions() {
    return potionConsumptions;
  }
}
