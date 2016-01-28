package by.epam.library.action;

import java.util.Arrays;

import by.epam.library.domain.Role;

/**
 * Действие при авторизации пользователя
 *
 * @author Gubanov Andrey
 */
public abstract class AuthorizedUserAction extends Action {
    /**
     * Действие при авторизации пользователя
     */
    public AuthorizedUserAction() {
        getAllowRoles().addAll(Arrays.asList(Role.values()));
    }
}
