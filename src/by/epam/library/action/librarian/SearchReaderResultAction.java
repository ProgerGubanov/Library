package by.epam.library.action.librarian;

import by.epam.library.action.Action;
import by.epam.library.domain.User;
import by.epam.library.exception.PersistentException;
import by.epam.library.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Вывод результата поиска читателей
 *
 * @author Gubanov Andrey
 */
public class SearchReaderResultAction extends LibrarianAction {

    /**
     * Вывод результата поиска читателей
     *
     * @param request  запрос
     * @param response ответ
     * @return forward
     * @throws PersistentException
     */
    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        Forward forward = new Forward("/search/reader/result.jsp", false);
        Integer readerIdentity;
        if (request.getParameterMap().get("readerIdentity") != null) {
            readerIdentity = Integer.parseInt(request.getParameter("readerIdentity"));
        } else {
            readerIdentity = (Integer) request.getAttribute("readerIdentity");
        }
        forward.getAttributes().put("readerIdentity", readerIdentity);
        UserService service = factory.getService(UserService.class);
        List<User> users;
        String surname = request.getParameter("surname");
        String subscription = request.getParameter("subscription");
        if (subscription != null) {
            users = service.findBySubscription(subscription);
        } else {
            users = service.findBySurname(surname);
        }
        request.setAttribute("users", users);
        return forward;
    }
}
