package by.epam.library.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.library.service.ServiceFactory;
import by.epam.library.exception.PersistentException;

/**
 * Created by Gubanov Andrey on 10.01.2016.
 */

public class ActionManagerImpl implements ActionManager {
    private ServiceFactory factory;

    public ActionManagerImpl(ServiceFactory factory) {
        this.factory = factory;
    }

    @Override
    public Action.Forward execute(Action action, HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        action.setFactory(factory);
        HttpSession session = request.getSession(false);
        if (session != null) {
            @SuppressWarnings("unchecked")
            Map<String, Object> attributes = (Map<String, Object>) session.getAttribute("redirectedData");
            if (attributes != null) {
                for (String key : attributes.keySet()) {
                    request.setAttribute(key, attributes.get(key));
                }
                session.removeAttribute("redirectedData");
            }
        }
        Action.Forward forward = action.exec(request, response);
        if (session != null && forward != null && !forward.getAttributes().isEmpty()) {
            session.setAttribute("redirectedData", forward.getAttributes());
        }
        return forward;
    }

    @Override
    public void close() {
        factory.close();
    }
}
