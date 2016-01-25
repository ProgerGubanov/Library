package by.epam.library.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.library.exception.PersistentException;

/**
 * Created by Gubanov Andrey on 22.01.2016.
 */

public class LocaleAction extends Action {

    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        Forward forward = new Forward("/login.jsp", false);
        String lang = request.getParameter("language");
        request.setAttribute("language", lang);
        return forward;
    }
}
