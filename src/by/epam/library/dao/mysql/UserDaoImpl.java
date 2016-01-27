package by.epam.library.dao.mysql;

import by.epam.library.dao.UserDao;
import by.epam.library.domain.Role;
import by.epam.library.domain.User;
import by.epam.library.exception.PersistentException;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gubanov Andrey on 16.12.2015.
 */

public class UserDaoImpl extends BaseDaoImpl implements UserDao {
    private static Logger logger = Logger.getLogger(UserDaoImpl.class);

    public static User getUserInfoFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setIdentity(resultSet.getInt("IdUser"));
        user.setSurname(resultSet.getString("Surname"));
        user.setName(resultSet.getString("Name"));
        user.setPatronymic(resultSet.getString("Patronymic"));
        user.setSubscription(resultSet.getString("Subscription"));
        user.setAddress(resultSet.getString("Address"));
        user.setPhoneHome(resultSet.getString("PhoneHome"));
        user.setPhoneMobile(resultSet.getString("PhoneMobile"));
        user.setEmail(resultSet.getString("Email"));
        user.setRole(Role.getByIdentity(resultSet.getInt("IdUserRole") - 1));
        user.setLogin(resultSet.getString("Login"));
        user.setPassword(resultSet.getString("Password"));
        return user;
    }

    @Override
    public Integer create(User user) throws PersistentException {
        final String SQL_INSERT_USER = "INSERT INTO `library`.`User` (`Surname`, `Name`, `Patronymic`, `Subscription`, " +
                "`Address`, `PhoneHome`, `PhoneMobile`, `Email`, `IdUserRole`, `Login`, `Password`) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getSurname());
            statement.setString(2, user.getName());
            statement.setString(3, user.getPatronymic());
            statement.setString(4, user.getSubscription());
            statement.setString(5, user.getAddress());
            statement.setString(6, user.getPhoneHome());
            statement.setString(7, user.getPhoneMobile());
            statement.setString(8, user.getEmail());
            statement.setInt(9, user.getRole().getIdentity() + 1);
            statement.setString(10, user.getLogin());
            statement.setString(11, user.getPassword());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                logger.error("There is no autoincremented index after trying to add record into table `User`");
                throw new PersistentException();
            }
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }

    @Override
    public User read(Integer identity) throws PersistentException {
        final String SQL_SELECT_USER = "SELECT `IdUser`, `Surname`, `Name`, `Patronymic`, `Subscription`, `Address`, `PhoneHome`, " +
                "`PhoneMobile`, `Email`, `IdUserRole`, `Login`, `Password` " +
                "FROM `library`.`User` " +
                "WHERE (`IdUser` = ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_USER);
            statement.setInt(1, identity);
            resultSet = statement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = getUserInfoFromResultSet(resultSet);
            }
            return user;
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }

    @Override
    public void update(User user) throws PersistentException {
        final String SQL_UPDATE_USER = "UPDATE `library`.`User` SET `Surname` = ?, `Name` = ?, `Patronymic` = ?, " +
                "`Subscription` = ?, `Address` = ?, `PhoneHome` = ?, `PhoneMobile` = ?, `Email` = ?, `IdUserRole` = ?, " +
                "`Login` = ?, `Password` = ? " +
                "WHERE (`IdUser` = ?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE_USER);
            statement.setString(1, user.getSurname());
            statement.setString(2, user.getName());
            statement.setString(3, user.getPatronymic());
            statement.setString(4, user.getSubscription());
            statement.setString(5, user.getAddress());
            statement.setString(6, user.getPhoneHome());
            statement.setString(7, user.getPhoneMobile());
            statement.setString(8, user.getEmail());
            statement.setInt(9, user.getRole().getIdentity() + 1);
            statement.setString(10, user.getLogin());
            statement.setString(11, user.getPassword());
            statement.setInt(12, user.getIdentity());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }

    @Override
    public void delete(Integer identity) throws PersistentException {
        final String SQL_DELETE_USER = "DELETE FROM `library`.`User` " +
                "WHERE (`IdUser` = ?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_DELETE_USER);
            statement.setInt(1, identity);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }

    @Override
    public User read(String login, String password) throws PersistentException {
        final String SQL_SELECT_USER_BY_LOGIN = "SELECT `IdUser`, `Surname`, `Name`, `Patronymic`, `Subscription`, `Address`, `PhoneHome`, " +
                "`PhoneMobile`, `Email`, `IdUserRole`, `Login`, `Password` " +
                "FROM `library`.`User` " +
                "WHERE (`Login` = ?) AND (`Password` = ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN);
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = getUserInfoFromResultSet(resultSet);
            }
            return user;
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }

    @Override
    public List<User> findAllUsers() throws PersistentException {
        final String SQL_SELECT_ALL_USERS = "SELECT `IdUser`, `Surname`, `Name`, `Patronymic`, `Subscription`, `Address`, `PhoneHome`, " +
                "`PhoneMobile`, `Email`, `IdUserRole`, `Login`, `Password` " +
                "FROM `library`.`User` " +
                "ORDER BY `IdUserRole`, `Surname`";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_ALL_USERS);
            resultSet = statement.executeQuery();
            List<User> users = new ArrayList<>();
            User user;
            while (resultSet.next()) {
                user = getUserInfoFromResultSet(resultSet);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }

    @Override
    public List<User> findBySubscription(String subscription) throws PersistentException {
        if (subscription == null) {
            subscription = "";
        }
        final String SQL_SELECT_USERS_BY_SUBSCRIPTION = "SELECT `IdUser`, `Surname`, `Name`, `Patronymic`, `Subscription`, `Address`, `PhoneHome`, " +
                "`PhoneMobile`, `Email`, `IdUserRole`, `Login`, `Password` " +
                "FROM `library`.`User` " +
                "WHERE (`Subscription` LIKE \"%" + subscription + "%\") AND (`IdUserRole` = 3) " +
                "ORDER BY `Surname`";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_USERS_BY_SUBSCRIPTION);
            resultSet = statement.executeQuery();
            List<User> users = new ArrayList<>();
            User user;
            while (resultSet.next()) {
                user = getUserInfoFromResultSet(resultSet);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }

    @Override
    public List<User> findBySurname(String surname) throws PersistentException {
        if (surname == null) {
            surname = "";
        }
        final String SQL_SELECT_USERS_BY_SURNAME = "SELECT `IdUser`, `Surname`, `Name`, `Patronymic`, `Subscription`, `Address`, `PhoneHome`, " +
                "`PhoneMobile`, `Email`, `IdUserRole`, `Login`, `Password` " +
                "FROM `library`.`User` " +
                "WHERE (`Surname` LIKE \"%" + surname + "%\") AND (`IdUserRole` = 3) " +
                "ORDER BY `Surname`";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_USERS_BY_SURNAME);
            resultSet = statement.executeQuery();
            List<User> users = new ArrayList<>();
            User user;
            while (resultSet.next()) {
                user = getUserInfoFromResultSet(resultSet);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }

}
