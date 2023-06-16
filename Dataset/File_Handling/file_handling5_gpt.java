public class FileHandlingExample {
    public static void main(String[] args) {
        try {
            // Open the source file for reading
            FileInputStream inputStream = new FileInputStream("source.txt");

            // Open the destination file for writing
            FileOutputStream outputStream = new FileOutputStream("destination.txt");

            // Copy the contents of the source file to the destination file
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            // Close the input and output streams
            inputStream.close();
            outputStream.close();

            System.out.println("File copied successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
