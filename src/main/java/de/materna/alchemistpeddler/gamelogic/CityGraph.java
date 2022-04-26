package de.materna.alchemistpeddler.gamelogic;

import de.materna.alchemistpeddler.gameuicommunication.CITY_NAME;
import de.materna.alchemistpeddler.gameuicommunication.Route;
import java.util.EnumMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Builds a fully connected Graph of Cities with random travel costs from each city to each other
 * city
 */
public final class CityGraph {

  private EnumMap<CITY_NAME, EnumMap<CITY_NAME, Route>> routes = new EnumMap<>(CITY_NAME.class);

  public void buildGraph(int minPrice, int maxPrice) {
    for (CITY_NAME from : CITY_NAME.values()) {
      EnumMap<CITY_NAME, Route> destMap = new EnumMap<>(CITY_NAME.class);
      for (CITY_NAME dest : CITY_NAME.values()) {
        if (from.name().equals(dest.name())) {
          destMap.put(dest, new Route(from, dest, 0));
        } else {
          int price = 0;
          try {
            price = getPrice(dest, from);
          } catch (NullPointerException e) {
            price = ThreadLocalRandom.current().nextInt(minPrice, maxPrice);
          } finally {
            destMap.put(dest, new Route(from, dest, price));
          }
        }
      }
      routes.put(from, destMap);
    }
  }

  public int getPrice(CITY_NAME from, CITY_NAME dest) {
    return routes.get(from).get(dest).cost();
  }
}
