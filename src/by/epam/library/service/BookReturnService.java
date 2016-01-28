package by.epam.library.service;

import by.epam.library.domain.Book;
import by.epam.library.domain.Order;
import by.epam.library.exception.PersistentException;

/**
 * Интерфейс сервиса возврата книги в библиотеку
 *
 * @author Gubanov Andrey
 */
public interface BookReturnService extends Service {
    /**
     * Возврат книги в библиотеку
     *
     * @param book  книга
     * @param order заказ
     * @throws PersistentException
     */
    void returnBook(Book book, Order order) throws PersistentException;
}
