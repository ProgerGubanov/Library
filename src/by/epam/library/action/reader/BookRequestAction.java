package by.epam.library.action.reader;

import by.epam.library.action.Action;
import by.epam.library.domain.Card;
import by.epam.library.domain.Request;
import by.epam.library.domain.User;
import by.epam.library.exception.PersistentException;
import by.epam.library.local.MessageManager;
import by.epam.library.service.CardService;
import by.epam.library.service.RequestService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Формирование заявки на книгу
 *
 * @author Gubanov Andrey
 */
public class BookRequestAction extends ReaderAction {
    private static Logger logger = Logger.getLogger(BookRequestAction.class);

    /**
     * Формирование заявки на книгу
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
            Request bookRequest = new Request();
            Card card = new Card();
            card.setIdentity(Integer.parseInt(request.getParameter("identitycard")));
            bookRequest.setCard(card);
            User user = new User();
            user.setIdentity(Integer.parseInt(request.getParameter("identityuser")));
            bookRequest.setUser(user);
            bookRequest.setIsReadingRoom(Boolean.parseBoolean(request.getParameter("isreadingroom")));
            CardService cardService = factory.getService(CardService.class);
            int countFreeBooks = cardService.countFreeBooks(card.getIdentity());
            int countRequestThisBook = service.LocateRequestByIdUserAndIdCard(user.getIdentity(), card.getIdentity());
            if (((countFreeBooks >= 1) && (bookRequest.isReadingRoom())) || ((countFreeBooks >= 2) && (!bookRequest.isReadingRoom()))) {
                if (countRequestThisBook == 0) {
                    service.save(bookRequest);
                    forward.getAttributes().put("message", MessageManager.getInstance(request).getProperty("message.requestFormed"));
                    logger.info(String.format("User \"%s\" created request with identity card %d", getAuthorizedUser().getLogin(), card.getIdentity()));
                } else {
                    forward.getAttributes().put("message", MessageManager.getInstance(request).getProperty("message.requestRejectedBookAlreadyRequested"));
                }
            } else {
                forward.getAttributes().put("message", MessageManager.getInstance(request).getProperty("message.requestRejectedEnoughBooksAvailable"));
            }
        } catch (NumberFormatException e) {
            logger.warn(String.format("Incorrect data was found when user \"%s\" tried to create request", getAuthorizedUser().getLogin()), e);
        }
        return forward;
    }
}
