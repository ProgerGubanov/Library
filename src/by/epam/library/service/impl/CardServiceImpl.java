package by.epam.library.service.impl;

import java.util.List;

import by.epam.library.dao.BookDao;
import by.epam.library.dao.CardDao;
import by.epam.library.domain.Book;
import by.epam.library.domain.Card;
import by.epam.library.exception.PersistentException;
import by.epam.library.service.CardService;

/**
 * Реализация интерфейса CardService
 *
 * @author Gubanov Andrey
 */
public class CardServiceImpl extends ServiceImpl implements CardService {

    /**
     * Получение списка карточек по автору
     *
     * @param author автор
     * @return List<Card> список карточек
     * @throws PersistentException
     */
    @Override
    public List<Card> findByAuthor(String author) throws PersistentException {
        CardDao cardDao = factory.createDao(CardDao.class);
        return cardDao.readByAuthor(author);
    }

    /**
     * Получение карточки по коду записи
     *
     * @param identity код карточки
     * @return Card карточка
     * @throws PersistentException
     */
    @Override
    public Card findByIdentity(Integer identity) throws PersistentException {
        CardDao cardDao = factory.createDao(CardDao.class);
        Card card = cardDao.read(identity);
        return card;
    }

    /**
     * Сохранение информации о карточке
     *
     * @param card карточка
     * @throws PersistentException
     */
    @Override
    public void save(Card card) throws PersistentException {
        CardDao dao = factory.createDao(CardDao.class);
        if (card.getIdentity() != null) {
            dao.update(card);
        } else {
            card.setIdentity(dao.create(card));
        }
    }

    /**
     * Удаление карточки
     *
     * @param identity код карточки
     * @throws PersistentException
     */
    @Override
    public void delete(Integer identity) throws PersistentException {
        CardDao dao = factory.createDao(CardDao.class);
        dao.delete(identity);
    }

    /**
     * Получение списка карточек по названию
     *
     * @param search название
     * @return List<Card> список карточек
     * @throws PersistentException
     */
    @Override
    public List<Card> findByTitle(String search) throws PersistentException {
        CardDao cardDao = factory.createDao(CardDao.class);
        List<Card> cards = cardDao.readByTitle(search);
        return cards;
    }

    /**
     * Получение списка карточек по isbn
     *
     * @param isbn isbn
     * @return List<Card> список карточек
     * @throws PersistentException
     */
    @Override
    public List<Card> findByIsbn(String isbn) throws PersistentException {
        CardDao cardDao = factory.createDao(CardDao.class);
        List<Card> cards = cardDao.readByIsbn(isbn);
        return cards;
    }

    /**
     * Получение списка книг по коду карточки
     *
     * @param identity код карточки
     * @return List<Book> список книг
     * @throws PersistentException
     */
    @Override
    public List<Book> findByIdCard(Integer identity) throws PersistentException {
        BookDao bookDao = factory.createDao(BookDao.class);
        List<Book> books = bookDao.readByIdCard(identity);
        return books;
    }

    /**
     * Подсчет количества свободных книг по коду карточки
     *
     * @param idCardInput код карточки
     * @return int количество свободных книг
     * @throws PersistentException
     */
    @Override
    public int countFreeBooks(int idCardInput) throws PersistentException {
        BookDao bookDao = factory.createDao(BookDao.class);
        int countFreeBooks = bookDao.countFreeBooks(idCardInput);
        return countFreeBooks;
    }
}
