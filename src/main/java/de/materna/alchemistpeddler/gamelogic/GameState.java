package de.materna.alchemistpeddler.gamelogic;

public class GameState {
  public final CityRecord[] cityRecords;
  public final PlayerRecord playerRecord;
  public final int gameDay;
  public GameState(CityRecord[] cityRecords,
      PlayerRecord playerRecord, int gameDay) {
    this.gameDay = gameDay;
    this.cityRecords = cityRecords;
    this.playerRecord = playerRecord;
  }
}
