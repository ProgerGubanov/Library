package by.epam.library.action;

/**
 * Интерфейс фабрики действий
 *
 * @author Gubanov Andrey
 */
public interface ActionManagerFactory {
    /**
     * Получение ActionManager
     *
     * @return ActionManager
     */
    ActionManager getManager();
}
