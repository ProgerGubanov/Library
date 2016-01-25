package by.epam.library.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.library.exception.PersistentException;

/**
 * Created by Gubanov Andrey on 12.01.2016.
 */

public class ProfileEditAction extends AuthorizedUserAction {
    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        return null;
    }
}
