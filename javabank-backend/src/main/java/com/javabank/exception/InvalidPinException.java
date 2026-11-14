package JavaBank.Exceptions;

public class InvalidPinException extends AuthenticationException{
    public InvalidPinException(String message) {
        super(message);
    }
    
}
