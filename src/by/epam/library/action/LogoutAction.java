package by.epam.library.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epam.library.domain.User;
import by.epam.library.exception.PersistentException;

/**
 * Выход (logout) пользователя
 *
 * @author Gubanov Andrey
 */
public class LogoutAction extends AuthorizedUserAction {
    private static Logger logger = Logger.getLogger(LogoutAction.class);

    /**
     * Выход (logout) пользователя
     *
     * @param request  запрос
     * @param response ответ
     * @return forward
     * @throws PersistentException
     */
    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        User user = getAuthorizedUser();
        logger.info(String.format("user \"%s\" is logged out", user.getLogin()));
        request.getSession(false).invalidate();
        return new Forward("/login.html");
    }
}
