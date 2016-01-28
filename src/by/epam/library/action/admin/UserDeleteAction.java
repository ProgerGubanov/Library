package by.epam.library.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.library.domain.Order;
import by.epam.library.domain.Request;
import by.epam.library.local.MessageManager;
import by.epam.library.service.OrderService;
import by.epam.library.service.RequestService;
import org.apache.log4j.Logger;

import by.epam.library.action.Action;
import by.epam.library.service.UserService;
import by.epam.library.exception.PersistentException;

import java.util.List;

/**
 * Удаление пользователя
 *
 * @author Gubanov Andrey
 */
public class UserDeleteAction extends AdministratorAction {
    private static Logger logger = Logger.getLogger(UserDeleteAction.class);

    /**
     * Удаление пользователя
     *
     * @param request  запрос
     * @param response ответ
     * @return forward
     * @throws PersistentException
     */
    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        Forward forward = new Forward("/user/list.html");
        try {
            UserService service = factory.getService(UserService.class);
            Integer identity = Integer.parseInt(request.getParameter("identity"));
            RequestService requestService = factory.getService(RequestService.class);
            List<Request> requests = requestService.readByIdUser(identity);
            OrderService orderService = factory.getService(OrderService.class);
            List<Order> ordersReader = orderService.readByIdUser(identity, false);
            List<Order> ordersLibrarian = orderService.readByIdLibrarian(identity);
            if (requests.size() > 0 || ordersReader.size() > 0 || ordersLibrarian.size() > 0) {
                forward.getAttributes().put("message", MessageManager.getInstance(request).getProperty("message.userHasNotRemoved"));
            } else {
                service.delete(identity);
                forward.getAttributes().put("message", MessageManager.getInstance(request).getProperty("message.userDeleted"));
                logger.info(String.format("User \"%s\" deleted user with identity %d", getAuthorizedUser().getLogin(), identity));
            }
        } catch (NumberFormatException e) {
            logger.warn(String.format("Incorrect data was found when user \"%s\" tried to delete user", getAuthorizedUser().getLogin()), e);
        }
        return forward;
    }
}
