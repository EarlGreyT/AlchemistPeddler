package de.materna.alchemistpeddler.gameuicommunication;

import java.util.Collection;
import java.util.List;

/**
 * Enumeration representing different kinds of potions.
 *
 * <p>Their basePrice attribute is used by a City to calculate the current sales price</p>
 */
public enum Potion implements Buyable {
  MANA(10),
  STAMINA(10),
  HEALTH(15),
  DEXTERITY(20),
  STRENGTH(20),
  INTELLIGENCE(20),
  VIGOR(40),
  VITALITY(40),
  FROST(50),
  FIRE(50);


  private final int basePrice;
  public int getBasePrice(){
    return basePrice;
  }
  Potion(int basePrice) {
    this.basePrice = basePrice;
  }

  @Override
  public Collection getValues() {
    return List.of(values());
  }
}
