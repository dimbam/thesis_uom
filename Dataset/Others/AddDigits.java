
public class AddDigits {
    public static void main (String[] args) {
        System.out.println(addDigits(123));
    }

    public static int addDigits(int num) {
        return num == 0 ? 0 : 1 + (num - 1) % 9; //Formula for the Digital Root
    }
}
