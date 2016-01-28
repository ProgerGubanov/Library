package by.epam.library.exception;

/**
 * Обработка исключительных ситуаций при проверке корректности введенных данных
 *
 * @author Gubanov Andrey
 */
public class IncorrectFormDataException extends Exception {

    /**
     * Обработка исключительных ситуаций при проверке корректности введенных данных
     *
     * @param param параметр с ошибкой
     * @param value некорректное значение
     */
    public IncorrectFormDataException(String param, String value) {
        super(String.format("\"%s\": %s", param, value));
    }
}
