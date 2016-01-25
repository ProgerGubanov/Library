package by.epam.library.service;

import by.epam.library.domain.Book;
import by.epam.library.exception.PersistentException;

/**
 * Created by Gubanov Andrey on 22.01.2016.
 */

public interface BookService extends Service {
    Book read(Integer identity) throws PersistentException;

    void save(Book book) throws PersistentException;
}
