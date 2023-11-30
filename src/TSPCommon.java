public class TSPCommon {
    /*
        * TSPCommon class is used to store the common functions used by the algorithms.
    */
    public static float Distance(City city1, City city2) {
        /*
         * Distance calculates the distance between two cities and returns a float.
         * @param city1: City
         * @param city2: City
         * @return distance: float
         */
        return (float) Math.sqrt(Math.pow((city1.getCityXLocation() - city2.getCityXLocation()), 2)
                + Math.pow((city1.getCityYLocation() - city2.getCityYLocation()), 2));
    }
}
