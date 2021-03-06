package by.epam.library.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.library.exception.PersistentException;

/**
 * Переход на первый пункт меню пользователя
 *
 * @author Gubanov Andrey
 */
public class MainAction extends AuthorizedUserAction {
    /**
     * Переход на первый пункт меню пользователя
     *
     * @param request  запрос
     * @param response ответ
     * @return forward
     * @throws PersistentException
     */
    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        @SuppressWarnings("unchecked")
        List<MenuItem> menu = (List<MenuItem>) request.getSession(false).getAttribute("menu");
        return new Forward(menu.get(0).getUrl());
    }
}
