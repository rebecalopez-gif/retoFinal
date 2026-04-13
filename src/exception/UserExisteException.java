package exception;

public class UserExisteException extends Exception {
    public UserExisteException(String mensaje) {
        super(mensaje);
    }
}
