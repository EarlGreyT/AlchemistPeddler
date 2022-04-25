package de.materna.alchemistpeddler.gameuicommunication;

/**
 *
 * @param action - what Kind of action should the player do?
 * @param what - the Resource the player should interact with encoded as the ordinal of an enum
 * @param amount - how many resources should be bought/sold...
 */
public record PlayerEvent(PlayerAction action, int what, int amount) {
}
