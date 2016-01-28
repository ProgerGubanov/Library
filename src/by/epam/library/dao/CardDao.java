package by.epam.library.dao;

import java.util.List;

import by.epam.library.domain.Card;
import by.epam.library.exception.PersistentException;

/**
 * Интерфейс по работе с карточками книг в ДАО
 *
 * @author Gubanov Andrey
 */
public interface CardDao extends Dao<Card> {
    /**
     * Чтение информации о карточках книг по автору
     *
     * @param author автор книги
     * @return List<Card> список карточек
     * @throws PersistentException
     */
    List<Card> readByAuthor(String author) throws PersistentException;

    /**
     * Чтение информации о карточках книг по названию
     *
     * @param title название книги
     * @return List<Card> список карточек
     * @throws PersistentException
     */
    List<Card> readByTitle(String title) throws PersistentException;

    /**
     * Чтение информации о карточках книг по номеру Isbn
     *
     * @param isbn номер Isbn книги
     * @return List<Card> список карточек
     * @throws PersistentException
     */
    List<Card> readByIsbn(String isbn) throws PersistentException;
}
