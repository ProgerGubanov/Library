package by.epam.library.service;

import java.util.List;

import by.epam.library.domain.Card;
import by.epam.library.domain.Book;
import by.epam.library.exception.PersistentException;

/**
 * Created by Gubanov Andrey on 22.01.2016.
 */

public interface CardService extends Service {
    List<Card> findByAuthor(String author) throws PersistentException;

    List<Card> findByTitle(String title) throws PersistentException;

    Card findByIdentity(Integer identity) throws PersistentException;

    List<Card> findByIsbn(String isbn) throws PersistentException;

    List<Book> findByIdCard(Integer identity) throws PersistentException;

    int countFreeBooks(int idCardInput) throws PersistentException;

    void save(Card card) throws PersistentException;

    void delete(Integer identity) throws PersistentException;
}
