package by.epam.library.dao;

import java.util.List;

import by.epam.library.domain.Request;
import by.epam.library.exception.PersistentException;

/**
 * Created by Gubanov Andrey on 16.12.2015.
 */

/**
 * Интерфейс по работе с заявкам в ДАО
 */
public interface RequestDao extends Dao<Request> {
    /**
     * Чтение информации о всех заявках
     *
     * @return List<Request> список заявок
     * @throws PersistentException
     */
    List<Request> read() throws PersistentException;

    /**
     * Чтение информации о всех заявках одного читателя
     *
     * @param idUser код читателя
     * @return List<Request> список заявок
     * @throws PersistentException
     */
    List<Request> readByIdUser(int idUser) throws PersistentException;

    /**
     * Считаем количество заявок указанной книги читателем
     *
     * @param idUserInput код читателя
     * @param idCardInput код карточки книги
     * @return int количество заявок
     * @throws PersistentException
     */
    Request readRequestByIdUserAndIdCard(int idUserInput, int idCardInput) throws PersistentException;

    /**
     * Чтение информации о заявке на книгу читателем
     *
     * @param idUserInput код читателя
     * @param idCardInput код карточки книги
     * @return request заявка
     * @throws PersistentException
     */
    int LocateRequestByIdUserAndIdCard(int idUserInput, int idCardInput) throws PersistentException;
}
