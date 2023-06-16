
public class CheckIfTwoStringArraysAreEquivalent {
    public static void main (String[] args) {
        String[] a = new String[]{"a" , "bc" , "def"};
        String[] b = new String[]{"abc" , "de" , "f"};
        System.out.println(arrayStringsAreEqual(a , b));
    }
    public static boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        return Arrays.stream(word1).reduce( (a , b)-> a + b )
                .equals(Arrays.stream(word2).reduce( (a , b)-> a + b ));
    }
}
