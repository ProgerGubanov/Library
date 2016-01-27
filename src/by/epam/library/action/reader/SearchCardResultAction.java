package by.epam.library.action.reader;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.library.service.CardService;
import by.epam.library.action.Action;
import by.epam.library.domain.Card;
import by.epam.library.exception.PersistentException;

/**
 * Created by Gubanov Andrey on 10.01.2016.
 */

/**
 * Вывод результата поиска карточки книги
 */
public class SearchCardResultAction extends ReaderAction {

    /**
     * Вывод результата поиска карточки книги
     *
     * @param request  запрос
     * @param response ответ
     * @return forward
     * @throws PersistentException
     */
    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        Forward forward = new Forward("/search/card/result.jsp", false);
        Integer readerIdentity = (Integer) request.getAttribute("readerIdentity");
        if (readerIdentity != null) {
            forward.getAttributes().put("readerIdentity", readerIdentity);
        }
        List<Card> cards;
        CardService service = factory.getService(CardService.class);
        String author = request.getParameter("author");
        String title = request.getParameter("title");
        String isbn = null;
        try {
            isbn = request.getParameter("isbn");
        } catch (NumberFormatException e) {
        }
        if (isbn != null) {
            cards = service.findByIsbn(isbn);
        } else if (title != null) {
            cards = service.findByTitle(title);
        } else {
            cards = service.findByAuthor(author);
        }
        request.setAttribute("cards", cards);
        return forward;
    }
}
