package by.epam.library.exception;

/**
 * Created by Gubanov Andrey on 16.12.2015.
 */

public class PersistentException extends Exception {
    public PersistentException() {
    }

    public PersistentException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersistentException(String message) {
        super(message);
    }

    public PersistentException(Throwable cause) {
        super(cause);
    }
}
