package by.epam.library.service;

import by.epam.library.domain.Book;
import by.epam.library.domain.Order;
import by.epam.library.domain.Request;
import by.epam.library.exception.PersistentException;

/**
 * Интерфейс сервиса выдачи книги
 *
 * @author Gubanov Andrey
 */
public interface BookIssueService extends Service {
    /**
     * Выдача книги читателю
     *
     * @param book    книга
     * @param order   заказ
     * @param request заявка
     * @throws PersistentException
     */
    void issueBook(Book book, Order order, Request request) throws PersistentException;
}
