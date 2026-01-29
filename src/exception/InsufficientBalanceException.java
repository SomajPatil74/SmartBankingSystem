package exception;

public class InsufficientBalanceException extends RuntimeException{
    
    // Thrown when account balance is insufficient.
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
