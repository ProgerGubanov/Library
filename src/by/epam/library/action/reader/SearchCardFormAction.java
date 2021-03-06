package by.epam.library.action.reader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.library.action.Action;
import by.epam.library.exception.PersistentException;

/**
 * Поиск карточки книги
 *
 * @author Gubanov Andrey
 */
public class SearchCardFormAction extends ReaderAction {

    /**
     * Поиск карточки книги
     *
     * @param request  запрос
     * @param response ответ
     * @return forward
     * @throws PersistentException
     */
    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        Forward forward = new Forward("/search/card/form.jsp", false);
        Integer readerIdentity = (Integer) request.getAttribute("readerIdentity");
        if (readerIdentity != null) {
            forward.getAttributes().remove("readerIdentity");
        }
        return forward;
    }
}
