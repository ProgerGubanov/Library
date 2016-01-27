package by.epam.library.dao.mysql;

import by.epam.library.dao.OrderDao;
import by.epam.library.domain.Book;
import by.epam.library.domain.Card;
import by.epam.library.domain.Order;
import by.epam.library.domain.User;
import by.epam.library.exception.PersistentException;
import org.apache.log4j.Logger;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gubanov Andrey on 16.12.2015.
 */

/**
 * Реализация DAO для класса Order - оформленный заказ (выданная книга)
 */
public class OrderDaoImpl extends BaseDaoImpl implements OrderDao {
    private static Logger logger = Logger.getLogger(OrderDaoImpl.class);

    /**
     * Извлечение информации из набора данных в объект order
     *
     * @param resultSet набор данных
     * @return возвращаем объект order
     * @throws SQLException
     */
    public static Order getOrderInfoFromResultSet(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setIdentity(resultSet.getInt("IdOrder"));

        Book book = new Book();
        book.setIdentity(resultSet.getInt("IdBook"));
        order.setBook(book);

        User user = new User();
        user.setIdentity(resultSet.getInt("IdUser"));
        order.setUser(user);

        User librarian = new User();
        librarian.setIdentity(resultSet.getInt("IdLibrarian"));
        order.setLibrarian(librarian);

        order.setDatePlannedReturn(resultSet.getDate("DatePlannedReturn"));
        order.setDateActualReturn(resultSet.getTimestamp("DateActualReturn"));
        order.setDateIssue(resultSet.getTimestamp("DateIssue"));
        order.setIsReadingRoom(resultSet.getInt("IsReadingRoom") == 1);
        return order;
    }

