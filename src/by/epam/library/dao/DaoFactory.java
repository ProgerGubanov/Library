package by.epam.library.dao;

import by.epam.library.exception.PersistentException;

/**
 * Created by Gubanov Andrey on 16.12.2015.
 */

/**
 * Интерфейс фабрики ДАО
 */
public interface DaoFactory {
    /**
     * Создание ДАО
     *
     * @param key
     * @param <Type>
     * @return <Type extends Dao<?>> Type
     * @throws PersistentException
     */
    <Type extends Dao<?>> Type createDao(Class<Type> key) throws PersistentException;

    /**
     * Закрытие соединения
     */
    void close();
}
