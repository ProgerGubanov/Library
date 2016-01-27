package by.epam.library.action.admin;

import by.epam.library.action.Action;
import by.epam.library.domain.Role;

/**
 * Created by Gubanov Andrey on 05.01.2016.
 */

/**
 * Абстрактный класс действий администратора
 */
abstract public class AdministratorAction extends Action {

    /**
     * Действия администратора
     */
    public AdministratorAction() {
        getAllowRoles().add(Role.ADMINISTRATOR);
    }
}
