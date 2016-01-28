package by.epam.library.service;

import by.epam.library.domain.Book;
import by.epam.library.exception.PersistentException;

/**
 * Интерфейс сервиса для работы с книгами
 *
 * @author Gubanov Andrey
 */
public interface BookService extends Service {
    /**
     * Чтение информации о книге по уникалоному коду
     *
     * @param identity уникальный код записи
     * @return Book информация о книге
     * @throws PersistentException
     */
    Book read(Integer identity) throws PersistentException;

    /**
     * Сохранение информации о книге
     *
     * @param book книга
     * @throws PersistentException
     */
    void save(Book book) throws PersistentException;
}
