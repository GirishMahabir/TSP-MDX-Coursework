//public class FileTreatment {
//    /*
//     * FileTreatment is a class that contains methods to read and write to files.
//     * Ideal File Format:
//     * Files must have 3 Columns (Integer, Integer, Integer)
//     * Columns must be separated by a tab.
//     * Files must not:
//     * Contain any other characters other than integers and tabs.
//     * Contain any empty lines.
//     * Contain any lines with less than 3 columns.
//     * Contain any lines with more than 3 columns.
//     * Contains additional spaces before or after the integers.
//     */
//
//    public static String[] readFile(String fileName) {
//        /*
//         * Reads a file and returns an array of strings.
//         * Each string is a line in the file.
//         */
//
//        String[] fileContent = {};
//        /* Read content from file, line by line, and store in fileContent. */
//        try {
//            fileContent = Files.readAllLines(Path.of(fileName)).toArray(String[]::new);
//        } catch (IOException e) {
//            System.out.println("File not found. Please enter a valid file path.");
//            System.exit(0);
//        }
//        return fileContent;
//    }
//
//    public static String[] processLines(String[] fileContent) {
//        /*
//         * Processes the lines in the file.
//         * Removes all spaces before and after the integers.
//         * Removes all empty lines.
//         * Removes all lines with less than 3 columns.
//         * Removes all lines with more than 3 columns.
//         * @param fileContent: String[] -> Array of strings.
//         * @return processedLines: String[] -> Array of strings.
//         */
//        String[] processedLines = {};
//        for (String line : fileContent) {
//            // Remove all spaces before and after the integers.
//            line = line.trim();
//            System.out.println(line);
//        }
//        return processedLines;
//    }
//
//    public static void main(String[] args) {
//        String[] fileContent = readFile("/home/girish/Documents/MDX/AI/Travelling Salesman Problem/Coursework 1 - Files/train1.txt");
//        String[] processedLines = processLines(fileContent);
//    }
//
//}