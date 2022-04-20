package de.materna.alchemistpeddler.ui;

import de.materna.alchemistpeddler.gamelogic.Game;
import de.materna.alchemistpeddler.gameuicommunication.PlayerEventGenerator;

public class TUIApp implements PlayerEventGenerator {
  private Game game = new Game(this);
  public static void main(String[] args) {

  }
}
