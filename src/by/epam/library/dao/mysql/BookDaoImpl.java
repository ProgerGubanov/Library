package by.epam.library.dao.mysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import by.epam.library.domain.BookStatus;
import by.epam.library.domain.Card;
import org.apache.log4j.Logger;

import by.epam.library.dao.BookDao;
import by.epam.library.domain.Book;
import by.epam.library.exception.PersistentException;

/**
 * Реализация DAO для класса Book - книга
 *
 * @author Gubanov Andrey
 */
public class BookDaoImpl extends BaseDaoImpl implements BookDao {
    private static Logger logger = Logger.getLogger(BookDaoImpl.class);

    /**
     * Извлечение информации из набора данных в объект book
     *
     * @param resultSet набор данных
     * @return возвращаем объект book
     * @throws SQLException
     */
    public static Book getBookInfoFromResultSet(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setIdentity(resultSet.getInt("IdBook"));
        book.setInventoryNumber(resultSet.getString("InventoryNumber"));
        book.setBookStatus(BookStatus.getByIdentity(resultSet.getInt("IdBookStatus") - 1));
        Card card = new Card();
        card.setIdentity(resultSet.getInt("IdCard"));
        book.setCard(card);
        return book;
    }

    /**
     * Добавление записи в таблицу Book
     *
     * @param book объект для сохранения в базе данных
     * @return возвращаем автоинкрементное поле
     * @throws PersistentException
     */
    @Override
    public Integer create(Book book) throws PersistentException {
        final String SQL_INSERT_BOOK = "INSERT INTO `library`.`Book` (`InventoryNumber`, `IdBookStatus`, `IdCard`) " +
                "VALUES(?,?,?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_INSERT_BOOK, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, book.getInventoryNumber());
            statement.setInt(2, book.getBookStatus().getIdentity() + 1);
            statement.setInt(3, book.getCard().getIdentity());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                logger.error("There is no autoincremented index after trying to add record into table `Book`");
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
     * Чтение одной записи из таблицы Book
     *
     * @param identity уникальный код записи (IdBook)
     * @return объект book
     * @throws PersistentException
     */
    @Override
    public Book read(Integer identity) throws PersistentException {
        final String SQL_SELECT_BOOK = "SELECT `IdBook`, `InventoryNumber`, `IdBookStatus`, `IdCard` " +
                "FROM `library`.`Book` " +
                "WHERE (`IdBook` = ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_BOOK);
            statement.setInt(1, identity);
            resultSet = statement.executeQuery();
            Book book = null;
            if (resultSet.next()) {
                book = getBookInfoFromResultSet(resultSet);
            }
            return book;
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
     * Обновление записи в таблице Book
     *
     * @param book объект с новой информацией
     * @throws PersistentException
     */
    @Override
    public void update(Book book) throws PersistentException {
        final String SQL_UPDATE_BOOK = "UPDATE `library`.`Book` SET `InventoryNumber` = ?, `IdBookStatus` = ?, `IdCard` = ? " +
                "WHERE (`IdBook` = ?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE_BOOK);
            statement.setString(1, book.getInventoryNumber());
            statement.setInt(2, book.getBookStatus().getIdentity() + 1);
            statement.setInt(3, book.getCard().getIdentity());
            statement.setInt(4, book.getIdentity());
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
     * Удаление записи из таблицы Book
     *
     * @param identity уникальный код записи (IdBook)
     * @throws PersistentException
     */
    @Override
    public void delete(Integer identity) throws PersistentException {
        final String SQL_DELETE_BOOK = "DELETE FROM `library`.`Book` " +
                "WHERE (`IdBook` = ?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_DELETE_BOOK);
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
     * Чтение информации о всех книгах
     *
     * @return List<Book> список книг
     * @throws PersistentException
     */
    public List<Book> read() throws PersistentException {
        final String SQL_SELECT_BOOK_ALL = "SELECT `IdBook`, `InventoryNumber`, `IdBookStatus`, `IdCard` " +
                "FROM `library`.`Book`";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_BOOK_ALL);
            resultSet = statement.executeQuery();
            List<Book> books = new ArrayList<>();
            Book book;
            while (resultSet.next()) {
                book = getBookInfoFromResultSet(resultSet);
                books.add(book);
            }
            return books;
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
     * Чтение информации о книгах по их карточке (все экземпляры одной книги)
     *
     * @param idCardInput код карточки
     * @return List<Book> список книг
     * @throws PersistentException
     */
    public List<Book> readByIdCard(int idCardInput) throws PersistentException {
        final String SQL_SELECT_BOOK_BY_CARD = "SELECT `IdBook`, `InventoryNumber`, `IdBookStatus`, `IdCard` " +
                "FROM `library`.`Book` " +
                "WHERE (`IdCard` = ?) " +
                "ORDER BY `IdBookStatus`, `InventoryNumber`";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_BOOK_BY_CARD);
            statement.setInt(1, idCardInput);
            resultSet = statement.executeQuery();
            List<Book> books = new ArrayList<>();
            Book book;
            while (resultSet.next()) {
                book = getBookInfoFromResultSet(resultSet);
                books.add(book);
            }
            return books;
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
     * Подсчет количества доступных экземпляров книги по коду карточки
     *
     * @param idCardInput код карточки
     * @return countFreeBooks количество доступных книг
     * @throws PersistentException
     */
    public int countFreeBooks(int idCardInput) throws PersistentException {
        final String SQL_SELECT_COUNT_FREE_BOOKS = "SELECT COUNT(*) AS `CountFreeBooks` " +
                "FROM `library`.`Book` " +
                "WHERE (`IdBookStatus` = 1) AND (`IdCard` = ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_COUNT_FREE_BOOKS);
            statement.setInt(1, idCardInput);
            resultSet = statement.executeQuery();
            int countFreeBooks = 0;
            if (resultSet.next()) {
                countFreeBooks = resultSet.getInt("CountFreeBooks");
            }
            return countFreeBooks;
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
