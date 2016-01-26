package by.epam.library.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.library.local.MessageManager;
import org.apache.log4j.Logger;

import by.epam.library.service.UserService;
import by.epam.library.domain.User;
import by.epam.library.exception.PersistentException;

/**
 * Created by Gubanov Andrey on 12.01.2016.
 */

public class ProfileSaveAction extends AuthorizedUserAction {
    private static Logger logger = Logger.getLogger(ProfileSaveAction.class);

    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        Forward forward = new Forward("/profile/edit.html");
        String oldPassword = request.getParameter("old-password");
        String newPassword = request.getParameter("new-password");
        String confirmPassword = request.getParameter("confirmPassword");
        User authorizedUser = getAuthorizedUser();
        if (oldPassword != null && newPassword != null) {
            UserService service = factory.getService(UserService.class);
            User user = service.findByLoginAndPassword(authorizedUser.getLogin(), oldPassword);
            if (user != null) {
                if (newPassword.equals(confirmPassword)) {
                    user.setPassword(newPassword);
                    service.save(user);
                    forward.getAttributes().put("message", MessageManager.getInstance(request).getProperty("message.passwordChanged"));
                    logger.info(String.format("User \"%s\" changed password", authorizedUser.getLogin()));
                } else {
                    forward.getAttributes().put("message", MessageManager.getInstance(request).getProperty("message.newPasswordNotMatch"));
                    logger.info(String.format("User \"%s\" tried to change password and specified the incorrect new password", authorizedUser.getLogin()));
                }
            } else {
                forward.getAttributes().put("message", MessageManager.getInstance(request).getProperty("message.oldPasswordIsNotRecognized"));
                logger.info(String.format("User \"%s\" tried to change password and specified the incorrect previous password", authorizedUser.getLogin()));
            }
        } else {
            logger.warn(String.format("Incorrect data was found when user \"%s\" tried to change password", authorizedUser.getLogin()));
        }
        return forward;
    }
}
