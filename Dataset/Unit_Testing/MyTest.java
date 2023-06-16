public class MyTest {

    @Test
    public void testAddition() {
        int a = 2;
        int b = 3;
        int expected = 5;
        int result = MyMath.add(a, b);
        assertEquals(expected, result);
    }
}

class MyMath {
    public static int add(int a, int b) {
        return a + b;
    }
}