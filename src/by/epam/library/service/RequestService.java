package by.epam.library.service;

import by.epam.library.domain.Request;
import by.epam.library.exception.PersistentException;

import java.util.List;

/**
 * Created by Gubanov Andrey on 22.01.2016.
 */

/**
 * Интерфейс сервиса для работы с заявками
 */
public interface RequestService extends Service {

    /**
     * Сохранение заявки
     *
     * @param request заявка
     * @throws PersistentException
     */
    void save(Request request) throws PersistentException;

    /**
     * Список заявок по читателю
     *
     * @param idUserInput код читателя
     * @return List<Request> список заявок
     * @throws PersistentException
     */
    List<Request> readByIdUser(int idUserInput) throws PersistentException;

    /**
     * Список заявок
     *
     * @return List<Request> список заявок
     * @throws PersistentException
     */
    List<Request> read() throws PersistentException;

    /**
     * Чтение заявки
     *
     * @param identity код заявки
     * @return Request заявка
     * @throws PersistentException
     */
    Request read(Integer identity) throws PersistentException;

    /**
     * Чтение заявки по читателю и книге
     *
     * @param idUserInput код читателя
     * @param idCardInput код карточки книги
     * @return Request заявка
     * @throws PersistentException
     */
    Request readRequestByIdUserAndIdCard(int idUserInput, int idCardInput) throws PersistentException;

    /**
     * Определение количества заявок по читателю и книге
     *
     * @param idUserInput код читателя
     * @param idCardInput код карточки
     * @return int количество заявок по читателю и книге
     * @throws PersistentException
     */
    int LocateRequestByIdUserAndIdCard(int idUserInput, int idCardInput) throws PersistentException;

    /**
     * Удаление заявки
     *
     * @param identity код заявки
     * @throws PersistentException
     */
    void delete(Integer identity) throws PersistentException;
}
