package by.epam.library.service;

import by.epam.library.domain.Book;
import by.epam.library.domain.Order;
import by.epam.library.exception.PersistentException;

/**
 * Created by Gubanov Andrey on 22.01.2016.
 */

public interface BookReturnService extends Service {
    void returnBook(Book book, Order order) throws PersistentException;
}
