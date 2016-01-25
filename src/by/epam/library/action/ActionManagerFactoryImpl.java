package by.epam.library.action;

import by.epam.library.service.ServiceFactory;

/**
 * Created by Gubanov Andrey on 10.01.2016.
 */

public class ActionManagerFactoryImpl implements ActionManagerFactory {
    private ServiceFactory factory;

    public ActionManagerFactoryImpl(ServiceFactory factory) {
        this.factory = factory;
    }

    public ActionManager getManager() {
        return new ActionManagerImpl(factory);
    }
}
