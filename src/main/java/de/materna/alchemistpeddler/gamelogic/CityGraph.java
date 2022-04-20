package de.materna.alchemistpeddler.gamelogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

class CityGraph {
  record Route(CITY_NAMES from, CITY_NAMES dest, int cost){}
  static HashMap<String, HashMap<String, Route>> routes = new HashMap<>();
  static {
    for (CITY_NAMES from: CITY_NAMES.values()){
      for (CITY_NAMES dest: CITY_NAMES.values()){
        HashMap<String, Route> destMap = new HashMap<>();
        if (from.cityName.equals(dest.cityName)){
          destMap.put(from.cityName,new Route(from,dest,0));
        } else{
          int randomPrice = ThreadLocalRandom.current().nextInt(50,301);
          destMap.put(from.cityName, new Route(from,dest,randomPrice));
        }
        routes.put(dest.cityName, destMap);
      }
    }
  }
}