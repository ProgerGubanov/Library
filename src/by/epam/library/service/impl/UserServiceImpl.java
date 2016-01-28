package by.epam.library.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.List;

import by.epam.library.dao.UserDao;
import by.epam.library.domain.User;
import by.epam.library.exception.PersistentException;
import by.epam.library.service.UserService;

/**
 * Реализация интерфейса UserService
 *
 * @author Gubanov Andrey
 */
public class UserServiceImpl extends ServiceImpl implements UserService {
    /**
     * Список всех пользователей
     *
     * @return List<User> список пользователей
     * @throws PersistentException
     */
    @Override
    public List<User> findAllUsers() throws PersistentException {
        UserDao dao = factory.createDao(UserDao.class);
        return dao.findAllUsers();
    }

    /**
     * Поиск пользователя по коду записи
     *
     * @param identity код пользователя
     * @return User информация о пользователе
     * @throws PersistentException
     */
    @Override
    public User findByIdentity(Integer identity) throws PersistentException {
        UserDao dao = factory.createDao(UserDao.class);
        return dao.read(identity);
    }

    /**
     * Поиск пользователя по логину и паролю
     *
     * @param login    логин
     * @param password md5 пароля
     * @return User информация о пользователе
     * @throws PersistentException
     */
    @Override
    public User findByLoginAndPassword(String login, String password) throws PersistentException {
        UserDao dao = factory.createDao(UserDao.class);
        return dao.read(login, md5(password));
    }

    /**
     * Поиск пользователей по номеру читательского билета
     *
     * @param subscription номер читательского билета
     * @return List<User> список пользователей
     * @throws PersistentException
     */
    @Override
    public List<User> findBySubscription(String subscription) throws PersistentException {
        UserDao dao = factory.createDao(UserDao.class);
        return dao.findBySubscription(subscription);
    }

    /**
     * Поиск пользователей по фамилии
     *
     * @param surname фамилия
     * @return List<User> список пользователей
     * @throws PersistentException
     */
    @Override
    public List<User> findBySurname(String surname) throws PersistentException {
        UserDao dao = factory.createDao(UserDao.class);
        return dao.findBySurname(surname);
    }

    /**
     * Сохранение информации о пользователе
     *
     * @param user пользователь
     * @throws PersistentException
     */
    @Override
    public void save(User user) throws PersistentException {
        UserDao dao = factory.createDao(UserDao.class);
        if (user.getIdentity() != null) {
            if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                user.setPassword(md5(user.getPassword()));
            } else {
                User oldUser = dao.read(user.getIdentity());
                user.setPassword(oldUser.getPassword());
            }
            dao.update(user);
        } else {
            if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                user.setPassword(md5(user.getPassword()));
            } else {
                user.setPassword(md5(new String()));
            }
            user.setIdentity(dao.create(user));
        }
    }

    /**
     * Удаление информации о пользователе
     *
     * @param identity код пользователя
     * @throws PersistentException
     */
    @Override
    public void delete(Integer identity) throws PersistentException {
        UserDao dao = factory.createDao(UserDao.class);
        dao.delete(identity);
    }

    private String md5(String string) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("md5");
            digest.reset();
            digest.update(string.getBytes());
            byte hash[] = digest.digest();
            Formatter formatter = new Formatter();
            for (int i = 0; i < hash.length; i++) {
                formatter.format("%02X", hash[i]);
            }
            String md5summ = formatter.toString();
            formatter.close();
            return md5summ;
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
