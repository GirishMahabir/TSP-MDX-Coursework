public class EdgeDS implements Comparable<EdgeDS> {
    /*
      * Edge Class to represent an edge between two cities.
      * Each edge has a destination and a cost.
      * Edge: (destination, cost)
      * Destination: City
      * Cost: float
      * Implements Comparable interface to compare two edges.
    */
    City destination; // City Destination
    float cost; // Cost of the edge

    public EdgeDS(City destination, float cost) {
        /*
          * Constructor for Edge class.
          * @param destination: City
          * @param cost: float
        */
        this.destination = destination;
        this.cost = cost;
    }

    @Override
    public int compareTo(EdgeDS other) {
        /*
          * Compare two edges based on their cost.
          * @param other: Edge
          * @return int
        */
        return Float.compare(this.cost, other.cost);
    }

    public City getDestination() {
        /*
          * Get the destination of the edge.
          * @return destination: City
        */
        return this.destination;
    }

    public float getCost() {
        return this.cost;
    }
}