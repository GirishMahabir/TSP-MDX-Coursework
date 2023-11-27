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
            float[] distanceArray = TSPCommon.DistanceArray(cities, startingCity); // Get the distance array for the current city.
            int shortestPathIndex = TSPCommon.ShortestPathIndex(distanceArray); // Get the shortest path index.
            City nextCity = cities[shortestPathIndex]; // Get the next city.
            // Add the next city to the path
            Path.add(nextCity.getCity_ID());

            // Remove the next city from the array of cities.
            cities = TSPCommon.RemoveCityFromArray(cities, nextCity);
            // Set the next city as the starting city.
            startingCity = nextCity;

            // Add the distance to the total distance.
            distances += distanceArray[shortestPathIndex];
        }

        // Add the starting city to the path
        Path.add(tempCity.getCity_ID());
        // Add the distance from the last city to the starting city.
        distances += TSPCommon.Distance(tempCity, startingCity);

        // Convert the array list to an array of ints.
        int[] tour = Path.stream().mapToInt(i -> i).toArray();

        return new AlgoResultDS(tour, distances);
    }

    public static AlgoResultDS main(City[] cities, City startingCity) {
        return TourDistance(cities, startingCity);
    }
}
