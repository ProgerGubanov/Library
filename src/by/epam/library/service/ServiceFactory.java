package by.epam.library.service;

import by.epam.library.exception.PersistentException;

/**
 * Created by Gubanov Andrey on 22.01.2016.
 */

/**
 * Фабрика создания сервисов
 */
public interface ServiceFactory {
    /**
     * Получение сервиса
     *
     * @param key    ключ
     * @param <Type> тип
     * @return сервис
     * @throws PersistentException
     */
    <Type extends Service> Type getService(Class<Type> key) throws PersistentException;

    /**
     * Закрытие сервиса
     */
    void close();
}