    /**
     * Добавление записи в таблицу Order
     *
     * @param order объект для сохранения в базе данных
     * @return возвращаем автоинкрементное поле
     * @throws PersistentException
     */
    @Override
    public Integer create(Order order) throws PersistentException {
        final String SQL_INSERT_ORDER = "INSERT INTO `library`.`Order` (`IdBook`, `IdUser`, `IdLibrarian`, `DatePlannedReturn`, " +
                "`DateActualReturn`, `DateIssue`, `IsReadingRoom`) " +
                "VALUES(?,?,?,?,?,?,?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, order.getBook().getIdentity());
            statement.setInt(2, order.getUser().getIdentity());
            statement.setInt(3, order.getLibrarian().getIdentity());
            statement.setDate(4, new Date(order.getDatePlannedReturn().getTime()));
            statement.setTimestamp(5, null);
            statement.setTimestamp(6, new Timestamp(order.getDateIssue().getTime()));
            if (order.isReadingRoom()) {
                statement.setInt(7, 1);
            } else {
                statement.setInt(7, 0);
            }
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                logger.error("There is no autoincremented index after trying to add record into table `Order`");
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
                logger.error(e);
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error(e);
            }
        }
    }

    /**
     * Чтение одной записи из таблицы Order
     *
     * @param identity уникальный код записи (IdOrder)
     * @return объект order
     * @throws PersistentException
     */
    @Override
    public Order read(Integer identity) throws PersistentException {
        final String SQL_SELECT_ORDER = "SELECT `IdOrder`, `IdBook`, `IdUser`, `IdLibrarian`, `DatePlannedReturn`, `DateActualReturn`, " +
                "`DateIssue`, `IsReadingRoom` " +
                "FROM `library`.`Order` " +
                "WHERE (`IdOrder` = ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_ORDER);
            statement.setInt(1, identity);
            resultSet = statement.executeQuery();
            Order order = null;
            if (resultSet.next()) {
                order = getOrderInfoFromResultSet(resultSet);
            }
            return order;
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error(e);
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error(e);
            }
        }
    }

    /**
     * Обновление записи в таблице Order
     *
     * @param order объект с новой информацией
     * @throws PersistentException
     */
    @Override
    public void update(Order order) throws PersistentException {
        final String SQL_UPDATE_ORDER = "UPDATE `library`.`Order` SET `IdBook` = ?, `IdUser` = ?, `IdLibrarian` = ?, " +
                "`DatePlannedReturn` = ?, `DateActualReturn` = ?, `DateIssue` = ?, `IsReadingRoom` = ? " +
                "WHERE (`IdOrder` = ?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE_ORDER);
            statement.setInt(1, order.getBook().getIdentity());
            statement.setInt(2, order.getUser().getIdentity());
            statement.setInt(3, order.getLibrarian().getIdentity());
            statement.setDate(4, new Date(order.getDatePlannedReturn().getTime()));
            statement.setTimestamp(5, new Timestamp(order.getDateActualReturn().getTime()));
            statement.setTimestamp(6, new Timestamp(order.getDateIssue().getTime()));
            if (order.isReadingRoom()) {
                statement.setInt(7, 1);
            } else {
                statement.setInt(7, 0);
            }
            statement.setInt(8, order.getIdentity());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error(e);
            }
        }
    }

    /**
     * Удаление записи из таблицы Order
     *
     * @param identity уникальный код записи (IdOrder)
     * @throws PersistentException
     */
    @Override
    public void delete(Integer identity) throws PersistentException {
        final String SQL_DELETE_ORDER = "DELETE FROM `library`.`Order` " +
                "WHERE (`IdOrder` = ?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_DELETE_ORDER);
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
                logger.error(e);
            }
        }
    }

    /**
     * Чтение информации о всех заказах (выданных книгах)
     *
     * @return List<Order> список заказов
     * @throws PersistentException
     */
    @Override
    public List<Order> read() throws PersistentException {
        final String SQL_SELECT_ALL_ORDER = "SELECT `IdOrder`, `IdBook`, `IdUser`, `IdLibrarian`, `DatePlannedReturn`, " +
                "`DateActualReturn`, `DateIssue`, `IsReadingRoom` " +
                "FROM `library`.`Order`";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_ALL_ORDER);
            resultSet = statement.executeQuery();
            Order order;
            List<Order> orders = new ArrayList<>();
            if (resultSet.next()) {
                order = getOrderInfoFromResultSet(resultSet);
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error(e);
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error(e);
            }
        }
    }

    /**
     * Чтение информации о всех полученных книгах читателя
     *
     * @param idUserInput        код читателя
     * @param isDateActualReturn если значение true - список содержит только не возвращенные книги, иначе - все заказанные ранее
     * @return List<Order> список заказов
     * @throws PersistentException
     */
    @Override
    public List<Order> readByIdUser(int idUserInput, boolean isDateActualReturn) throws PersistentException {
        final String SQL_SELECT_ORDER_BY_ID_USER;
        if (isDateActualReturn) {
            SQL_SELECT_ORDER_BY_ID_USER = "SELECT `IdOrder`, `library`.`Order`.`IdBook`, `IdUser`, `IdLibrarian`, " +
                    "`library`.`Book`.`IdCard`, `DatePlannedReturn`, `DateActualReturn`, `DateIssue`, `IsReadingRoom`, " +
                    "`InventoryNumber`, `Author`, `Title`, `Isbn`, `YearPublication`, `IdBookStatus` " +
                    "FROM `library`.`Order` " +
                    "JOIN `library`.`Book` ON `library`.`Book`.`IdBook`=`library`.`Order`.`IdBook`" +
                    "JOIN `library`.`Card` ON `library`.`Book`.`IdCard`=`library`.`Card`.`IdCard`" +
                    "WHERE (`IdUser` = ?) AND (`DateActualReturn` IS NULL)" +
                    "ORDER BY `DateIssue`";
        } else {
            SQL_SELECT_ORDER_BY_ID_USER = "SELECT `IdOrder`, `library`.`Order`.`IdBook`, `IdUser`, `IdLibrarian`, " +
                    "`library`.`Book`.`IdCard`, `DatePlannedReturn`, `DateActualReturn`, `DateIssue`, `IsReadingRoom`, " +
                    "`InventoryNumber`, `Author`, `Title`, `Isbn`, `YearPublication`, `IdBookStatus` " +
                    "FROM `library`.`Order` " +
                    "JOIN `library`.`Book` ON `library`.`Book`.`IdBook`=`library`.`Order`.`IdBook`" +
                    "JOIN `library`.`Card` ON `library`.`Book`.`IdCard`=`library`.`Card`.`IdCard`" +
                    "WHERE (`IdUser` = ?)" +
                    "ORDER BY `DateIssue`";
        }
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_ORDER_BY_ID_USER);
            statement.setInt(1, idUserInput);
            resultSet = statement.executeQuery();
            Order order;
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                order = getOrderInfoFromResultSet(resultSet);
                Book book = BookDaoImpl.getBookInfoFromResultSet(resultSet);

                Card card = CardDaoImpl.getCardInfoFromResultSet(resultSet);
                book.setCard(card);
                order.setBook(book);

                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error(e);
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error(e);
            }
        }
    }

    /**
     * Чтение информации о выданных книгах библиотекарем
     *
     * @param idUserInput код библиотекаря
     * @return List<Order> список заказов
     * @throws PersistentException
     */
    @Override
    public List<Order> readByIdLibrarian(int idUserInput) throws PersistentException {
        final String SQL_SELECT_ORDER_BY_ID_LIBRARIAN = "SELECT `IdOrder`, `library`.`Order`.`IdBook`, `IdUser`, `IdLibrarian`, " +
                "`library`.`Book`.`IdCard`, `DatePlannedReturn`, `DateActualReturn`, `DateIssue`, `IsReadingRoom`, " +
                "`InventoryNumber`, `Author`, `Title`, `Isbn`, `YearPublication`, `IdBookStatus` " +
                "FROM `library`.`Order` " +
                "JOIN `library`.`Book` ON `library`.`Book`.`IdBook`=`library`.`Order`.`IdBook`" +
                "JOIN `library`.`Card` ON `library`.`Book`.`IdCard`=`library`.`Card`.`IdCard`" +
                "WHERE (`IdLibrarian` = ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_ORDER_BY_ID_LIBRARIAN);
            statement.setInt(1, idUserInput);
            resultSet = statement.executeQuery();
            Order order;
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                order = getOrderInfoFromResultSet(resultSet);
                Book book = BookDaoImpl.getBookInfoFromResultSet(resultSet);

                Card card = CardDaoImpl.getCardInfoFromResultSet(resultSet);
                book.setCard(card);
                order.setBook(book);

                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error(e);
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error(e);
            }
        }
    }
}
