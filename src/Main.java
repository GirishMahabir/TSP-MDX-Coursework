import java.util.Arrays;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        // Ask for user input for filename.
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter filename: ");
        String filename = scanner.nextLine();

        // Chceck if filename exists.
        FileTreatment.checkFileExists(filename);

        // Declare cities Variable (Array of cities)
        String[] citiesString = FileTreatment.processFile(filename);
        // Convert citiesString to cities
        City[] cities = populateCity(citiesString);

        // Print options for user to choose from.
        String[] availableAlgos = {"1. MST Prims Algorithm", "2. Dijkstra Algorithm", "3. Nearest Neighbor Algorithm"};
        System.out.println("Available Algorithms: ");
        for (String algo : availableAlgos) {
            System.out.println(algo);
        }

        // Ask for user input for algorithm.
        System.out.print("Enter algorithm: ");
        String algo = scanner.nextLine();

        // Check if user input is valid.
        if (!Arrays.asList("1", "2", "3").contains(algo)) {
            System.out.println("Invalid Input");
            System.exit(0);
        }


        System.out.println("Starting from city: " + cities[0].getCityId());
        // Run algorithm based on input.
        RunAlgoBasedOnInput(cities, cities[0], algo);

    }

    public static void RunAlgoBasedOnInput(City[] cities, City startingCity, String algo) {
        /*
         * RunAlgoBasedOnInput runs the algorithm based on the input.
         * @param cities: City[] -> Array of cities.
         * @param startingCity: City -> Starting city.
         * @param algo: String -> Algorithm to run.
        */
        long startTime = System.nanoTime();

        switch (algo) {
            case "1":
                // Run MST Prims Algorithm
                RunMSTPrimsAlgo(cities, startingCity);
                System.out.println("Time taken: " + (System.nanoTime() - startTime) / 1000000 + "ms");
                break;
            case "2":
                // Run Dijkstra Algorithm
                RunDijkstraAlgo(cities, startingCity);
                System.out.println("Time taken: " + (System.nanoTime() - startTime) / 1000000 + "ms");
                break;
            case "3":
                // Run Nearest Neighbor Algorithm
                RunNearestNeighborAlgo(cities, startingCity);
                System.out.println("Time taken: " + (System.nanoTime() - startTime) / 1000000 + "ms");
                break;
            default:
                System.out.println("Invalid Input");
                break;
        }
    }


    public static void RunMSTPrimsAlgo(City[] cities, City startingCity) {
        /*
         * RunMSTPrimsAlgo runs the MST Prims Algorithm.
         * @param cities: City[] -> Array of cities.
         * @param startingCity: City -> Starting city.
         */
        // Run MST Prims Algorithm
        AlgoResultDS result = TSPMSTPrimsAlgo.main(cities, startingCity);
        // Print results
        System.out.println("Tour: " + Arrays.toString(result.getTour()));
        System.out.println("Distance: " + result.getDistance());
    }

    public static void RunDijkstraAlgo(City[] cities, City startingCity) {
        /*
         * RunDijkstraAlgo runs the Dijkstra Algorithm.
         * @param cities: City[] -> Array of cities.
         * @param startingCity: City -> Starting city.
         */
        // Run Dijkstra Algorithm
        AlgoResultDS result = TSPDijkstraAlgo.main(cities, startingCity);
        // Print results
        System.out.println("Tour: " + Arrays.toString(result.getTour()));
        System.out.println("Distance: " + result.getDistance());
    }

    public static void RunNearestNeighborAlgo(City[] cities, City startingCity) {
        /*
         * RunNearestNeighbourAlgo runs the Nearest Neighbour Algorithm.
         * @param cities: City[] -> Array of cities.
         * @param startingCity: City -> Starting city.
         */
        // Run Nearest Neighbor Algorithm
        AlgoResultDS result = TSPNearestNeighbourAlgo.main(cities, startingCity);
        // Print results
        System.out.println("Tour: " + Arrays.toString(result.getTour()));
        System.out.println("Distance: " + result.getDistance());
    }

    // populateCity using the new HashMap -> CityManager Data Structure.
    public static City[] populateCity(String[] cities_string) {
        /*
         * populateCityManager takes an array of strings and returns a CityManager object.
         * @param cities_string: String[]
         * @return cityManager: CityManager
         */
        City[] cities = new City[cities_string.length];

        for (int i = 0; i < cities_string.length; i++) {
            String[] city = cities_string[i].split(" ");
            cities[i] = new City(Integer.parseInt(city[0]), Float.parseFloat(city[1]), Float.parseFloat(city[2]));
        }

        return cities;
    }
}