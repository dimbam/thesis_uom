public class TemperatureConverterTest {

    @Test
    public void testCelsiusToFahrenheit() {
        double celsius = 25.0;
        double expectedFahrenheit = 77.0;
        double actualFahrenheit = TemperatureConverter.celsiusToFahrenheit(celsius);
        assertEquals(expectedFahrenheit, actualFahrenheit, 0.01);
    }

    @Test
    public void testFahrenheitToCelsius() {
        double fahrenheit = 77.0;
        double expectedCelsius = 25.0;
        double actualCelsius = TemperatureConverter.fahrenheitToCelsius(fahrenheit);
        assertEquals(expectedCelsius, actualCelsius, 0.01);
    }

    @Test
    public void testNegativeTemperature() {
        double celsius = -10.0;
        double expectedFahrenheit = 14.0;
        double actualFahrenheit = TemperatureConverter.celsiusToFahrenheit(celsius);
        assertEquals(expectedFahrenheit, actualFahrenheit, 0.01);
    }
}

class TemperatureConverter {

    public static double celsiusToFahrenheit(double celsius) {
        return (celsius * 1.8) + 32.0;
    }

    public static double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32.0) / 1.8;
    }
}