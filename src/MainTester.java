public class MainTester {
    public static void main(String[] args) {
        // Array of dataset paths
        String[] datasetPaths = {
                "/home/girish/Documents/MDX/AI/Travelling Salesman Problem/Coursework 1 - Files/sample1-22.txt",
                "/home/girish/Documents/MDX/AI/Travelling Salesman Problem/Coursework 1 - Files/sample2-22.txt",
                "/home/girish/Documents/MDX/AI/Travelling Salesman Problem/Coursework 1 - Files/sample3-22.txt",
                "/home/girish/Documents/MDX/AI/Travelling Salesman Problem/Coursework 1 - Files/sample4-22.txt",
                "/home/girish/Documents/MDX/AI/Travelling Salesman Problem/Coursework 1 - Files/train1.txt",
                "/home/girish/Documents/MDX/AI/Travelling Salesman Problem/Coursework 1 - Files/train2.txt",
                "/home/girish/Documents/MDX/AI/Travelling Salesman Problem/Coursework 1 - Files/train3.txt"
        };

        // Array of algorithms to test.
        String[] algorithms = {"1", "2", "3"};

        for (String path : datasetPaths) {
            System.out.println("Dataset: " + path);
            for (String algorithm : algorithms) {
                // Create an instance of the main class
                Main mainInstance = new Main();
                // Simulate user input for filename and algorithm choice
                System.setIn(new java.io.ByteArrayInputStream((path + "\n" + algorithm + "\n").getBytes()));
                // Call the main method
                mainInstance.main(null);
            }
            System.out.println("====================================");
        }
    }
}
