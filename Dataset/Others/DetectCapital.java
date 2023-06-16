
public class DetectCapital {
    public static void main (String[] args) {
        System.out.println(detectCapitalUse("Hussein"));
    }
    public static boolean detectCapitalUse(String word) {
        int cnt = 0;
        for (var c : word.toCharArray()){
            if (Character.isUpperCase(c))
                ++cnt;
        }
        if (cnt == word.length() || cnt == 0)
            return true;
        return cnt == 1 && Character.isUpperCase(word.charAt(0));
    }
}
