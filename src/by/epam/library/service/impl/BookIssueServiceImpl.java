package by.epam.library.service.impl;

import by.epam.library.dao.BookDao;
import by.epam.library.dao.OrderDao;
import by.epam.library.dao.RequestDao;
import by.epam.library.domain.Book;
import by.epam.library.domain.Order;
import by.epam.library.domain.Request;
import by.epam.library.exception.PersistentException;
import by.epam.library.service.BookIssueService;

/**
 * Реализация интерфейса BookIssueService
 *
 * @author Gubanov Andrey
 */
public class BookIssueServiceImpl extends ServiceImpl implements BookIssueService {
    /**
     * Выдача книги читателю
     *
     * @param book    книга
     * @param order   заказ
     * @param request заявка
     * @throws PersistentException
     */
    @Override
    public void issueBook(Book book, Order order, Request request) throws PersistentException {
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
