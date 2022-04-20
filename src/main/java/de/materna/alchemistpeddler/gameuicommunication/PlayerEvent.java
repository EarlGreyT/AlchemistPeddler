package de.materna.alchemistpeddler.gameuicommunication;

public record PlayerEvent(PlayerAction action, int what, int amount) {
}
