package by.epam.library.dao;

import by.epam.library.domain.Book;
import by.epam.library.exception.PersistentException;

import java.util.List;

/**
 * Интерфейс по работе с книгами в ДАО
 *
 * @author Gubanov Andrey
 */
public interface BookDao extends Dao<Book> {
    /**
     * Чтение информации о книгах по их карточке (все экземпляры одной книги)
     *
     * @param idCardInput код карточки
     * @return List<Book> список книг
     * @throws PersistentException
     */
    List<Book> readByIdCard(int idCardInput) throws PersistentException;

    /**
     * Подсчет количества доступных экземпляров книги по коду карточки
     *
     * @param idCardInput код карточки
     * @return countFreeBooks количество доступных книг
     * @throws PersistentException
     */
    int countFreeBooks(int idCardInput) throws PersistentException;
}
