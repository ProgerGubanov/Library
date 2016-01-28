package by.epam.library.service.impl;

import by.epam.library.dao.DaoFactory;
import by.epam.library.service.Service;

/**
 * Реализация интерфейса Service
 *
 * @author Gubanov Andrey
 */
abstract public class ServiceImpl implements Service {
    protected DaoFactory factory = null;

    /**
     * Установка фабрики ДАО
     *
     * @param factory фабрика ДАО
     */
    public void setDaoFactory(DaoFactory factory) {
        this.factory = factory;
    }
}
