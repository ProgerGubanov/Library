package by.epam.library.service;

import java.util.List;

import by.epam.library.domain.Card;
import by.epam.library.domain.Book;
import by.epam.library.exception.PersistentException;

/**
 * Интерфейс сервиса для работы с карточками
 *
 * @author Gubanov Andrey
 */
public interface CardService extends Service {
    /**
     * Получение списка карточек по автору
     *
     * @param author автор
     * @return List<Card> список карточек
     * @throws PersistentException
     */
    List<Card> findByAuthor(String author) throws PersistentException;

    /**
     * Получение списка карточек по названию
     *
     * @param title название
     * @return List<Card> список карточек
     * @throws PersistentException
     */
    List<Card> findByTitle(String title) throws PersistentException;

    /**
     * Получение карточки по коду записи
     *
     * @param identity код карточки
     * @return Card карточка
     * @throws PersistentException
     */
    Card findByIdentity(Integer identity) throws PersistentException;

    /**
     * Получение списка карточек по isbn
     *
     * @param isbn isbn
     * @return List<Card> список карточек
     * @throws PersistentException
     */
    List<Card> findByIsbn(String isbn) throws PersistentException;

    /**
     * Получение списка книг по коду карточки
     *
     * @param identity код карточки
     * @return List<Book> список книг
     * @throws PersistentException
     */
    List<Book> findByIdCard(Integer identity) throws PersistentException;

    /**
     * Подсчет количества свободных книг по коду карточки
     *
     * @param idCardInput код карточки
     * @return int количество свободных книг
     * @throws PersistentException
     */
    int countFreeBooks(int idCardInput) throws PersistentException;

    /**
     * Сохранение информации о карточке
     *
     * @param card карточка
     * @throws PersistentException
     */
    void save(Card card) throws PersistentException;

    /**
     * Удаление карточки
     *
     * @param identity код карточки
     * @throws PersistentException
     */
    void delete(Integer identity) throws PersistentException;
}
