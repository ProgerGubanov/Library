package by.epam.library.service.impl;

import by.epam.library.dao.BookDao;
import by.epam.library.domain.Book;
import by.epam.library.exception.PersistentException;
import by.epam.library.service.BookService;

/**
 * Реализация интерфейса BookService
 *
 * @author Gubanov Andrey
 */
public class BookServiceImpl extends ServiceImpl implements BookService {
    /**
     * Сохранение информации о книге
     *
     * @param book книга
     * @throws PersistentException
     */
    @Override
    public void save(Book book) throws PersistentException {
        BookDao dao = factory.createDao(BookDao.class);
        if (book.getIdentity() != null) {
            dao.update(book);
        } else {
            book.setIdentity(dao.create(book));
        }
    }

    /**
     * Чтение информации о книге по уникалоному коду
     *
     * @param identity уникальный код записи
     * @return Book информация о книге
     * @throws PersistentException
     */
    @Override
    public Book read(Integer identity) throws PersistentException {
        BookDao dao = factory.createDao(BookDao.class);
        return dao.read(identity);
    }
}
