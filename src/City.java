public class City {
    /*
      * City class is used to store the city id and its x and y location.
    */
    int cityId;
    float cityXLocation;
    float cityYLocation;

    public City() {
        cityId = 1; // Default value, 1st city.
        cityXLocation = 0; // Default value, 0.
        cityYLocation = 0; // Default value, 0.
    }

    public City(int cityId, float cityXLocation, float cityYLocation) {
        /*
          * Constructor for City class.
          * @param cityId: The id of the city.
          * @param cityXLocation: The x location of the city.
          * @param cityYLocation: The y location of the city.
        */
        this.cityId = cityId;
        this.cityXLocation = cityXLocation;
        this.cityYLocation = cityYLocation;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public float getCityXLocation() {
        return cityXLocation;
    }

    public void setCityXLocation(float cityXLocation) {
        this.cityXLocation = cityXLocation;
    }

    public float getCityYLocation() {
        return cityYLocation;
    }

    public void setCityYLocation(float cityYLocation) {
        this.cityYLocation = cityYLocation;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityID=" + cityId +
                ", cityXLocation=" + cityXLocation +
                ", cityYLocation=" + cityYLocation +
                '}';
    }

}
