package by.epam.library.action.reader;

import by.epam.library.action.Action;
import by.epam.library.domain.Request;
import by.epam.library.domain.User;
import by.epam.library.exception.PersistentException;
import by.epam.library.service.RequestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Gubanov Andrey on 08.01.2016.
 */

/**
 * Формирование заявки на книгу
 */
public class BookRequestListAction extends ReaderAction {

    /**
     * Формирование заявки на книгу
     *
     * @param request  запрос
     * @param response ответ
     * @return forward
     * @throws PersistentException
     */
    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        RequestService service = factory.getService(RequestService.class);
        User authorizedUser = (User) request.getSession().getAttribute("authorizedUser");
        List<Request> requests;
        if (authorizedUser.getRole().getIdentity() == 2) {
            requests = service.readByIdUser(authorizedUser.getIdentity());
        } else {
            requests = service.read();
        }
        request.setAttribute("requests", requests);
        return null;
    }
}
