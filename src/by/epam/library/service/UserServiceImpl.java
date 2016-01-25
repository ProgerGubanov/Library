package by.epam.library.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.List;

import by.epam.library.dao.UserDao;
import by.epam.library.domain.User;
import by.epam.library.exception.PersistentException;

/**
 * Created by Gubanov Andrey on 22.01.2016.
 */

public class UserServiceImpl extends ServiceImpl implements UserService {
    @Override
    public List<User> findAllUsers() throws PersistentException {
        UserDao dao = factory.createDao(UserDao.class);
        return dao.findAllUsers();
    }

    @Override
    public User findByIdentity(Integer identity) throws PersistentException {
        UserDao dao = factory.createDao(UserDao.class);
        return dao.read(identity);
    }

    @Override
    public User findByLoginAndPassword(String login, String password) throws PersistentException {
        UserDao dao = factory.createDao(UserDao.class);
        return dao.read(login, md5(password));
    }

    @Override
    public List<User> findBySubscription(String subscription) throws PersistentException {
        UserDao dao = factory.createDao(UserDao.class);
        return dao.findBySubscription(subscription);
    }

    @Override
    public List<User> findBySurname(String surname) throws PersistentException {
        UserDao dao = factory.createDao(UserDao.class);
        return dao.findBySurname(surname);
    }


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
