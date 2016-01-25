package by.epam.library.action.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.library.action.Action;
import by.epam.library.service.UserService;
import by.epam.library.domain.User;
import by.epam.library.exception.PersistentException;

/**
 * Created by Gubanov Andrey on 05.01.2016.
 */

public class UserListAction extends AdministratorAction {
    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        UserService service = factory.getService(UserService.class);
        List<User> users = service.findAllUsers();
        request.setAttribute("users", users);
        return null;
    }
}
