import java.util.Arrays;

public class TSPNearestNeighbourAlgo {

    /*
    Class to Tackle the Traveling Salesman Problem using the Nearest Neighbor Algorithm.
    */

    public static float Distance(City city1, City city2) {
        /*
         * Distance calculates the distance between two cities.
         * @param city1: City -> 1st City
         * @param city2: City -> 2nd City
         * @return float -> Distance between the cities.
         */
        float x1 = city1.getCity_X_Location();
        float y1 = city1.getCity_Y_Location();
        float x2 = city2.getCity_X_Location();
        float y2 = city2.getCity_Y_Location();

        return (float) Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    }

    public static float[][] DistanceMatrix(City[] cities) {
        /*
         * DistanceMatrix calculates the distance between all cities and returns a matrix.
         * @param cities: City[] -> Array of cities.
         * @return float[][] -> Matrix of distances between cities.
         */
        float[][] distance_matrix = new float[cities.length][cities.length];

        for (int i = 0; i < cities.length; i++) {
            for (int j = i + 1; j < cities.length; j++) {
                distance_matrix[i][j] = Distance(cities[i], cities[j]);
                distance_matrix[j][i] = distance_matrix[i][j];
            }
        }

        return distance_matrix;
    }

    public static City ClosestCity(City[] cities, City city, int[] ignoreCities) {
        /*
         * ClosestCity returns the closest city to the current city.
         * @param cities: City[] -> Array of cities.
         * @param city: City -> Current city.
         * @param ignoreCities: int[] -> Array of city IDs to ignore.
         * @return City -> Closest city to the current city.
         */
        float[][] distanceMatrix = DistanceMatrix(cities);
        int cityId = city.getCity_ID();
        float minDistance = Float.MAX_VALUE;
        int closestCityId = -1;

        if (ignoreCities == null) {
            return null;
        }

        for (City otherCity : cities) {
            int otherCityId = otherCity.getCity_ID();

            if (otherCityId == cityId || Arrays.binarySearch(ignoreCities, otherCityId) >= 0) {
                continue;
            }

            float distance = distanceMatrix[cityId][otherCityId];

            if (distance < minDistance) {
                minDistance = distance;
                closestCityId = otherCityId;
            }
        }

        if (closestCityId != -1) {
            return cities[closestCityId];
        } else {
            return null;
        }
    }

    public static AlgoResultDS TourDistance(City[] cities, City startingCity) {
        /*
         * TourDistance calculates the total distance of the tour, Travelling from one city to another.
         * @param cities: City[] -> Array of cities.
         * @param startingCity: City -> Starting city.
         */
        int numCities = cities.length;
        float[][] distanceMatrix = DistanceMatrix(cities);
        float totalDistance = 0;
        int[] visitedCities = new int[numCities + 1];
        boolean[] isVisited = new boolean[numCities];
        int currentCityIndex = startingCity.getCity_ID();
        visitedCities[0] = currentCityIndex;
        isVisited[currentCityIndex] = true;
        int visitedCount = 1;

        while (visitedCount < numCities) {
            float minDistance = Float.MAX_VALUE;
            int closestCityIndex = -1;

            for (int i = 0; i < numCities; i++) {
                if (!isVisited[i] && i != currentCityIndex && distanceMatrix[currentCityIndex][i] < minDistance) {
                    minDistance = distanceMatrix[currentCityIndex][i];
                    closestCityIndex = i;
                }
            }

            if (closestCityIndex != -1) {
                totalDistance += minDistance;
                visitedCities[visitedCount] = closestCityIndex;
                isVisited[closestCityIndex] = true;
                visitedCount++;
                currentCityIndex = closestCityIndex;
            }
        }

        totalDistance += distanceMatrix[currentCityIndex][startingCity.getCity_ID()];
        visitedCities[visitedCount] = startingCity.getCity_ID();
        return new AlgoResultDS(visitedCities, totalDistance);
    }

    public static AlgoResultDS main(City[] cities, City startingCity) {
        return TourDistance(cities, startingCity);
    }
}