public class FileHandlingExample {
    public static void main(String[] args) {
        try {
            // Create a new directory
            File dir = new File("example");

            // Check if the directory exists
            if (dir.exists()) {
                System.out.println("Directory exists!");
            } else {
                System.out.println("Directory does not exist.");

                // Create the directory
                if (dir.mkdir()) {
                    System.out.println("Directory created!");
                } else {
                    System.out.println("Directory could not be created.");
                }
            }

            // List the files in the directory
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    System.out.println(file.getName());
                }
            }

            // Recursively delete the directory and its contents
            if (deleteDirectory(dir)) {
                System.out.println("Directory deleted.");
            } else {
                System.out.println("Directory could not be deleted.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper method to recursively delete a directory and its contents
    public static boolean deleteDirectory(File directory) {
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteDirectory(file);
                    } else {
                        file.delete();
                    }
                }
            }
        }
        return directory.delete();
    }
}
