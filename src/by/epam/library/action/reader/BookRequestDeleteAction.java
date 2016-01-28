package by.epam.library.action.reader;

import by.epam.library.action.Action;
import by.epam.library.exception.PersistentException;
import by.epam.library.local.MessageManager;
import by.epam.library.service.RequestService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Удаление заявки на книгу
 *
 * @author Gubanov Andrey
 */
public class BookRequestDeleteAction extends ReaderAction {
    private static Logger logger = Logger.getLogger(BookRequestDeleteAction.class);

    /**
     * Удаление заявки на книгу
     *
     * @param request  запрос
     * @param response ответ
     * @return forward
     * @throws PersistentException
     */
    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        Forward forward = new Forward("/reader/requestlist.html");
        try {
            RequestService service = factory.getService(RequestService.class);
            Integer identity = Integer.parseInt(request.getParameter("identity"));
            service.delete(identity);
            forward.getAttributes().put("message", MessageManager.getInstance(request).getProperty("message.requestDeleted"));
            logger.info(String.format("User \"%s\" deleted request with identity %d", getAuthorizedUser().getLogin(), identity));
        } catch (NumberFormatException e) {
            logger.warn(String.format("Incorrect data was found when user \"%s\" tried to delete request", getAuthorizedUser().getLogin()), e);
        }
        return forward;
    }

}
