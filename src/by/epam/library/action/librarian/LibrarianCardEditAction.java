package by.epam.library.action.librarian;

import by.epam.library.action.Action;
import by.epam.library.domain.*;
import by.epam.library.exception.PersistentException;
import by.epam.library.service.CardService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Корректировка карточки книги
 *
 * @author Gubanov Andrey
 */
public class LibrarianCardEditAction extends LibrarianAction {
    private static Logger logger = Logger.getLogger(LibrarianCardEditAction.class);

    /**
     * Корректировка карточки книги
     *
     * @param request  запрос
     * @param response ответ
     * @return forward
     * @throws PersistentException
     */
    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        try {
            Integer identity = (Integer) request.getAttribute("identity");
            if (identity == null) {
                identity = Integer.parseInt(request.getParameter("identity"));
            }
            CardService service = factory.getService(CardService.class);
            Card card = service.findByIdentity(identity);
            if (card != null) {
                request.setAttribute("card", card);
            }
        } catch (NumberFormatException e) {
            logger.warn(String.format("Incorrect data was found when user \"%s\" tried to card editing", getAuthorizedUser().getLogin()), e);
        }
        return null;
    }
}
