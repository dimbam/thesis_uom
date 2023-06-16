public class FileHandlingExample {
    public static void main(String[] args) {
        try {
            // Open a file for writing
            FileWriter writer = new FileWriter("example.txt");
            writer.write("Hello, world!");
            writer.close();

            // Open the same file for reading
            FileReader reader = new FileReader("example.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);

            // Read the file line by line and print it to the console
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
