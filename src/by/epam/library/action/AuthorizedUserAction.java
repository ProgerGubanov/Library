package by.epam.library.action;

import java.util.Arrays;

import by.epam.library.domain.Role;

/**
 * Created by Gubanov Andrey on 10.01.2016.
 */

/**
 * Действие при авторизации пользователя
 */
public abstract class AuthorizedUserAction extends Action {
    /**
     * Действие при авторизации пользователя
     */
    public AuthorizedUserAction() {
        getAllowRoles().addAll(Arrays.asList(Role.values()));
    }
}
