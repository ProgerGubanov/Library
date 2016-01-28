package by.epam.library.action.reader;

/**
 * Created by Андрей on 06.01.2016.
 */

import by.epam.library.action.Action;
import by.epam.library.domain.Role;

/**
 * Абстрактный класс действий читателя
 *
 * @author Gubanov Andrey
 */
abstract public class ReaderAction extends Action {
    /**
     * Абстрактный класс действий читателя
     */
    public ReaderAction() {
        getAllowRoles().add(Role.READER);
        getAllowRoles().add(Role.LIBRARIAN);
    }
}
