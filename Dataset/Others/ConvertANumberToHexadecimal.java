
public class ConvertANumberToHexadecimal {
    public static void main (String[] args) {
        System.out.println(toHex(999999999));
    }
    public static String toHex(int num) {
        String hexDigit = "0123456789abcdef";
        if(num == 0) return "0";
        String ans = "";
        while (num != 0){
            ans = hexDigit.charAt(num & 15) + ans;
            num >>>= 4;
        }
        return ans;
    }
}
