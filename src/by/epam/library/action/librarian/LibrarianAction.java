package by.epam.library.action.librarian;

import by.epam.library.action.Action;
import by.epam.library.domain.Role;

/**
 * Created by Gubanov Andrey on 05.01.2016.
 */

abstract public class LibrarianAction extends Action {
    public LibrarianAction() {
        getAllowRoles().add(Role.LIBRARIAN);
    }
}
