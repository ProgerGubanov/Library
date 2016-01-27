package by.epam.library.action;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.epam.library.service.UserService;
import by.epam.library.domain.Role;
import by.epam.library.domain.User;
import by.epam.library.exception.PersistentException;
import by.epam.library.local.MessageManager;

/**
 * Created by Gubanov Andrey on 10.01.2016.
 */

/**
 * Действие авторизации пользователя
 */
public class LoginAction extends Action {
    private static Logger logger = Logger.getLogger(LoginAction.class);

    private static Map<Role, List<MenuItem>> menu = new ConcurrentHashMap<>();

    /**
     * Получение списка ролей
     *
     * @return Set<Role> список ролей
     */
    @Override
    public Set<Role> getAllowRoles() {
        return null;
    }

    /**
     * Заполнение меню авторизованного пользователя
     *
     * @param request  запрос
     * @param response ответ
     * @return Forward
     * @throws PersistentException
     */
    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        if (login != null && password != null) {
            UserService service = factory.getService(UserService.class);
            User user = service.findByLoginAndPassword(login, password);
            if (user != null) {
                menu.put(Role.ADMINISTRATOR, new ArrayList<>(Arrays.asList(
                        new MenuItem("/user/list.html", MessageManager.getInstance(request).getProperty("menu.users"))
                )));

                menu.put(Role.READER, new ArrayList<>(Arrays.asList(
                        new MenuItem("/search/card/form.html", MessageManager.getInstance(request).getProperty("menu.bookSearch")),
                        new MenuItem("/reader/requestlist.html", MessageManager.getInstance(request).getProperty("menu.myRequests")),
                        new MenuItem("/reader/orderlist.html", MessageManager.getInstance(request).getProperty("menu.myBooks"))
                )));
                menu.put(Role.LIBRARIAN, new ArrayList<>(Arrays.asList(
                        new MenuItem("/search/reader/form.html", MessageManager.getInstance(request).getProperty("menu.readers")),
                        new MenuItem("/reader/requestlist.html", MessageManager.getInstance(request).getProperty("menu.requests")),
                        new MenuItem("/search/card/form.html", MessageManager.getInstance(request).getProperty("menu.books"))
                )));

                session.setAttribute("menu", menu.get(user.getRole()));
                session.setAttribute("authorizedUser", user);
                logger.info(String.format("user \"%s\" is logged in from %s (%s:%s)", login, request.getRemoteAddr(), request.getRemoteHost(), request.getRemotePort()));
                return new Forward("/index.html");
            } else {
                request.setAttribute("message", MessageManager.getInstance(request).getProperty("message.userNameOrPasswordIncorrect"));
                logger.info(String.format("user \"%s\" unsuccessfully tried to log in from %s (%s:%s)", login, request.getRemoteAddr(), request.getRemoteHost(), request.getRemotePort()));
            }
        }
        return null;
    }
}
