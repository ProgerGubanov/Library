package by.epam.library.service;

import by.epam.library.dao.DaoFactory;

/**
 * Created by Gubanov Andrey on 22.01.2016.
 */

abstract public class ServiceImpl implements Service {
    protected DaoFactory factory = null;

    public void setDaoFactory(DaoFactory factory) {
        this.factory = factory;
    }
}
