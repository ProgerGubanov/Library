package by.epam.library.service;

import by.epam.library.dao.BookDao;
import by.epam.library.domain.Book;
import by.epam.library.exception.PersistentException;

/**
 * Created by Gubanov Andrey on 18.01.2016.
 */

public class BookServiceImpl extends ServiceImpl implements BookService {
    @Override
    public void save(Book book) throws PersistentException {
        BookDao dao = factory.createDao(BookDao.class);
        if (book.getIdentity() != null) {
            dao.update(book);
        } else {
            book.setIdentity(dao.create(book));
        }
    }

    @Override
    public Book read(Integer identity) throws PersistentException {
        BookDao dao = factory.createDao(BookDao.class);
        return dao.read(identity);
    }
}
