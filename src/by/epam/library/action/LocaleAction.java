package by.epam.library.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.library.exception.PersistentException;

/**
 * Действие локализации
 *
 * @author Gubanov Andrey
 */
public class LocaleAction extends Action {

    /**
     * Установка аттрибута выбранного языка
     *
     * @param request  запрос
     * @param response ответ
     * @return forward
     * @throws PersistentException
     */
    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        Forward forward = new Forward("/login.jsp", false);
        String lang = request.getParameter("language");
        request.setAttribute("language", lang);
        return forward;
    }
}
