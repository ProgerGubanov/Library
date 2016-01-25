package by.epam.library.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.library.action.Action;
import by.epam.library.domain.Order;
import by.epam.library.domain.Request;
import by.epam.library.service.OrderService;
import by.epam.library.service.RequestService;
import by.epam.library.service.UserService;
import by.epam.library.domain.Role;
import by.epam.library.domain.User;
import by.epam.library.exception.PersistentException;

import java.util.List;

/**
 * Created by Gubanov Andrey on 05.01.2016.
 */

public class UserEditAction extends AdministratorAction {
    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        request.setAttribute("roles", Role.values());
        try {
            Integer identity = (Integer) request.getAttribute("identity");
            if (identity == null) {
                identity = Integer.parseInt(request.getParameter("identity"));
            }
            UserService service = factory.getService(UserService.class);
            User user = service.findByIdentity(identity);
            if (user != null) {
                request.setAttribute("user", user);
                RequestService requestService = factory.getService(RequestService.class);
                List<Request> requests = requestService.readByIdUser(identity);
                OrderService orderService = factory.getService(OrderService.class);
                List<Order> ordersReader = orderService.readByIdUser(identity, false);
                List<Order> ordersLibrarian = orderService.readByIdLibrarian(identity);
                Boolean isUserUsages = requests.size() > 0 || ordersReader.size() > 0 || ordersLibrarian.size() > 0;
                request.setAttribute("isUserUsages", isUserUsages);
            }
        } catch (NumberFormatException e) {
        }
        return null;
    }
}
