import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Declare filename Variable (Dataset of cities)
        String filename = "/home/girish/Documents/MDX/AI/Travelling Salesman Problem" +
                "/Coursework 1 - Files/sample1-22.txt";
        // Declare cities Variable (Array of cities)
        String[] cities_string = ReadCities(filename);
        // Convert cities_string to cities
        City[] cities = populateCity(cities_string);

        // Start Timer
        long startTime = System.nanoTime();

        // Run Nearest Neighbour Algorithm
//        RunNearestNeighborAlgo(cities, cities[0]);
        // Run Dijkstra Algorithm
        RunDijkstraAlgo(cities, cities[0]);


        // Print Time Taken
        System.out.println("Time Taken: " + (System.nanoTime() - startTime) + " nanoseconds");

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


    public static String[] ReadCities(String filename) {
        /*
         * ReadCities reads the content of a file and returns an array of strings.
         * @param filename: String
         * @return file_content: String[]
         */
        String[] file_content = {};
        /* Read content from file, line by line, and store in file_content. */
        try {
            file_content = Files.readAllLines(Path.of(filename)).toArray(String[]::new);
        } catch (IOException e) {
            System.out.println("File not found. Please enter a valid file path.");
            System.exit(0);
        }
        return file_content;
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
            String[] city = cities_string[i].split("\t");
            cities[i] = new City(Integer.parseInt(city[0]), Float.parseFloat(city[1]), Float.parseFloat(city[2]));
        }

        return cities;
    }
}