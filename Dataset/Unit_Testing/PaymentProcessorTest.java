public class PaymentProcessorTest {

    @Mock
    private PaymentGateway paymentGateway;

    private PaymentProcessor paymentProcessor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        paymentProcessor = new PaymentProcessor(paymentGateway);
    }

    @Test
    public void testProcessPaymentSuccessful() throws PaymentFailedException {
        when(paymentGateway.processPayment(anyDouble())).thenReturn(true);
        double amount = 100.0;
        boolean result = paymentProcessor.processPayment(amount);
        assertTrue(result);
        verify(paymentGateway, times(1)).processPayment(amount);
    }

    @Test
    public void testProcessPaymentFailed() throws PaymentFailedException {
        when(paymentGateway.processPayment(anyDouble())).thenReturn(false);
        double amount = 100.0;
        assertThrows(PaymentFailedException.class, () -> paymentProcessor.processPayment(amount));
        verify(paymentGateway, times(1)).processPayment(amount);
    }
}

class PaymentProcessor {

    private PaymentGateway paymentGateway;

    public PaymentProcessor(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public boolean processPayment(double amount) throws PaymentFailedException {
        if (paymentGateway.processPayment(amount)) {
            return true;
        } else {
            throw new PaymentFailedException("Payment processing failed");
        }
    }
}

interface PaymentGateway {

    boolean processPayment(double amount);
}

class PaymentFailedException extends Exception {

    public PaymentFailedException(String message) {
        super(message);
    }
}