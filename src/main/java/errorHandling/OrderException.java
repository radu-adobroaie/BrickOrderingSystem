package errorHandling;

public class OrderException extends RuntimeException {

    public static final String ILLEGAL_ARG_ZERO_OR_NULL = "Illegal argument: 0 or null order";
    public static final String ILLEGAL_ARG_NO_SUCH_REFERENCE = "Illegal argument: empty or null reference";
    public static final String NO_EXISTING_ORDERS = "No orders have been created yet";
    public static final String ALREADY_DISPATCHED = "Order has already been dispatched";
    public OrderException(final String message) {
        super(message);
    }
}
