package de.materna.alchemistpeddler.gamelogic;

import java.util.List;

public record GameState(
    List<CityRecord> cityRecords,
    PlayerRecord playerRecord, int gameDay, int lastDay) {

}
