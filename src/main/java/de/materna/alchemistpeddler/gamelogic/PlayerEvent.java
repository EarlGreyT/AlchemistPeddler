package de.materna.alchemistpeddler.gamelogic;

public record PlayerEvent(PlayerAction action, String msg, int amount) {
}
