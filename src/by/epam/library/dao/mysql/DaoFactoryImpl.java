package by.epam.library.dao.mysql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import by.epam.library.dao.*;
import org.apache.log4j.Logger;

import by.epam.library.dao.pool.ConnectionPool;
import by.epam.library.exception.PersistentException;

/**
 * Created by Gubanov Andrey on 16.12.2015.
 */

public class DaoFactoryImpl implements DaoFactory {
    private static Logger logger = Logger.getLogger(DaoFactoryImpl.class);

    private static Map<Class<? extends Dao<?>>, Class<? extends BaseDaoImpl>> classes = new ConcurrentHashMap<>();

    static {
        classes.put(OrderDao.class, OrderDaoImpl.class);
        classes.put(BookDao.class, BookDaoImpl.class);
        classes.put(UserDao.class, UserDaoImpl.class);
        classes.put(CardDao.class, CardDaoImpl.class);
        classes.put(RequestDao.class, RequestDaoImpl.class);
    }

    private Connection connection;

    public DaoFactoryImpl() throws PersistentException {
        connection = ConnectionPool.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            logger.error("It is impossible to turn off autocommiting for database connection", e);
            throw new PersistentException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <Type extends Dao<?>> Type createDao(Class<Type> key) throws PersistentException {
        Class<? extends BaseDaoImpl> value = classes.get(key);
        if (value != null) {
            try {
                BaseDaoImpl dao = value.newInstance();
                dao.setConnection(connection);
                return (Type) dao;
            } catch (InstantiationException | IllegalAccessException e) {
                logger.error("It is impossible to create data access object", e);
                throw new PersistentException(e);
            }
        }
        return null;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
        }
    }
}
