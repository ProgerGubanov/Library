package by.epam.library.dao;

import by.epam.library.domain.Entity;
import by.epam.library.exception.PersistentException;

/**
 * Created by Gubanov Andrey on 16.12.2015.
 */

/**
 * Интерфейс для работы с ДАО
 * @param <Type> объект
 */
public interface Dao<Type extends Entity> {
    /**
     * Создание записи
     * @param entity объект
     * @return Integer уникальный код созданной записи
     * @throws PersistentException
     */
    Integer create(Type entity) throws PersistentException;

    /**
     * Чтение записи
     * @param identity код записи
     * @return Type объект
     * @throws PersistentException
     */
    Type read(Integer identity) throws PersistentException;

    /**
     * Изменение записи
     * @param entity объект
     * @throws PersistentException
     */
    void update(Type entity) throws PersistentException;

    /**
     * Удаление записи
     * @param identity код записи
     * @throws PersistentException
     */
    void delete(Integer identity) throws PersistentException;
}
