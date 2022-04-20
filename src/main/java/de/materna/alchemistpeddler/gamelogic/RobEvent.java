package de.materna.alchemistpeddler.gamelogic;

import java.util.concurrent.ThreadLocalRandom;

public class RobEvent extends GameEvent {
  private final Player target;
  public static final int UPPERBOUND = 26;
  public static final int LOWERBOUND = 0;
  public RobEvent(Player target) {
    this.target = target;
  }

  @Override
  GameEvent process() {
    double stealPercent = ThreadLocalRandom.current().nextDouble(LOWERBOUND, UPPERBOUND);
    int targetCurrency = (int) (target.getCurrency()*(1.0 - stealPercent));
    msg = String.valueOf(targetCurrency);
    target.setCurrency(targetCurrency);
    return this;
  }
}
