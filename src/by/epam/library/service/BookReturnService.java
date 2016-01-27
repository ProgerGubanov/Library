package by.epam.library.service;

import by.epam.library.domain.Book;
import by.epam.library.domain.Order;
import by.epam.library.exception.PersistentException;

/**
 * Created by Gubanov Andrey on 22.01.2016.
 */

/**
 * Интерфейс сервиса возврата книги в библиотеку
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
