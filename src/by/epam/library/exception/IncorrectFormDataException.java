package by.epam.library.exception;

/**
 * Created by Gubanov Andrey on 16.12.2015.
 */

public class IncorrectFormDataException extends Exception {
    public IncorrectFormDataException(String param, String value) {
        super(String.format("Empty or incorrect \"%s\" parameter was found: %s", param, value));
    }
}
