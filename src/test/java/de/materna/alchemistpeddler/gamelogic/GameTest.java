package de.materna.alchemistpeddler.gamelogic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;

import de.materna.alchemistpeddler.gameuicommunication.CITY_NAME;
import de.materna.alchemistpeddler.gameuicommunication.PlayerAction;
import de.materna.alchemistpeddler.gameuicommunication.PlayerEvent;
import de.materna.alchemistpeddler.gameuicommunication.Potion;
import de.materna.alchemistpeddler.tui.GameController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class GameTest {
  public static final Potion TEST_POTION = Potion.MANA;
  public static final CITY_NAME TEST_DESTINATION = CITY_NAME.ATLANTIS;
  public static final String cityName = TEST_DESTINATION.cityName;
  @Test
  void testGetUpdateCallsApropiatePlayerMethods() {
    GameController gameController = mock(GameController.class);
    Game testGame = new Game(gameController);
    Player mockPLayer = spy(Player.class);
    mockPLayer.setLocation(testGame.getPlayer().getLocation());
    mockPLayer.setCityGraph(testGame.getPlayer().getCityGraph());
    testGame.setPlayer(mockPLayer);
    PlayerEvent testBuyEvent = new PlayerEvent(PlayerAction.BUY, TEST_POTION.ordinal(),10);
    PlayerEvent testSellEvent = new PlayerEvent(PlayerAction.SELL, TEST_POTION.ordinal(),10);
    PlayerEvent testTakeLoanEvent = new PlayerEvent(PlayerAction.TAKELOAN, 0,10);
    PlayerEvent testPayLoanEvent = new PlayerEvent(PlayerAction.PAYLOAN, 0,10);
    PlayerEvent testPlayerTravelEvent = new PlayerEvent(PlayerAction.TRAVEL,TEST_DESTINATION.ordinal(),0);
    //when
    testGame.getUpdate(testBuyEvent);
    testGame.getUpdate(testSellEvent);
    testGame.getUpdate(testTakeLoanEvent);
    testGame.getUpdate(testPayLoanEvent);
    testGame.getUpdate(testPlayerTravelEvent);
    //then
    Mockito.verify(mockPLayer, times(1)).buy(TEST_POTION,10);
    Mockito.verify(mockPLayer, times(1)).sell(TEST_POTION,10);
    Mockito.verify(mockPLayer, times(1)).takeLoan(10);
    Mockito.verify(mockPLayer, times(1)).payLoan(10);
    Mockito.verify(mockPLayer,times(1)).travel(testGame.getCities().get(cityName));

  }
}