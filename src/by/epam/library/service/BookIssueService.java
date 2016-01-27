package by.epam.library.service;

import by.epam.library.domain.Book;
import by.epam.library.domain.Order;
import by.epam.library.domain.Request;
import by.epam.library.exception.PersistentException;

/**
 * Created by Gubanov Andrey on 22.01.2016.
 */

/**
 * Интерфейс сервиса выдачи книги
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
