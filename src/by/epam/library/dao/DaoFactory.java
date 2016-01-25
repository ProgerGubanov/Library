package by.epam.library.dao;

import by.epam.library.exception.PersistentException;

/**
 * Created by Gubanov Andrey on 16.12.2015.
 */

public interface DaoFactory {
    <Type extends Dao<?>> Type createDao(Class<Type> key) throws PersistentException;

    void close();
}
