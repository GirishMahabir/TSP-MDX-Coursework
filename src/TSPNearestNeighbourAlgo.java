import java.util.Arrays;
import java.util.ArrayList;

public class TSPNearestNeighbourAlgo {
    /*
    Class to Tackle the Traveling Salesman Problem using the Nearest Neighbor Algorithm.
    */
    public static float[][] distanceMatrix(City[] cities) {
        /*
         * distanceMatrix calculates the distance between all cities and returns a matrix.
         * @param cities: City[] -> Array of cities.
         * @return float[][] -> Matrix of distances between cities.
         */

        int numberOfCities = cities.length;
        float[][] matrix = new float[numberOfCities][numberOfCities];

        for (int i = 0; i < cities.length; i++) {
            // Loop through all cities and calculate the distance between them.
            for (int j = i + 1; j < numberOfCities; j++) {
                // Loop through all cities after the current city.
                matrix[i][j] = TSPCommon.Distance(cities[i], cities[j]);
                // Set the distance between the current city and the next city.
                matrix[j][i] = matrix[i][j];
            }
        }
        return matrix;
    }

    public static void printDistanceMatrix(float[][] matrix) {
        /*
         * printDistanceMatrix prints the distance matrix.
         * @param matrix: float[][] -> Matrix of distances between cities.
         */
        System.out.println("Distance Matrix");
        // Print in proper rows and columns with proper headings (everything at 2 decimal places)
        // Print column headings
        System.out.print("City ID\t\t");
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(i + 1 + "\t\t");
        }
        System.out.println();
        // Print rows
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(i + 1 + "\t\t");
            for (int j = 0; j < matrix.length; j++) {
                System.out.printf("%.2f\t", matrix[i][j]);
            }
            System.out.println();
        }
    }

    public static AlgoResultDS tourDistance(City[] cities, City startingCity) {
        /*
         * tourDistance calculates the total distance of the tour, Traveling from one city to another.
         * @param cities: City[] -> Array of cities.
         * @param startingCity: City -> Starting city.
         * @return AlgoResultDS -> Object containing the total distance and the visited cities.
         */
        float[][] distanceMatrix = distanceMatrix(cities);
//        printDistanceMatrix(distanceMatrix);
        boolean[] visited = new boolean[cities.length]; // Used to keep track of visited cities.
        Arrays.fill(visited, false); // Initialize all values to false.

        int currentCityIndex = startingCity.getCityId() - 1; // -1 because array index starts from 0
        visited[currentCityIndex] = true; // Mark starting city as visited.
        ArrayList<City> visitedCities = new ArrayList<>(); // Used to keep track of visited cities.
        visitedCities.add(cities[currentCityIndex]); // Add starting city to visited cities.

        float totalDistance = 0; // Total distance of the tour.

        for (int i = 0; i < cities.length - 1; i++) {
            /*
                * Loop through all cities and find the nearest city.
                * Add the distance to the total distance.
                * Mark the nearest city as visited.
                * Add the nearest city to the visited cities.
                * Set the current city to the nearest city.
                * Repeat until all cities are visited.
             */
            float nearestDistance = Float.MAX_VALUE;
            int nearestCityIndex = -1;

            for (int j = 0; j < cities.length; j++) {
                if (!visited[j] && distanceMatrix[currentCityIndex][j] < nearestDistance) {
                    nearestDistance = distanceMatrix[currentCityIndex][j];
                    nearestCityIndex = j;
                }
            }

            visited[nearestCityIndex] = true;
            visitedCities.add(cities[nearestCityIndex]);
            totalDistance += nearestDistance;
            currentCityIndex = nearestCityIndex;
        }

        // Add distance back to the starting city
        totalDistance += distanceMatrix[currentCityIndex][startingCity.getCityId() - 1];
        visitedCities.add(startingCity);

        // Convert ArrayList to Array (To keep standard return type)
        int[] visitedCitiesArray = new int[visitedCities.size()];
        for (int i = 0; i < visitedCities.size(); i++) {
            visitedCitiesArray[i] = visitedCities.get(i).getCityId();
        }

        return new AlgoResultDS(visitedCitiesArray, totalDistance);
    }

    public static AlgoResultDS main(City[] cities, City startingCity) {
        return tourDistance(cities, startingCity);
    }
}