package by.epam.library.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.library.exception.PersistentException;

/**
 * Интерфейс менеджера действий
 *
 * @author Gubanov Andrey
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
