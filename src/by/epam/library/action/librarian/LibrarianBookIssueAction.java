package by.epam.library.action.librarian;

import by.epam.library.action.Action;
import by.epam.library.domain.*;
import by.epam.library.exception.PersistentException;
import by.epam.library.local.MessageManager;
import by.epam.library.service.*;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Gubanov Andrey on 20.01.2016.
 */

public class LibrarianBookIssueAction extends LibrarianAction {
    private static Logger logger = Logger.getLogger(LibrarianBookReturnAction.class);

    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        Forward forward = new Forward("/librarian/usages.html");
        try {
            Integer bookIdentity = Integer.parseInt(request.getParameter("bookIdentity"));
            Integer cardIdentity = Integer.parseInt(request.getParameter("identitycard"));
            Integer librarianIdentity = Integer.parseInt(request.getParameter("identityuser"));

            Integer readerIdentity;
            if (request.getParameterMap().get("readerIdentity") != null) {
                readerIdentity = Integer.parseInt(request.getParameter("readerIdentity"));
            } else {
                readerIdentity = (Integer) request.getAttribute("readerIdentity");
            }

            BookService bookService = factory.getService(BookService.class);
            Book book = bookService.read(bookIdentity);

            UserService userService = factory.getService(UserService.class);
            User librarian = userService.findByIdentity(librarianIdentity);

            RequestService requestService = factory.getService(RequestService.class);
            Request requestCard = requestService.readRequestByIdUserAndIdCard(readerIdentity, cardIdentity);
            User reader = userService.findByIdentity(readerIdentity);
            Boolean isReadingRoom = true;
            if (requestCard != null) {
                isReadingRoom = requestCard.isReadingRoom();
            }

            Order order = new Order();

            order.setBook(book);
            order.setUser(reader);
            order.setLibrarian(librarian);
            order.setIsReadingRoom(isReadingRoom);
            if (isReadingRoom) {
                order.setDatePlannedReturn(new Date());
                book.setBookStatus(BookStatus.INREADINGROOM);
            } else {
                Calendar c = new GregorianCalendar();
                c.setTimeInMillis(System.currentTimeMillis());
                c.add(Calendar.MONTH, 1);
                Date date = c.getTime();
                order.setDatePlannedReturn(date); // на месяц выдаем по абонементу
                book.setBookStatus(BookStatus.ONSUBSCRIPTION);
            }
            order.setDateIssue(new Date());

            BookIssueService bookIssueService = factory.getService(BookIssueService.class);
            // изменяем местоположение книги, создаем в заказах запись с выданной книгой, удаляем заявку
            bookIssueService.issueBook(book, order, requestCard);

            forward.getAttributes().put("message", MessageManager.getInstance(request).getProperty("message.book")+" "+
                    book.getInventoryNumber()+" "+MessageManager.getInstance(request).getProperty("message.successfullyIssued"));
            forward.getAttributes().put("readerIdentity", readerIdentity);
            logger.info(String.format("User \"%s\" issuing book with book identity %d", getAuthorizedUser().getLogin(), book.getIdentity()));
        } catch (NumberFormatException e) {
            forward.getAttributes().put("message", MessageManager.getInstance(request).getProperty("message.incorrectData"));
            logger.warn(String.format("Incorrect data was found when user \"%s\" tried to save issuing book", getAuthorizedUser().getLogin()), e);
        }
        return forward;
    }
}
