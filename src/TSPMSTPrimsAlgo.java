import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class TSPMSTPrimsAlgo {
    /*
     * Implements Prim's algorithm to find the Minimum Spanning Tree (MST) for a network of cities.
     * It uses a priority queue to efficiently manage edges and their costs during MST construction.
     *
     * Methods:
     * - primsAlgorithm: Computes the MST from a given array of cities and a starting city.
     * Usage:
     * Pass an array of City objects and a starting City to primsAlgorithm to get the MST.
     */

    public static AlgoResultDS TSPMSTPrimsAlgo(City[] cities, City startingCity) {
        /*
         * Computes the MST from a given array of cities and a starting city.
         * @param cities: City[] -> Array of cities.
         * @param startingCity: City -> Starting city.
         */
        List<City> visitedCitiesMST = new ArrayList<City>(); // List of visited cities.
        PriorityQueue<EdgeDS> priorityQueue = new PriorityQueue<EdgeDS>(); // Priority queue to store edges.

        // Save initial starting city.
        City initialStartingCity = startingCity;

        // Initialize the priority queue with edges from the starting city.
        for (City city : cities) {
            if (!city.equals(startingCity)) {
                priorityQueue.add(new EdgeDS(city, TSPCommon.Distance(startingCity, city)));
            }
        }

        // Add the starting city to the visited cities list.
        visitedCitiesMST.add(startingCity);


        // Process edges from the priority queue until it's empty or all cities are visited.
        while (!priorityQueue.isEmpty() && visitedCitiesMST.size() < cities.length) {
            EdgeDS currentEdge = priorityQueue.poll();

            // Check if the city has not been visited yet.
            if (!visitedCitiesMST.contains(currentEdge.getDestination())) {
                visitedCitiesMST.add(currentEdge.getDestination());

                // Delete edges that have cities that have already been visited.
                for (int i = 0; i < priorityQueue.size(); i++) {
                    EdgeDS edge = priorityQueue.poll();
                    if (visitedCitiesMST.contains(edge.getDestination())) {
                        priorityQueue.remove(edge);
                    }
                }

                // Get all cities from the current edge.
                for (City city : cities) {
                    // Add new edges from the newly added city to the priority queue.
                    if (!visitedCitiesMST.contains(city)) {
                        priorityQueue.add(new EdgeDS(city, TSPCommon.Distance(currentEdge.getDestination(), city)));
                    }
                }
            }
        }

        // Add the starting city to the visited cities list.
        visitedCitiesMST.add(initialStartingCity);
        // Return the MST as an AlgoResultDS object.
        return new AlgoResultDS(convertCityListToArray(visitedCitiesMST),
                calculateTour(convertCityListToArray(visitedCitiesMST), visitedCitiesMST));
    }

    public static float calculateTour(int[] tour, List<City> visitedCitiesMST) {
        /*
         * Calculates the total distance of a tour.
         * @param tour: int[] -> Array of city ids.
         * @param visitedCitiesMST: List<City> -> List of cities.
         * @return float -> Total distance of the tour.
         */
        float distance = 0;
        for (int i = 0; i < tour.length - 1; i++) {
            distance += TSPCommon.Distance(visitedCitiesMST.get(i), visitedCitiesMST.get(i + 1));
        }

        return distance;
    }

    public static int[] convertCityListToArray(List<City> cityList) {
        /*
         * Converts a list of cities to an array of city ids.
         * @param cityList: List<City> -> List of cities.
         * @return int[] -> Array of city ids.
         */
        int[] tour = new int[cityList.size()];
        for (int i = 0; i < cityList.size(); i++) {
            City city = cityList.get(i);
            tour[i] = city.getCityId();
        }
        return tour;
    }

    public static AlgoResultDS main(City[] cities, City startingCity) {
        return TSPMSTPrimsAlgo(cities, startingCity);
    }

}