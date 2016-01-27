package by.epam.library.action;

/**
 * Created by Gubanov Andrey on 10.01.2016.
 */

/**
 * Интерфейс фабрики действий
 */
public interface ActionManagerFactory {
    /**
     * Получение ActionManager
     *
     * @return ActionManager
     */
    ActionManager getManager();
}
