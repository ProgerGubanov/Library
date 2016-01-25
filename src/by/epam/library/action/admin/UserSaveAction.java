package by.epam.library.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.library.local.MessageManager;
import org.apache.log4j.Logger;

import by.epam.library.action.Action;
import by.epam.library.service.UserService;
import by.epam.library.validator.Validator;
import by.epam.library.validator.ValidatorFactory;
import by.epam.library.domain.User;
import by.epam.library.exception.IncorrectFormDataException;
import by.epam.library.exception.PersistentException;

/**
 * Created by Gubanov Andrey on 05.01.2016.
 */

public class UserSaveAction extends AdministratorAction {
    private static Logger logger = Logger.getLogger(UserSaveAction.class);

    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        Forward forward = new Forward("/user/edit.html");
        try {
            Validator<User> validator = ValidatorFactory.createValidator(User.class);
            User user = validator.validate(request);
            UserService service = factory.getService(UserService.class);
            service.save(user);
            forward.getAttributes().put("identity", user.getIdentity());
            forward.getAttributes().put("message", MessageManager.getInstance(request).getProperty("message.userSaved"));
            logger.info(String.format("User \"%s\" saved user with identity %d", getAuthorizedUser().getLogin(), user.getIdentity()));
        } catch (IncorrectFormDataException e) {
            forward.getAttributes().put("message", MessageManager.getInstance(request).getProperty("message.incorrectData"));
            logger.warn(String.format("Incorrect data was found when user \"%s\" tried to save user", getAuthorizedUser().getLogin()), e);
        }
        return forward;
    }
}
