package de.materna.alchemistpeddler.gameuicommunication;

/**
 * An Edge in the CityGraph
 * @param from starting point
 * @param dest destination
 * @param cost amount the player has to spend to take the route
 */
public record Route(CITY_NAME from, CITY_NAME dest, int cost) {

}
