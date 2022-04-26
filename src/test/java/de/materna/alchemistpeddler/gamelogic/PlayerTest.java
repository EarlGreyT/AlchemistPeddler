package de.materna.alchemistpeddler.gamelogic;


import de.materna.alchemistpeddler.gameuicommunication.Potion;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;

class PlayerSellTest {

  public static final Potion TEST_POTION = Potion.FIRE;

  @Test
  void testCantSellMoreThanPlayerHas(){
    //given
    City mockCity = mock(City.class);
    Player testPlayer = new Player(); // player starts with an empty inventory!
    testPlayer.setLocation(mockCity); //player is in a City
    int playerCurrency = testPlayer.getCurrency();
    given(mockCity.price(Potion.FIRE)).willReturn(Potion.FIRE.basePrice); //the city has a price for the Potion
    //when
    testPlayer.sell(Potion.FIRE,20);
    //then
    int expectedQuantity = 0;
    int actualQuantity = testPlayer.getInventoryAsList().get(Potion.FIRE.ordinal());
    int expectedCurrency = playerCurrency;
    int actualCurrency = testPlayer.getCurrency();
    assertEquals(expectedQuantity, actualQuantity);
    assertEquals(expectedCurrency,actualCurrency);
  }

  @Test
  void testPlayerGetsMoneyForEveryPotionSold(){
    //given
    City mockCity = mock(City.class);
    Player testPlayer = new Player();
    testPlayer.setLocation(mockCity); // player is in a City
    int playerCurrency = testPlayer.getCurrency();
    testPlayer.inventory[TEST_POTION.ordinal()] = 5; // the player carries 5 potions with them
    given(mockCity.price(TEST_POTION)).willReturn(TEST_POTION.basePrice); //the city has a price for the Potion
    //when
    testPlayer.sell(TEST_POTION,5);
    int expectedCurrency = playerCurrency + 5*mockCity.price(TEST_POTION);
    int actualCurrency = testPlayer.getCurrency();
    int expectedAmount = 0;
    int actualAmount = testPlayer.inventory[TEST_POTION.ordinal()];
    assertEquals(expectedCurrency,actualCurrency);
    assertEquals(expectedAmount,actualAmount);
  }
}

class PlayerBuyTest {
  public static final Potion TEST_POTION = Potion.FIRE;
  @Test
  void testPlayerCantBuyWithoutMoney(){
    //given
    Player testPlayer = new Player();
    City mockCity = mock(City.class);
    testPlayer.setLocation(mockCity);
    testPlayer.setCurrency(0); // the player has no money
    given(mockCity.price(TEST_POTION)).willReturn(TEST_POTION.basePrice);
    //when
    testPlayer.buy(TEST_POTION,20);
    //then
    int expectedAmount = 0;
    int actualAmount = testPlayer.inventory[TEST_POTION.ordinal()];
    int expectedCurrency = 0;
    int actualCurrency = 0;
    assertEquals(expectedAmount,actualAmount);
    assertEquals(expectedCurrency, actualCurrency);
  }
  @Test
  void testPlayerCanBuyPotion(){
    Player testPlayer = new Player();
    City mockCity = mock(City.class);
    testPlayer.setLocation(mockCity);
    testPlayer.setCurrency(1000); // the player has money
    given(mockCity.price(TEST_POTION)).willReturn(TEST_POTION.basePrice);
    given(mockCity.sellPotion(TEST_POTION,3)).willReturn(3); // the city has 3 potions to sell
    //when
    testPlayer.buy(TEST_POTION,3);
    //then
    int expectedAmount = 3;
    int actualAmount = testPlayer.inventory[TEST_POTION.ordinal()];
    int expectedCurrency = 1000 - 3*TEST_POTION.basePrice;
    int actualCurrency = testPlayer.getCurrency();
    assertEquals(expectedAmount,actualAmount);
    assertEquals(expectedCurrency, actualCurrency);
  }
}