public class FileHandlingExample {
    public static void main(String[] args) {
        File directory = new File("exampleDir");

        if (directory.mkdir()) {
            System.out.println("Directory created successfully.");
        } else {
            System.out.println("Failed to create the directory.");
        }
    }
}
