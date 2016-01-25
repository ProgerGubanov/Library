package by.epam.library.dao;

import java.util.List;

import by.epam.library.domain.Card;
import by.epam.library.exception.PersistentException;

/**
 * Created by Gubanov Andrey on 16.12.2015.
 */

public interface CardDao extends Dao<Card> {
    List<Card> readByAuthor(String author) throws PersistentException;

    List<Card> readByTitle(String title) throws PersistentException;

    List<Card> readByIsbn(String isbn) throws PersistentException;
}
