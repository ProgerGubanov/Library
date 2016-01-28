package by.epam.library.action.admin;

import by.epam.library.action.Action;
import by.epam.library.domain.Role;

/**
 * Абстрактный класс действий администратора
 *
 * @author Gubanov Andrey
 */
abstract public class AdministratorAction extends Action {

    /**
     * Действия администратора
     */
    public AdministratorAction() {
        getAllowRoles().add(Role.ADMINISTRATOR);
    }
}
