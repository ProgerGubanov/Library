package by.epam.library.action.librarian;

import by.epam.library.action.Action;
import by.epam.library.domain.*;
import by.epam.library.exception.PersistentException;
import by.epam.library.service.CardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Gubanov Andrey on 20.01.2016.
 */

public class LibrarianCardEditAction extends LibrarianAction {
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
        }
        return null;
    }
}
