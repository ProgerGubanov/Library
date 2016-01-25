package by.epam.library.action.librarian;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.library.action.Action;
import by.epam.library.exception.PersistentException;

/**
 * Created by Gubanov Andrey on 05.01.2016.
 */

public class SearchReaderFormAction extends LibrarianAction {

    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        Forward forward = new Forward("/search/reader/form.jsp", false);
        Integer readerIdentity;
        if (request.getParameterMap().get("readerIdentity") != null) {
            readerIdentity = Integer.parseInt(request.getParameter("readerIdentity"));
        } else {
            readerIdentity = (Integer) request.getAttribute("readerIdentity");
        }
        forward.getAttributes().put("readerIdentity", readerIdentity);
        return forward;
    }
}
