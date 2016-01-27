package by.epam.library.action.reader;

/**
 * Created by Андрей on 06.01.2016.
 */

import by.epam.library.action.Action;
import by.epam.library.domain.Role;

/**
 * Абстрактный класс действий читателя
 */
abstract public class ReaderAction extends Action {
    /**
     * Абстрактный класс действие читателя
     */
    public ReaderAction() {
        getAllowRoles().add(Role.READER);
        getAllowRoles().add(Role.LIBRARIAN);
    }
}
