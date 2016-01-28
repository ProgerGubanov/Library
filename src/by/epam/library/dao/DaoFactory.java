package by.epam.library.dao;

import by.epam.library.exception.PersistentException;

/**
 * Интерфейс фабрики ДАО
 *
 * @author Gubanov Andrey
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
