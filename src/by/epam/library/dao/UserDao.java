package by.epam.library.dao;

import java.util.List;

import by.epam.library.domain.User;
import by.epam.library.exception.PersistentException;

/**
 * Created by Gubanov Andrey on 16.12.2015.
 */

public interface UserDao extends Dao<User> {
    User read(String login, String password) throws PersistentException;

    List<User> findAllUsers() throws PersistentException;

    List<User> findBySubscription(String subscription) throws PersistentException;

    List<User> findBySurname(String surname) throws PersistentException;

}
