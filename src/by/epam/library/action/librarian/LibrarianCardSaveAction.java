package by.epam.library.action.librarian;

import by.epam.library.action.Action;
import by.epam.library.domain.Card;
import by.epam.library.exception.IncorrectFormDataException;
import by.epam.library.exception.PersistentException;
import by.epam.library.local.MessageManager;
import by.epam.library.service.CardService;
import by.epam.library.validator.Validator;
import by.epam.library.validator.ValidatorFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Gubanov Andrey on 20.01.2016.
 */

/**
 * Сохранение карточки книги
 */
public class LibrarianCardSaveAction extends LibrarianAction {
    private static Logger logger = Logger.getLogger(LibrarianCardSaveAction.class);

    /**
     * Сохранение карточки книги
     *
     * @param request  запрос
     * @param response ответ
     * @return forward
     * @throws PersistentException
     */
    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        Forward forward = new Forward("/search/card/edit.html");
        try {
            Validator<Card> validator = ValidatorFactory.createValidator(Card.class);
            Card card = validator.validate(request);
            CardService service = factory.getService(CardService.class);
            service.save(card);
            forward.getAttributes().put("identity", card.getIdentity());
            forward.getAttributes().put("message", MessageManager.getInstance(request).getProperty("message.bookSaved"));
            logger.info(String.format("User \"%s\" saved card with identity %d", getAuthorizedUser().getLogin(), card.getIdentity()));
        } catch (IncorrectFormDataException e) {
            forward.getAttributes().put("message", MessageManager.getInstance(request).getProperty("message.incorrectParameter") + " " + e.getMessage());
            logger.warn(String.format("Incorrect data was found when user \"%s\" tried to save card", getAuthorizedUser().getLogin()), e);
        }
        return forward;
    }
}
