package by.epam.library.action.librarian;

import by.epam.library.action.Action;
import by.epam.library.domain.Order;
import by.epam.library.domain.Request;
import by.epam.library.domain.User;
import by.epam.library.exception.PersistentException;
import by.epam.library.service.OrderService;
import by.epam.library.service.RequestService;
import by.epam.library.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Вывод информации о читателе
 *
 * @author Gubanov Andrey
 */
public class LibrarianUserInfoAction extends LibrarianAction {
    private static Logger logger = Logger.getLogger(LibrarianUserInfoAction.class);

    /**
     * Вывод информации о читателе
     *
     * @param request  запрос
     * @param response ответ
     * @return forward
     * @throws PersistentException
     */
    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        Forward forward = new Forward("/librarian/usages.jsp", false);
        try {
            Integer readerIdentity;
            if (request.getParameterMap().get("readerIdentity") != null) {
                readerIdentity = Integer.parseInt(request.getParameter("readerIdentity"));
            } else {
                readerIdentity = (Integer) request.getAttribute("readerIdentity");
            }

            UserService userService = factory.getService(UserService.class);
            User user = userService.findByIdentity(readerIdentity);
            request.setAttribute("user", user);

            RequestService requestService = factory.getService(RequestService.class);
            List<Request> requests;
            requests = requestService.readByIdUser(readerIdentity);
            request.setAttribute("requests", requests);

            OrderService orderService = factory.getService(OrderService.class);
            List<Order> orders = orderService.readByIdUser(readerIdentity, true);
            request.setAttribute("orders", orders);

            forward.getAttributes().put("readerIdentity", readerIdentity);

            String message = (String) request.getAttribute("message");
            if (message != null) {
                request.setAttribute("message", message);
            }
        } catch (NumberFormatException e) {
            logger.warn(String.format("Incorrect data was found when user \"%s\" tried to user info created", getAuthorizedUser().getLogin()), e);
        }
        return forward;
    }

}
