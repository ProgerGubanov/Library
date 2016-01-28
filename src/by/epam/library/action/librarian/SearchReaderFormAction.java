package by.epam.library.action.librarian;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.library.action.Action;
import by.epam.library.exception.PersistentException;

/**
 * Поиск читателей
 *
 * @author Gubanov Andrey
 */
public class SearchReaderFormAction extends LibrarianAction {
    /**
     * Поиск читателей
     *
     * @param request  запрос
     * @param response ответ
     * @return forward
     * @throws PersistentException
     */
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
