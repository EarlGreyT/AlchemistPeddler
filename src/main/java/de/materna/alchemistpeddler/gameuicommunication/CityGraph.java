package de.materna.alchemistpeddler.gameuicommunication;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Builds a fully connected Graph of Cities with random travel costs from each city to each other city
 */
public class CityGraph {

  public static EnumMap<CITY_NAMES, EnumMap<CITY_NAMES, Route>> routes = new EnumMap<>(CITY_NAMES.class);
  static {
    for (CITY_NAMES from: CITY_NAMES.values()){
      for (CITY_NAMES dest: CITY_NAMES.values()){
        EnumMap<CITY_NAMES, Route> destMap = new EnumMap<>(CITY_NAMES.class);
        if (from.equals(dest)){
          destMap.put(from,new Route(from,dest,0));
        } else{
          int randomPrice = ThreadLocalRandom.current().nextInt(50,301);
          destMap.put(from, new Route(from,dest,randomPrice));
        }
        routes.put(dest, destMap);
      }
    }
  }
}
