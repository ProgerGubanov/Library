package by.epam.library.action.librarian;

import by.epam.library.action.Action;
import by.epam.library.domain.Role;

/**
 * Абстрактный класс действий библиотекаря
 *
 * @author Gubanov Andrey
 */
abstract public class LibrarianAction extends Action {

    /**
     * Действия библиотекаря
     */
    public LibrarianAction() {
        getAllowRoles().add(Role.LIBRARIAN);
    }
}
