package by.epam.library.action.reader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.library.domain.Book;
import by.epam.library.service.CardService;
import by.epam.library.action.Action;
import by.epam.library.domain.Card;
import by.epam.library.exception.PersistentException;

import java.util.List;

/**
 * Created by Gubanov Andrey on 05.01.2016.
 */

/**
 * Вывод подробной информации о карточке книги и всех ее экземплярах
 */
public class CardUsageListAction extends ReaderAction {
    /**
     * Вывод подробной информации о карточке книги и всех ее экземплярах
     *
     * @param request  запрос
     * @param response ответ
     * @return forward
     * @throws PersistentException
     */
    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        Forward forward = new Forward("/search/card/usages.jsp", false);
        Integer readerIdentity = (Integer) request.getAttribute("readerIdentity");
        if (readerIdentity != null) {
            forward.getAttributes().put("readerIdentity", readerIdentity);
        }
        try {
            Integer identity;
            if ((request.getParameterMap().get("identity") != null)) {
                identity = Integer.parseInt(request.getParameter("identity"));
            } else {
                identity = (Integer) request.getAttribute("identity");
            }
            if (identity != null) {
                forward.getAttributes().put("identity", identity);
            }
            String message = (String) request.getAttribute("message");
            if (message != null) {
                request.setAttribute("message", message);
            }
            CardService cardService = factory.getService(CardService.class);
            Card card = cardService.findByIdentity(identity);
            if (card != null) {
                request.setAttribute("card", card);
                List<Book> books = cardService.findByIdCard(identity);
                request.setAttribute("books", books);
                int countFreeBooks = cardService.countFreeBooks(identity);
                request.setAttribute("countFreeBooks", countFreeBooks);
            }
        } catch (NumberFormatException e) {
        }
        return forward;
    }
}
