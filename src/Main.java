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
        // Starting timer for algorithm.
        final long start_time = System.nanoTime();

        /*
                      ######## ALGORITHM ########
            - Should Return Array of City IDs in order of visits.
            - Should Return Total Distance Traveled.
        */
        // NN Algorithm
        AlgoResultDS algoResult = TSPNearestNeighbourAlgo.main(cities, cities[0]);
        // Dijkstra Algorithm
        // AlgoResultDS algoResult = TSPDijkstraAlgo.main(cities, cities[0]);



        // Print the result.
        System.out.println("Tour: " + Arrays.toString(algoResult.getTour()));
        System.out.println("Distance: " + algoResult.getDistance());

        // End timer for algorithm.
        final long end_time = System.nanoTime();
        System.out.println("Total execution time: " + (end_time - start_time) + "ns");


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

    public static City[] populateCity(String[] cities_string) {
        /*
         * populateCity takes an array of strings and returns an array of City objects.
         * @param cities_string: String[]
         * @return cities: City[]
         */
        City[] cities = new City[cities_string.length];

        for (int i = 0; i < cities_string.length; i++) {
            String[] city = cities_string[i].split("\t");
            cities[i] = new City(Integer.parseInt(city[0]), Float.parseFloat(city[1]), Float.parseFloat(city[2]));
        }

        return cities;
    }
}