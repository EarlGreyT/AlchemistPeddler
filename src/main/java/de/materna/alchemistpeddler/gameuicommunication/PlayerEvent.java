package de.materna.alchemistpeddler.gameuicommunication;

public record PlayerEvent(PlayerAction action, String msg, int amount) {
}
