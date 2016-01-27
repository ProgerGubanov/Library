package by.epam.library.exception;

/**
 * Created by Gubanov Andrey on 16.12.2015.
 */

/**
 * Обработка исключительных ситуаций
 */
public class PersistentException extends Exception {
    /**
     * Конструктор
     */
    public PersistentException() {
    }

    public PersistentException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Сообщение об ошибке
     *
     * @param message сообщение
     */
    public PersistentException(String message) {
        super(message);
    }

    /**
     * Конструктор
     *
     * @param cause причина ошибки
     */
    public PersistentException(Throwable cause) {
        super(cause);
    }
}
