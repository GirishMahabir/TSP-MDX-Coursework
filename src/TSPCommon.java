import java.util.Arrays;

public class TSPCommon {
    public static float Distance(City city1, City city2) {
        /*
         * Distance calculates the distance between two cities and returns a float.
         * @param city1: City
         * @param city2: City
         * @return distance: float
         */
        return (float) Math.sqrt(Math.pow((city1.getCity_X_Location() - city2.getCity_X_Location()), 2)
                + Math.pow((city1.getCity_Y_Location() - city2.getCity_Y_Location()), 2));
    }

    public static float[] DistanceArray(City[] cities, City currentCity) {
        /*
         * DistanceArray calculates the distance from the current city to all other cities.
         * @param cities: City[] -> Array of cities.
         * @param startingCity: City -> Starting city.
         * @return float[] -> Array of distances from starting city to each city.
         */
        int numberOfCities = cities.length;
        float[] distanceArray = new float[numberOfCities];

        // Find the position of the current city in the array of cities.
        // Tests Revealed that city 1 is in position 0, city 2 is in position 1, etc.
        int startingCityIndex = Arrays.asList(cities).indexOf(currentCity);

        // Loop through all cities.
        for (int i = 0; i < numberOfCities; i++) {
            // If the city is the current city, set the distance to 0.
            if (i == startingCityIndex) {
                distanceArray[i] = 0;
            } else {
                // Get the distance from the current city to the next city.
                distanceArray[i] = TSPCommon.Distance(currentCity, cities[i]);
            }
        }

        return distanceArray;
    }

    public static City[] RemoveCityFromArray(City[] cities, City cityToRemove) {
        /*
         * RemoveCityFromArray removes a city from an array of cities.
         * @param cities: City[] -> Array of cities.
         * @param cityToRemove: City -> City to remove.
         * @return City[] -> Array of cities without the cityToRemove.
         */

        int numberOfCities = cities.length;
        City[] newCities = new City[numberOfCities - 1];
        int index = 0;

        for (int i = 0; i < numberOfCities; i++) {
            if (cities[i] != cityToRemove) {
                newCities[index] = cities[i];
                index++;
            }
        }

        return newCities;
    }

    public static int ShortestPathIndex(float[] distanceArray) {
        /*
         * ShortestPath finds the shortest path from the current city to the next city.
         * @param distanceArray: float[] -> Array of distances from starting city to each city.
         * @return int -> Index of the next city.
         */

        int shortestPathIndex = 0;
        float shortestPath = distanceArray[0];

        for (int i = 1; i < distanceArray.length; i++) {
            if (distanceArray[i] < shortestPath) {
                shortestPath = distanceArray[i];
                shortestPathIndex = i;
            }
        }

        return shortestPathIndex;
    }
}
