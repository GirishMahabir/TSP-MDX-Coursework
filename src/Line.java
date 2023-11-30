public class Line {
    /*
      * Line class is used to store the line read from the file.
      * Each line has 3 parts: cityId, cityXLocation, cityYLocation.
    */
    private String[] parts;

    public Line(String rawLine) {
        // Split the line based on one or more spaces
        this.parts = rawLine.trim().split("\\s+");
    }

    public boolean isCorrectFormat() {
        // Check if the line has 3 parts
        return parts.length == 3;
    }

    public String getFormattedLine() {
        // Join the parts with a space
        return String.join(" ", parts);
    }
}