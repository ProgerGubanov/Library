package by.epam.library.exception;

/**
 * Created by Gubanov Andrey on 16.12.2015.
 */

/**
 * Обработка исключительных ситуаций при проверке корректности введенных данных
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
