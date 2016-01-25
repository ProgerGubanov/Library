package by.epam.library.dao;

import by.epam.library.domain.Entity;
import by.epam.library.exception.PersistentException;

/**
 * Created by Gubanov Andrey on 16.12.2015.
 */

public interface Dao<Type extends Entity> {
    Integer create(Type entity) throws PersistentException;

    Type read(Integer identity) throws PersistentException;

    void update(Type entity) throws PersistentException;

    void delete(Integer identity) throws PersistentException;
}
