package by.epam.library.service;

import by.epam.library.dao.BookDao;
import by.epam.library.dao.OrderDao;
import by.epam.library.domain.Book;
import by.epam.library.domain.Order;
import by.epam.library.exception.PersistentException;

/**
 * Created by Gubanov Andrey on 22.01.2016.
 */

public class BookReturnServiceImpl extends ServiceImpl implements BookReturnService {
    @Override
    public void returnBook(Book book, Order order) throws PersistentException {
        BookDao bookDao = factory.createDao(BookDao.class);
        if (book.getIdentity() != null) {
            bookDao.update(book);
        } else {
            book.setIdentity(bookDao.create(book));
        }
        OrderDao orderDao = factory.createDao(OrderDao.class);
        if (order.getIdentity() != null) {
            orderDao.update(order);
        } else {
            order.setIdentity(orderDao.create(order));
        }
    }
}
