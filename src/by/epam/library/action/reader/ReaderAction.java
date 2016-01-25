package by.epam.library.action.reader;

/**
 * Created by Андрей on 06.01.2016.
 */

import by.epam.library.action.Action;
import by.epam.library.domain.Role;

abstract public class ReaderAction extends Action {
    public ReaderAction() {
        getAllowRoles().add(Role.READER);
        getAllowRoles().add(Role.LIBRARIAN);
    }
}
