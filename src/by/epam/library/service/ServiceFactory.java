package by.epam.library.service;

import by.epam.library.exception.PersistentException;

/**
 * Фабрика создания сервисов
 *
 * @author Gubanov Andrey
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
