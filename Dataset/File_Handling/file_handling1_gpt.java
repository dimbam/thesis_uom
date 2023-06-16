public class FileHandlingExample {
    public static void main(String[] args) {
        try {
            // Open a file for reading
            File file = new File("example.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));

            // Read from the file
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }

            // Close the reader
            reader.close();

            // Open a file for writing
            FileWriter writer = new FileWriter("output.txt");

            // Write to the file
            writer.write("Hello, world!\n");

            // Close the writer
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
