package by.epam.library.service.Impl;

import java.util.List;

import by.epam.library.dao.BookDao;
import by.epam.library.dao.CardDao;
import by.epam.library.domain.Book;
import by.epam.library.domain.Card;
import by.epam.library.exception.PersistentException;
import by.epam.library.service.CardService;

/**
 * Created by Gubanov Andrey on 22.01.2016.
 */

public class CardServiceImpl extends ServiceImpl implements CardService {
    @Override
    public List<Card> findByAuthor(String author) throws PersistentException {
        CardDao cardDao = factory.createDao(CardDao.class);
        return cardDao.readByAuthor(author);
    }

    @Override
    public Card findByIdentity(Integer identity) throws PersistentException {
        CardDao cardDao = factory.createDao(CardDao.class);
        Card card = cardDao.read(identity);
        return card;
    }

    @Override
    public void save(Card card) throws PersistentException {
        CardDao dao = factory.createDao(CardDao.class);
        if (card.getIdentity() != null) {
            dao.update(card);
        } else {
            card.setIdentity(dao.create(card));
        }
    }

    @Override
    public void delete(Integer identity) throws PersistentException {
        CardDao dao = factory.createDao(CardDao.class);
        dao.delete(identity);
    }

    @Override
    public List<Card> findByTitle(String search) throws PersistentException {
        CardDao cardDao = factory.createDao(CardDao.class);
        List<Card> cards = cardDao.readByTitle(search);
        return cards;
    }

    @Override
    public List<Card> findByIsbn(String isbn) throws PersistentException {
        CardDao cardDao = factory.createDao(CardDao.class);
        List<Card> cards = cardDao.readByIsbn(isbn);
        return cards;
    }

    @Override
    public List<Book> findByIdCard(Integer identity) throws PersistentException {
        BookDao bookDao = factory.createDao(BookDao.class);
        List<Book> books = bookDao.readByIdCard(identity);
        return books;
    }

    @Override
    public int countFreeBooks(int idCardInput) throws PersistentException {
        BookDao bookDao = factory.createDao(BookDao.class);
        int countFreeBooks = bookDao.countFreeBooks(idCardInput);
        return countFreeBooks;
    }
}
