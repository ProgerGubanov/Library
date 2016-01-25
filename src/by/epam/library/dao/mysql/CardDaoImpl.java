package by.epam.library.dao.mysql;

import by.epam.library.dao.CardDao;
import by.epam.library.domain.Card;
import by.epam.library.exception.PersistentException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gubanov Andrey on 16.12.2015.
 */

public class CardDaoImpl extends BaseDaoImpl implements CardDao {
    private static Logger logger = Logger.getLogger(CardDaoImpl.class);

    public static Card getCardInfoFromResultSet(ResultSet resultSet) throws SQLException {
        Card card = new Card();
        card.setIdentity(resultSet.getInt("IdCard"));
        card.setAuthor(resultSet.getString("Author"));
        card.setTitle(resultSet.getString("Title"));
        card.setIsbn(resultSet.getString("Isbn"));
        card.setYearPublication(resultSet.getInt("YearPublication"));
        return card;
    }

    @Override
    public Integer create(Card card) throws PersistentException {
        final String SQL_INSERT_CARD = "INSERT INTO `library`.`Card` (`Author`, `Title`, `Isbn`, `YearPublication`) " +
                "VALUES(?,?,?,?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_INSERT_CARD, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, card.getAuthor());
            statement.setString(2, card.getTitle());
            statement.setString(3, card.getIsbn());
            statement.setInt(4, card.getYearPublication());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                logger.error("There is no autoincremented index after trying to add record into table `Card`");
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
                e.printStackTrace();
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Card read(Integer identity) throws PersistentException {
        final String SQL_SELECT_CARD = "SELECT `IdCard`, `Author`, `Title`, `Isbn`, `YearPublication` " +
                "FROM `library`.`Card` " +
                "WHERE (`IdCard` = ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_CARD);
            statement.setInt(1, identity);
            resultSet = statement.executeQuery();
            Card card = null;
            if (resultSet.next()) {
                card = getCardInfoFromResultSet(resultSet);
            }
            return card;
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Card card) throws PersistentException {
        final String SQL_UPDATE_CARD = "UPDATE `library`.`Card` SET `Author` = ?, `Title` = ?, `Isbn` = ?, `YearPublication` = ? " +
                "WHERE (`IdCard` = ?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE_CARD);
            statement.setString(1, card.getAuthor());
            statement.setString(2, card.getTitle());
            statement.setString(3, card.getIsbn());
            statement.setInt(4, card.getYearPublication());
            statement.setInt(5, card.getIdentity());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Integer identity) throws PersistentException {
        final String SQL_DELETE_CARD = "DELETE FROM `library`.`Card` " +
                "WHERE (`IdCard` = ?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_DELETE_CARD);
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
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Card> readByAuthor(String author) throws PersistentException {
        if (author == null) {
            author = "";
        }
        final String SQL_SELECT_CARD_BY_AUTHOR = "SELECT `IdCard`, `Author`, `Title`, `Isbn`, `YearPublication` " +
                "FROM `library`.`Card` " +
                "WHERE (`Author` LIKE \"%" + author + "%\") ORDER BY `Author`";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_CARD_BY_AUTHOR);
            resultSet = statement.executeQuery();
            List<Card> cards = new ArrayList<>();
            Card card;
            while (resultSet.next()) {
                card = getCardInfoFromResultSet(resultSet);
                cards.add(card);
            }
            return cards;
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Card> readByTitle(String title) throws PersistentException {
        if (title == null) {
            title = "";
        }
        final String SQL_SELECT_CARD_BY_TITLE = "SELECT `IdCard`, `Author`, `Title`, `Isbn`, `YearPublication` " +
                "FROM `library`.`Card` " +
                "WHERE (`Title` LIKE \"%" + title + "%\") " +
                "ORDER BY `Title`";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_CARD_BY_TITLE);
            resultSet = statement.executeQuery();
            List<Card> cards = new ArrayList<>();
            Card card = null;
            while (resultSet.next()) {
                card = getCardInfoFromResultSet(resultSet);
                cards.add(card);
            }
            return cards;
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Card> readByIsbn(String isbn) throws PersistentException {
        if (isbn == null) {
            isbn = "";
        }
        final String SQL_SELECT_CARD_BY_ISBN = "SELECT `IdCard`, `Author`, `Title`, `Isbn`, `YearPublication` " +
                "FROM `library`.`Card` " +
                "WHERE (`Isbn` LIKE \"%" + isbn + "%\") " +
                "ORDER BY `Isbn`";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_CARD_BY_ISBN);
            resultSet = statement.executeQuery();
            List<Card> cards = new ArrayList<>();
            Card card;
            while (resultSet.next()) {
                card = getCardInfoFromResultSet(resultSet);
                cards.add(card);
            }
            return cards;
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
