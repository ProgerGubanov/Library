package by.epam.library.service;

import by.epam.library.domain.Order;
import by.epam.library.exception.PersistentException;

import java.util.List;

/**
 * Интерфейс сервиса для работы с заказами
 *
 * @author Gubanov Andrey
 */
public interface OrderService extends Service {

    /**
     * Чтение заказа
     *
     * @param identity код заказа
     * @return Order заказ
     * @throws PersistentException
     */
    Order read(Integer identity) throws PersistentException;

    /**
     * Сохранение заказа
     *
     * @param order заказ
     * @throws PersistentException
     */
    void save(Order order) throws PersistentException;

    /**
     * Список заказов читателя
     *
     * @param idUserInput        код пользователя
     * @param isDateActualReturn признак для поиска всех книг (false) или только не возвращенных (true)
     * @return List<Order> список заказов
     * @throws PersistentException
     */
    List<Order> readByIdUser(int idUserInput, boolean isDateActualReturn) throws PersistentException;

    /**
     * Список заказов библиотекаря
     *
     * @param IdLibrarian код библиотекаря
     * @return List<Order> список заказов
     * @throws PersistentException
     */
    List<Order> readByIdLibrarian(int IdLibrarian) throws PersistentException;
}
