public class City {
    int City_ID;
    float City_X_Location;
    float City_Y_Location;

    public City() {
        City_ID = 1; // Default value, 1st city.
        City_X_Location = 0; // Default value, 0.
        City_Y_Location = 0; // Default value, 0.
    }

    public City(int city_ID, float city_X_Location, float city_Y_Location) {
        City_ID = city_ID;
        City_X_Location = city_X_Location;
        City_Y_Location = city_Y_Location;
    }

    public int getCity_ID() {
        return City_ID;
    }

    public void setCity_ID(int city_ID) {
        City_ID = city_ID;
    }

    public float getCity_X_Location() {
        return City_X_Location;
    }

    public void setCity_X_Location(float city_X_Location) {
        City_X_Location = city_X_Location;
    }

    public float getCity_Y_Location() {
        return City_Y_Location;
    }

    public void setCity_Y_Location(float city_Y_Location) {
        City_Y_Location = city_Y_Location;
    }

    @Override
    public String toString() {
        return "City{" +
                "City_ID=" + City_ID +
                ", City_X_Location=" + City_X_Location +
                ", City_Y_Location=" + City_Y_Location +
                '}';
    }

}
