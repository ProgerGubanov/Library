package by.epam.library.service;

import java.util.List;

import by.epam.library.domain.User;
import by.epam.library.exception.PersistentException;

/**
 * Created by Gubanov Andrey on 22.01.2016.
 */

/**
 * Сервис по работе с пользователями
 */
public interface UserService extends Service {
    /**
     * Список всех пользователей
     *
     * @return List<User> список пользователей
     * @throws PersistentException
     */
    List<User> findAllUsers() throws PersistentException;

    /**
     * Поиск пользователя по коду записи
     *
     * @param identity код пользователя
     * @return User информация о пользователе
     * @throws PersistentException
     */
    User findByIdentity(Integer identity) throws PersistentException;

    /**
     * Поиск пользователя по логину и паролю
     *
     * @param login    логин
     * @param password md5 пароля
     * @return User информация о пользователе
     * @throws PersistentException
     */
    User findByLoginAndPassword(String login, String password) throws PersistentException;

    /**
     * Поиск пользователей по номеру читательского билета
     *
     * @param subscription номер читательского билета
     * @return List<User> список пользователей
     * @throws PersistentException
     */
    List<User> findBySubscription(String subscription) throws PersistentException;

    /**
     * Поиск пользователей по фамилии
     *
     * @param surname фамилия
     * @return List<User> список пользователей
     * @throws PersistentException
     */
    List<User> findBySurname(String surname) throws PersistentException;

    /**
     * Сохранение информации о пользователе
     *
     * @param user пользователь
     * @throws PersistentException
     */
    void save(User user) throws PersistentException;

    /**
     * Удаление информации о пользователе
     *
     * @param identity код пользователя
     * @throws PersistentException
     */
    void delete(Integer identity) throws PersistentException;
}
