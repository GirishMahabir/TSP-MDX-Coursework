final class AlgoResultDS {
    private final int[] tour;
    private final float distance;

    public AlgoResultDS(int[] tour, float distance) {
        this.tour = tour;
        this.distance = distance;
    }
    public int[] getTour() {
        return tour;
    }
    public float getDistance() {
        return distance;
    }
}
