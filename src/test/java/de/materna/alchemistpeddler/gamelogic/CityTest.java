package de.materna.alchemistpeddler.gamelogic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import de.materna.alchemistpeddler.gameuicommunication.CITY_NAME;
import de.materna.alchemistpeddler.gameuicommunication.Potion;
import org.junit.jupiter.api.Test;

class CityTest {

  public static final CITY_NAME TEST_CITY = CITY_NAME.CAMELOT;
  public static final Potion TEST_POTION = Potion.MANA;
  @Test
  void testPotionPriceIsGreaterThan10() {
    //given
    Potion mockPotion = mock(Potion.class);
    City testCity = new City(TEST_CITY);
    given(mockPotion.getBasePrice()).willReturn(0);
    testCity.getPotionAmounts()[TEST_POTION.ordinal()] = 0;
    testCity.getPotionProductions()[TEST_POTION.ordinal()] =1;
    testCity.getPotionConsumptions()[TEST_POTION.ordinal()] =10;
    //when
    int price = testCity.price(mockPotion);
    int priceForRarePotion = testCity.price(TEST_POTION);
    testCity.getPotionAmounts()[TEST_POTION.ordinal()] = 20;
    testCity.getPotionProductions()[TEST_POTION.ordinal()] =10;
    testCity.getPotionConsumptions()[TEST_POTION.ordinal()] =5;
    int priceForNonRarePotion = testCity.price(TEST_POTION);
    //then
    assertTrue(price>=10);
    assertTrue(priceForRarePotion>=priceForNonRarePotion);
  }

  @Test
  void testSellPotionWontSetAmountToLessThanZero() {
    //given
    City testCity = new City(TEST_CITY);
    int testPotionAmount = testCity.getPotionAmounts()[TEST_POTION.ordinal()];
    int potionsToSell = testPotionAmount+10;
    //when
    testCity.sellPotion(TEST_POTION,potionsToSell);
    //then
    int expectedAmount = 0;
    int actualAmount = testCity.getPotionAmounts()[TEST_POTION.ordinal()];
    assertEquals(expectedAmount,actualAmount);
  }

  @Test
  void testBuyPotionWontIncreaseAmountBeforeUpdate() {
    City testCity = new City(TEST_CITY);
    int expectedAmountBeforeUpdate = testCity.getPotionAmounts()[TEST_POTION.ordinal()];
    //when
    testCity.buyPotion(TEST_POTION,300);
    //then
    int actualAmountBeforeUpdate = testCity.getPotionAmounts()[TEST_POTION.ordinal()];
    assertEquals(expectedAmountBeforeUpdate,actualAmountBeforeUpdate);
    testCity.update();
    int actualAmountAfterUpdate = testCity.getPotionAmounts()[TEST_POTION.ordinal()];
    assertTrue(actualAmountAfterUpdate>actualAmountBeforeUpdate);
  }

  @Test
  void testModifyPotionProductionCantForceNegativeProduction() {
    City testCity = new City(TEST_CITY);
    int testPotionProduction = testCity.getPotionProductions()[TEST_POTION.ordinal()];
    //when
    testCity.modifyPotionProduction(TEST_POTION,-(testPotionProduction + 10));
    //then
    int expectedProduction=0;
    int actualProduction = testCity.getPotionProductions()[TEST_POTION.ordinal()];
    assertEquals(expectedProduction,actualProduction);
  }

  @Test
  void testModifyPotionConsumptionCantForceNegativeConsumption() {
    City testCity = new City(TEST_CITY);
    int testPotionConsumption = testCity.getPotionConsumptions()[TEST_POTION.ordinal()];
    //when
    testCity.modifyPotionConsumption(TEST_POTION,-(testPotionConsumption + 10));
    //then
    int expectedConsumption = 0;
    int actualConsumption = testCity.getPotionConsumptions()[TEST_POTION.ordinal()];
    assertEquals(expectedConsumption,actualConsumption);
  }


}