package de.materna.alchemistpeddler.gamelogic;

/**
 * Enumeration representing different kinds of potions.
 *
 * <p>Their basePrice attribute is used by a City to calculate the current sales price</p>
 */
public enum Potion {
  MANA(10),
  HEALTH(15),
  DEXTERITY(20),
  STRENGTH(20),
  INTELLIGENCE(30),
  VIGOR(35),
  VITALITY(40),
  STAMINA(10),
  FIRE(50);

  public final int basePrice;

  Potion(int basePrice) {
    this.basePrice = basePrice;
  }
}
