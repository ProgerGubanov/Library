package by.epam.library.dao;

import java.util.List;

import by.epam.library.domain.User;
import by.epam.library.exception.PersistentException;

/**
 * Created by Gubanov Andrey on 16.12.2015.
 */

/**
 * Интерфейс по работе с пользователями в ДАО
 */
public interface UserDao extends Dao<User> {
    /**
     * Чтение информации о пользователе по его логину и паролю
     *
     * @param login    логин пользователя
     * @param password md5 пароля
     * @return user информация о пользователе
     * @throws PersistentException
     */
    User read(String login, String password) throws PersistentException;

    /**
     * Чтение информации о всех пользователях
     *
     * @return List<User> список пользователей
     * @throws PersistentException
     */
    List<User> findAllUsers() throws PersistentException;

    /**
     * Чтение информации о пользователях по номеру читательского билета
     *
     * @param subscription номер читательского билета
     * @return List<User> список пользователей
     * @throws PersistentException
     */
    List<User> findBySubscription(String subscription) throws PersistentException;

    /**
     * Чтение информации о пользователях по фамилии
     *
     * @param surname фамилия
     * @return List<User> список пользователей
     * @throws PersistentException
     */
    List<User> findBySurname(String surname) throws PersistentException;

}
