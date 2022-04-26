package de.materna.alchemistpeddler.gameuicommunication;

import de.materna.alchemistpeddler.gameuicommunication.CITY_NAME;
import de.materna.alchemistpeddler.gameuicommunication.Route;
import java.util.EnumMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Builds a fully connected Graph of Cities with random travel costs from each city to each other city
 */
public final class CityGraph {

  public  EnumMap<CITY_NAME, EnumMap<CITY_NAME, Route>> routes  = new EnumMap<>(CITY_NAME.class);
  public  void buildGraph(){
    for (CITY_NAME from: CITY_NAME.values()){
      EnumMap<CITY_NAME, Route> destMap = new EnumMap<>(CITY_NAME.class);
      for (CITY_NAME dest: CITY_NAME.values()){
        if (from.name().equals(dest.name())){
          destMap.put(dest,new Route(from,dest,0));
        } else{
          int randomPrice = ThreadLocalRandom.current().nextInt(50,301);
          destMap.put(dest, new Route(from,dest,randomPrice));
        }
      }
      routes.put(from, destMap);
    }
  }
}
