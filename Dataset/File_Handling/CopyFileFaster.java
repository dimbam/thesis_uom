
class CopyFileFaster {
  public static void main(String[] args) throws IOException {
    FileInputStream fis;
    FileOutputStream fos;
    BufferedInputStream bis;
    BufferedOutputStream bos;
    try {
      Scanner s = new Scanner(System.in);
      System.out.println("Enter the source filename(with relative or absolute path): ");
      String source = s.nextLine();
      System.out.println("Enter the destination filename(with relative or absolute path): ");
      String destination = s.nextLine();
      s.close();
      fis = new FileInputStream(source);
      fos = new FileOutputStream(destination);

      bis = new BufferedInputStream(fis);
      bos = new BufferedOutputStream(fos);

      System.out.println("Copying....");
      int i;
      while ((i = bis.read()) != -1) {
        bos.write(i);
      }
      System.out.println("Copy completed");

      fis.close();
      fos.close();
      bis.close();
      bos.close();
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
    }
  }
}