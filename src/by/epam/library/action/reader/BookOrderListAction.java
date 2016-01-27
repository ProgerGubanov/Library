package by.epam.library.action.reader;

import by.epam.library.action.Action;
import by.epam.library.domain.Order;
import by.epam.library.domain.User;
import by.epam.library.exception.PersistentException;
import by.epam.library.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Gubanov Andrey on 07.01.2016.
 */

/**
 * Получение списка невозвращенных книг читателя
 */
public class BookOrderListAction extends ReaderAction {

    /**
     * Получение списка невозвращенных книг читателя
     *
     * @param request  запрос
     * @param response ответ
     * @return forward
     * @throws PersistentException
     */
    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        OrderService service = factory.getService(OrderService.class);
        User authorizedUser = (User) request.getSession().getAttribute("authorizedUser");
        List<Order> orders = service.readByIdUser(authorizedUser.getIdentity(), true);
        request.setAttribute("orders", orders);
        return null;
    }

}
