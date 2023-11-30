import java.util.Arrays;
import java.util.ArrayList;

public class TSPDijkstraAlgo {
    public static AlgoResultDS TourDistance(City[] cities, City startingCity) {
        /*
         * TourDistance calculates the total distance of the tour, Traveling from one city to another.
         * @param cities: City[] -> Array of cities.
         * @param startingCity: City -> Starting city.
         * @return AlgoResultDS -> Object containing the shortest paths and distances.
         */

        // Store starting city in temp variable.
        City tempCity = startingCity;

        // Array List to store the int (IDs)
        ArrayList<Integer> Path = new ArrayList<Integer>();
        // Add the starting city to the path
        float distances = 0;

        /*
            * Note the array that will be passed to DistanceArray needs to be adapted every time.
            * Visited cities need to be removed from the array.
         */
        while (cities.length >= 1) {
            float[] distanceArray = DistanceArray(cities, startingCity); // Get the distance array for the current city.
            int shortestPathIndex = ShortestPathIndex(distanceArray); // Get the shortest path index.
            City nextCity = cities[shortestPathIndex]; // Get the next city.
            // Add the next city to the path
            Path.add(nextCity.getCityId());

            // Remove the next city from the array of cities.
            cities = RemoveCityFromArray(cities, nextCity);
            // Set the next city as the starting city.
            startingCity = nextCity;

            // Add the distance to the total distance.
            distances += distanceArray[shortestPathIndex];
        }

        // Add the starting city to the path
        Path.add(tempCity.getCityId());
        // Add the distance from the last city to the starting city.
        distances += TSPCommon.Distance(tempCity, startingCity);

        // Convert the array list to an array of ints.
        int[] tour = Path.stream().mapToInt(i -> i).toArray();

        return new AlgoResultDS(tour, distances);
    }

    public static AlgoResultDS main(City[] cities, City startingCity) {
        return TourDistance(cities, startingCity);
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
