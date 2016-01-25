package by.epam.library.exception;

/**
 * Created by Gubanov Andrey on 16.12.2015.
 */

public class IncorrectFormDataException extends Exception {
    public IncorrectFormDataException(String param, String value) {
        super(String.format("\"%s\": %s", param, value));
    }
}
