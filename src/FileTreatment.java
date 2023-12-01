import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.IOException;
public class FileTreatment {
    /*
      * Class to Analyze the dataset of cities that the user wants to use.
      * If it can be fixed, it shall be fixed, else it shall inform the user to fix it and try again.
      * Finally, it will return the fixed/clean array of lines (cities).
    */

    public static void checkFileExists(String fileName) {
        /*
          * Check if the file exists.
          * @param String: fileName -> Dataset File Path.
          * @return String[]: String[] -> Array of lines from the file.
        */
        // Check if the file exists.
        if (Files.exists(Path.of(fileName))) {
            System.out.println("File exists, processing file...");
        } else {
            System.err.println("File does not exist.");
            System.exit(0);
        }
    }

    public static String[] readFile(String fileName) {
        /*
          * Read file line by line and prepare an array of the dataset (line by line)
          * @param String: fileName -> Dataset File Path.
          * @return String[]: String[] -> Array of lines from the file.
         */
        try {
            // Read file line by line and prepare an array of the dataset.
            return Files.readAllLines(Path.of(fileName)).toArray(new String[0]);
        } catch (IOException e) {
            // Print error message for any error caught.
            System.err.println("Error reading file: " + e.getMessage());
            return new String[0];
        }
    }

    public static boolean checkUnexpectedCharacters(String[] fileData) {
        /*
          * Check if there are any unexpected characters in the file.
          * @param String[]: fileData -> Array of lines from the file.
          * @return boolean: boolean -> True if there are no unexpected characters, else False.
        */
        // Loop line by line checking if there is any unexpected characters.
        int fileDataLength = fileData.length;
        for (int l = 0; l < fileDataLength; l++) {
            String line = fileData[l];
            // Loop character by character in the line and check if there is any unexpected character.
            for (char c : line.toCharArray()) {
                if (!Character.isDigit(c) && c != ' ' && c != '\t' && c != '-') {
                    System.err.println("Unsuported characters or separator '" + c
                            + "' found in line " + (l + 1) + ": " + line);
                    System.err.println("Ensure the file content are separated by space or tabs and run the program again.");
                    System.err.println("Fix the file and run the program again.");
                    return false;
                }
            }
        }
        return true;
    }

    public static String[] removeEmptyLines(String[] fileData) {
        /*
          * Remove empty lines from the file.
          * @param String[]: fileData -> Array of lines from the file.
          * @return String[]: String[] -> Array of lines from the file without empty lines.
        */
        List<String> lines = new ArrayList<>();
        // Loop line by line and check if it is empty.
        for (String line : fileData) {
            if (!line.trim().isEmpty()) {
                lines.add(line); // Add to list of lines.
            }
        }
        return lines.toArray(new String[0]);
    }

    public static String[][] fixFileFormat(String[] fileData) {
        /*
          * Fix file format ensuring that it's in the required format.
                * <cityId> <x-oordinate> <y-coordinate>
          * @param String[]: fileData -> Array of lines from the file.
          * @return String[][]: String[][] -> Array of lines from the file with correct format.
        */
        List<String[]> formattedLines = new ArrayList<>();
        // Loop line by line and check if it's in the correct format.
        for (String line : fileData) {
            Line lineObj = new Line(line);
            if (lineObj.isCorrectFormat()) {
                formattedLines.add(lineObj.getFormattedLine().split(" "));
            } else {
                // Inform user that the line is not in the correct format.
                System.err.println("Incorrect format line: " + line);
            }
        }
        return formattedLines.toArray(new String[0][]);
    }

    public static String[] processFile(String fileName) {
        /*
          * Process file and check if its in the correct format.
          * @param String: fileName -> Dataset File Path.
          * @return String[]: String[] -> Array of lines from the file with correct format.
        */
        String[] fileData = readFile(fileName);
        // Check if the file isn;t empty.
        if (fileData.length == 0) {
            System.err.println("No data read from file.");
            return new String[0];
        }

        // Check if there is any unexpected characters in the file.
        if (checkUnexpectedCharacters(fileData)) {
            fileData = removeEmptyLines(fileData);
            String[][] formattedData = fixFileFormat(fileData); // This returns String[][]
            // Convert String[][] to String[]
            return Arrays.stream(formattedData)
                    .map(parts -> String.join(" ", parts))
                    .toArray(String[]::new);
        } else {
            System.err.println("Unexpected characters found in file.");
            return new String[0];
        }
    }
}