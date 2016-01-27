package by.epam.library.dao;

import java.util.List;

import by.epam.library.domain.Order;
import by.epam.library.exception.PersistentException;

/**
 * Created by Gubanov Andrey on 16.12.2015.
 */

/**
 * Интерфейс по работе с заказами в ДАО
 */
public interface OrderDao extends Dao<Order> {
    /**
     * Чтение информации о всех заказах (выданных книгах)
     *
     * @return List<Order> список заказов
     * @throws PersistentException
     */
    List<Order> read() throws PersistentException;

    /**
     * Чтение информации о всех полученных книгах читателя
     *
     * @param idUser             код читателя
     * @param isDateActualReturn если значение true - список содержит только не возвращенные книги, иначе - все заказанные ранее
     * @return List<Order> список заказов
     * @throws PersistentException
     */
    List<Order> readByIdUser(int idUser, boolean isDateActualReturn) throws PersistentException;

    /**
     * Чтение информации о выданных книгах библиотекарем
     *
     * @param idUser код библиотекаря
     * @return List<Order> список заказов
     * @throws PersistentException
     */
    List<Order> readByIdLibrarian(int idUser) throws PersistentException;
}
