package by.epam.library.dao.mysql;

import by.epam.library.dao.RequestDao;
import by.epam.library.domain.Card;
import by.epam.library.domain.Request;
import by.epam.library.domain.User;
import by.epam.library.exception.PersistentException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gubanov Andrey on 16.12.2015.
 */

public class RequestDaoImpl extends BaseDaoImpl implements RequestDao {
    private static Logger logger = Logger.getLogger(BookDaoImpl.class);

    public static Request getRequestInfoFromResultSet(ResultSet resultSet) throws SQLException {
        Request request = new Request();
        request.setIdentity(resultSet.getInt("IdRequest"));

        Card card = new Card();
        card.setIdentity(resultSet.getInt("IdCard"));
        request.setCard(card);

        User user = new User();
        user.setIdentity(resultSet.getInt("IdUser"));
        request.setUser(user);

        request.setDateRequest(resultSet.getTimestamp("DateRequest"));
        if (resultSet.getInt("IsReadingRoom") == 1) {
            request.setIsReadingRoom(true);
        } else {
            request.setIsReadingRoom(false);
        }
        return request;
    }

    @Override
    public Integer create(Request request) throws PersistentException {
        final String SQL_INSERT_REQUEST = "INSERT INTO `library`.`Request` (`IdCard`, `IdUser`, `IsReadingRoom`) " +
                "VALUES(?,?,?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_INSERT_REQUEST, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, request.getCard().getIdentity());
            statement.setInt(2, request.getUser().getIdentity());
            if (request.isReadingRoom()) {
                statement.setInt(3, 1);
            } else {
                statement.setInt(3, 0);
            }
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                logger.error("There is no autoincremented index after trying to add record into table `Request`");
                throw new PersistentException();
            }
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    @Override
    public Request read(Integer identity) throws PersistentException {
        final String SQL_SELECT_REQUEST = "SELECT `IdRequest`, `IdCard`, `IdUser`, `DateRequest`, `IsReadingRoom` " +
                "FROM `library`.`Request` " +
                "WHERE (`IdRequest` = ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_REQUEST);
            statement.setInt(1, identity);
            resultSet = statement.executeQuery();
            Request request = null;
            if (resultSet.next()) {
                request = getRequestInfoFromResultSet(resultSet);
            }
            return request;
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    @Override
    public void update(Request request) throws PersistentException {
        final String SQL_UPDATE_REQUEST = "UPDATE `library`.`Request` SET `IdCard` = ?, `IdUser` = ?, `DateRequest` = ?, `IsReadingRoom` = ? " +
                "WHERE (`IdRequest` = ?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE_REQUEST);
            statement.setInt(1, request.getCard().getIdentity());
            statement.setInt(2, request.getUser().getIdentity());
            statement.setDate(3, new Date(request.getDateRequest().getTime()));
            if (request.isReadingRoom()) {
                statement.setInt(4, 1);
            } else {
                statement.setInt(4, 0);
            }
            statement.setInt(5, request.getIdentity());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    @Override
    public void delete(Integer identity) throws PersistentException {
        final String SQL_DELETE_REQUEST = "DELETE FROM `library`.`Request` " +
                "WHERE (`IdRequest` = ?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_DELETE_REQUEST);
            statement.setInt(1, identity);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    @Override
    public List<Request> read() throws PersistentException {
        final String SQL_SELECT_ALL_REQUEST = "SELECT `IdRequest`, `library`.`Request`.`IdCard`, `library`.`Request`.`IdUser`, " +
                "`DateRequest`, `IsReadingRoom`, `Author`, `Title`, `Isbn`, `YearPublication`, " +
                "`Surname`, `Name`, `Patronymic` " +
                "FROM `library`.`Request` " +
                "JOIN `library`.`Card` ON `library`.`Card`.`IdCard`=`library`.`Request`.`IdCard` " +
                "JOIN `library`.`User` ON `library`.`User`.`IdUser`=`library`.`Request`.`IdUser` " +
                "ORDER BY `DateRequest`";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_ALL_REQUEST);
            resultSet = statement.executeQuery();
            Request request;
            List<Request> requests = new ArrayList<>();
            ;
            while (resultSet.next()) {
                request = getRequestInfoFromResultSet(resultSet);
                if (!resultSet.wasNull()) {
                    Card card = CardDaoImpl.getCardInfoFromResultSet(resultSet);
                    request.setCard(card);
                    User user = new User();
                    user.setIdentity(request.getUser().getIdentity()); //
                    user.setSurname(resultSet.getString("Surname"));
                    user.setName(resultSet.getString("Name"));
                    user.setPatronymic(resultSet.getString("Patronymic"));
                    request.setUser(user);
                }
                requests.add(request);
            }
            return requests;
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    public List<Request> readByIdUser(int idUserInput) throws PersistentException {
        final String SQL_SELECT_REQUEST_BY_ID_USER = "SELECT `IdRequest`, `library`.`Request`.`IdCard`, `IdUser`, " +
                "`DateRequest`, `IsReadingRoom`, `Author`, `Title`, `Isbn`, `YearPublication` " +
                "FROM `library`.`Request` " +
                "JOIN `library`.`Card` ON `library`.`Card`.`IdCard`=`library`.`Request`.`IdCard` " +
                "WHERE (`IdUser` = ?)"; //
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_REQUEST_BY_ID_USER);
            statement.setInt(1, idUserInput);
            resultSet = statement.executeQuery();
            Request request;
            List<Request> requests = new ArrayList<>();
            while (resultSet.next()) {
                request = getRequestInfoFromResultSet(resultSet);
                if (!resultSet.wasNull()) {
                    Card card = CardDaoImpl.getCardInfoFromResultSet(resultSet);
                    request.setCard(card);
                }
                requests.add(request);
            }
            return requests;
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    public int LocateRequestByIdUserAndIdCard(int idUserInput, int idCardInput) throws PersistentException {
        final String SQL_SELECT_REQUEST_BY_ID_USER_AND_ID_CARD = "SELECT COUNT(*) " +
                "FROM `library`.`Request` " +
                "WHERE (`IdUser` = ?) AND (`IdCard` = ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_REQUEST_BY_ID_USER_AND_ID_CARD);
            statement.setInt(1, idUserInput);
            statement.setInt(2, idCardInput);
            resultSet = statement.executeQuery();
            int requestsCount = 0;
            while (resultSet.next()) {
                requestsCount = resultSet.getInt(1);
            }
            return requestsCount;
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    public Request readRequestByIdUserAndIdCard(int idUserInput, int idCardInput) throws PersistentException {
        final String SQL_SELECT_REQUEST_BY_ID_USER_AND_ID_CARD = "SELECT `IdRequest`, `IdCard`, `IdUser`, `DateRequest`, `IsReadingRoom` " +
                "FROM `library`.`Request` " +
                "WHERE (`IdUser` = ?) AND (`IdCard` = ?)"; //
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_REQUEST_BY_ID_USER_AND_ID_CARD);
            statement.setInt(1, idUserInput);
            statement.setInt(2, idCardInput);
            resultSet = statement.executeQuery();
            Request request = null;
            if (resultSet.next()) {
                request = getRequestInfoFromResultSet(resultSet);
            }
            return request;
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

}
