package by.epam.library.action.librarian;

import by.epam.library.action.Action;
import by.epam.library.domain.*;
import by.epam.library.exception.IncorrectFormDataException;
import by.epam.library.exception.PersistentException;
import by.epam.library.local.MessageManager;
import by.epam.library.service.*;
import by.epam.library.validator.Validator;
import by.epam.library.validator.ValidatorFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Сохранение экземпляра книги
 *
 * @author Gubanov Andrey
 */
public class LibrarianBookSaveAction extends LibrarianAction {
    private static Logger logger = Logger.getLogger(LibrarianBookSaveAction.class);

    /**
     * Сохранение экземпляра книги
     *
     * @param request  запрос
     * @param response ответ
     * @return forward
     * @throws PersistentException
     */
    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        Forward forward = new Forward("/search/card/usages.html");
        try {
            Validator<Book> validator = ValidatorFactory.createValidator(Book.class);
            Book book = validator.validate(request);
            BookService service = factory.getService(BookService.class);
            service.save(book);
            forward.getAttributes().put("identity", book.getCard().getIdentity());
            forward.getAttributes().put("message", MessageManager.getInstance(request).getProperty("message.instanceData") +
                    " " + book.getInventoryNumber() + " " + MessageManager.getInstance(request).getProperty("message.savedSuccessfully"));
            logger.info(String.format("User \"%s\" saved book with identity %d", getAuthorizedUser().getLogin(), book.getIdentity()));
        } catch (IncorrectFormDataException e) {
            forward.getAttributes().put("message", MessageManager.getInstance(request).getProperty("message.incorrectParameter") + " " + e.getMessage());
            logger.warn(String.format("Incorrect data was found when user \"%s\" tried to save book", getAuthorizedUser().getLogin()), e);
        }
        return forward;
    }
}