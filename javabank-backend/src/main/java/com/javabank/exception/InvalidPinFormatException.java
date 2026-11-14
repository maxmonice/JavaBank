package JavaBank.Exceptions;

public class InvalidPinFormatException extends AuthenticationException {
    public InvalidPinFormatException(String message) {
        super(message);
    } 
}
