package by.epam.library.action;

import by.epam.library.service.ServiceFactory;

/**
 * Реализация фабрики ActionManagerFactory
 *
 * @author Gubanov Andrey
 */
public class ActionManagerFactoryImpl implements ActionManagerFactory {
    private ServiceFactory factory;

    /**
     * Конструктор
     *
     * @param factory
     */
    public ActionManagerFactoryImpl(ServiceFactory factory) {
        this.factory = factory;
    }

    /**
     * Получение ActionManager
     *
     * @return ActionManager
     */
    public ActionManager getManager() {
        return new ActionManagerImpl(factory);
    }
}
