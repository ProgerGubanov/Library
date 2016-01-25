package by.epam.library.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.library.exception.PersistentException;

/**
 * Created by Gubanov Andrey on 10.01.2016.
 */

public interface ActionManager {
    Action.Forward execute(Action action, HttpServletRequest request, HttpServletResponse response) throws PersistentException;

    void close();
}
