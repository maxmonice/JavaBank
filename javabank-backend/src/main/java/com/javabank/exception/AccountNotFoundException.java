package JavaBank.Exceptions;

public class AccountNotFoundException extends AuthenticationException {
    public AccountNotFoundException(String message) {
        super(message);
    }
}
