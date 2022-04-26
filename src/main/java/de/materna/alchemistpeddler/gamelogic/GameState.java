package de.materna.alchemistpeddler.gamelogic;

import java.util.List;

/**
 *  Representation of the State of the Game, which can be sent to the UI
 * @param cityRecords
 * @param playerRecord
 * @param gameDay
 * @param lastDay
 * @see de.materna.alchemistpeddler.gameuicommunication.GameStateListener
 */
public record GameState(
    List<CityRecord> cityRecords,
    PlayerRecord playerRecord, int gameDay, int lastDay, CityGraph cityGraph) {

}
