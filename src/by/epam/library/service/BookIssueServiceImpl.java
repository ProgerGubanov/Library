package by.epam.library.service;

import by.epam.library.dao.BookDao;
import by.epam.library.dao.OrderDao;
import by.epam.library.dao.RequestDao;
import by.epam.library.domain.Book;
import by.epam.library.domain.Order;
import by.epam.library.domain.Request;
import by.epam.library.exception.PersistentException;

/**
 * Created by Gubanov Andrey on 22.01.2016.
 */

public class BookIssueServiceImpl extends ServiceImpl implements BookIssueService {
    @Override
    public void issueBook (Book book, Order order, Request request) throws PersistentException {
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
        RequestDao requestDao = factory.createDao(RequestDao.class);
        if (request != null) {  //если выдача книги по заявке
            requestDao.delete(request.getIdentity());
        }
    }
}
