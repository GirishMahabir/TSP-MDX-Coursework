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
}
