package by.epam.library.action.librarian;

import by.epam.library.action.Action;
import by.epam.library.domain.*;
import by.epam.library.exception.PersistentException;
import by.epam.library.local.MessageManager;
import by.epam.library.service.*;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by Gubanov Andrey on 05.01.2016.
 */

/**
 * Возврат книги читателем в библиотеку
 */
public class LibrarianBookReturnAction extends LibrarianAction {
    private static Logger logger = Logger.getLogger(LibrarianBookReturnAction.class);

    /**
     * Возврат книги читателем в библиотеку
     *
     * @param request  запрос
     * @param response ответ
     * @return forward
     * @throws PersistentException
     */
    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        Forward forward = new Forward("/librarian/usages.html");
        try {
            Integer orderIdentity = Integer.parseInt(request.getParameter("orderIdentity"));

            Integer readerIdentity;
            if (request.getParameterMap().get("readerIdentity") != null) {
                readerIdentity = Integer.parseInt(request.getParameter("readerIdentity"));
            } else {
                readerIdentity = (Integer) request.getAttribute("readerIdentity");
            }

            OrderService orderService = factory.getService(OrderService.class);
            Order order = orderService.read(orderIdentity);
            order.setDateActualReturn(new Date());

            BookService bookService = factory.getService(BookService.class);
            Book book = bookService.read(order.getBook().getIdentity());
            book.setBookStatus(BookStatus.INLIBRARY);

            BookReturnService bookReturnService = factory.getService(BookReturnService.class);
            // изменяем местоположение книги, проставляем дату возврата книги
            bookReturnService.returnBook(book, order);

            forward.getAttributes().put("message", MessageManager.getInstance(request).getProperty("message.bookReturned"));
            forward.getAttributes().put("readerIdentity", readerIdentity);
            logger.info(String.format("User \"%s\" returned book with order identity %d", getAuthorizedUser().getLogin(), order.getIdentity()));
        } catch (NumberFormatException e) {
            forward.getAttributes().put("message", MessageManager.getInstance(request).getProperty("message.incorrectData"));
            logger.warn(String.format("Incorrect data was found when user \"%s\" tried to save returned book", getAuthorizedUser().getLogin()), e);
        }
        return forward;
    }
}
