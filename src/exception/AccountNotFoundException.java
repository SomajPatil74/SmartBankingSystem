package exception;

public class AccountNotFoundException extends RuntimeException {
    
    // Thrown when account is not found.
    public AccountNotFoundException(String message) {
        super(message);
    }
}
