

public class CheckAllElementsAreEqual {
    public static <T> boolean allEqual(T[] array){
        return Arrays.stream(array).distinct().count() == 1;
    }
}
