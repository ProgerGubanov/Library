package by.epam.library.service;

import java.util.List;

import by.epam.library.domain.User;
import by.epam.library.exception.PersistentException;

/**
 * Created by Gubanov Andrey on 22.01.2016.
 */

public interface UserService extends Service {

    List<User> findAllUsers() throws PersistentException;

    User findByIdentity(Integer identity) throws PersistentException;

    User findByLoginAndPassword(String login, String password) throws PersistentException;

    List<User> findBySubscription(String subscription) throws PersistentException;

    List<User> findBySurname(String surname) throws PersistentException;

    void save(User user) throws PersistentException;

    void delete(Integer identity) throws PersistentException;
}
