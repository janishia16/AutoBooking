package exception;

public class DriverIdNotFound extends RuntimeException {
    public DriverIdNotFound(String message) {
        super(message);
    }
}
