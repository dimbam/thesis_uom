import java.io.*;
public class CheckedException {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter any sentence: ");
        String s = br.readLine();
        System.out.println("Your string: " + s);
        System.out.println("End of main()");
    }
}

