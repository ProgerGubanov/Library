package by.epam.library.action.admin;

import by.epam.library.action.Action;
import by.epam.library.domain.Role;

/**
 * Created by Gubanov Andrey on 05.01.2016.
 */

abstract public class AdministratorAction extends Action {
    public AdministratorAction() {
        getAllowRoles().add(Role.ADMINISTRATOR);
    }
}
