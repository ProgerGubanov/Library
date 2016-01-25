package by.epam.library.service;

import by.epam.library.exception.PersistentException;

/**
 * Created by Gubanov Andrey on 22.01.2016.
 */

public interface ServiceFactory {
    <Type extends Service> Type getService(Class<Type> key) throws PersistentException;

    void close();
}
