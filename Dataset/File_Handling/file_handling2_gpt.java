public class FileHandlingExample {
    public static void main(String[] args) {
        try {
            // Create a new file
            File file = new File("example.txt");

            // Check if the file exists
            if (file.exists()) {
                System.out.println("File exists!");
            } else {
                System.out.println("File does not exist.");

                // Create the file
                if (file.createNewFile()) {
                    System.out.println("File created!");
                } else {
                    System.out.println("File could not be created.");
                }
            }

            // Delete the file
            if (file.delete()) {
                System.out.println("File deleted.");
            } else {
                System.out.println("File could not be deleted.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
