package de.materna.alchemistpeddler.gameuicommunication;

public interface PlayerRecordListener {
  default PlayerRecord getPlayerRecord(PlayerRecord playerRecord){
    return playerRecord;
  }
}
