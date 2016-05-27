package fred.yu;

/**
 * Created by Fred on 16/5/27.
 */
public class ShoppingException extends Exception {
    public ShoppingException(String message) {
        super(message);
    }

    public ShoppingException(String message, Throwable cause) {
        super(message, cause);
    }
}
