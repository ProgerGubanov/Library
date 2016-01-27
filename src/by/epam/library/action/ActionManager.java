package by.epam.library.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.library.exception.PersistentException;

/**
 * Created by Gubanov Andrey on 10.01.2016.
 */

/**
 * Интерфейс менеджера действий
 */
public interface ActionManager {
    /**
     * Выполнение перехода
     *
     * @param action   действие
     * @param request  запрос
     * @param response ответ
     * @return Forward
     * @throws PersistentException
     */
    Action.Forward execute(Action action, HttpServletRequest request, HttpServletResponse response) throws PersistentException;

    /**
     * Закрытие действия
     */
    void close();
}
