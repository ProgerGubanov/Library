package by.epam.library.service.impl;

import by.epam.library.dao.OrderDao;
import by.epam.library.domain.Order;
import by.epam.library.exception.PersistentException;
import by.epam.library.service.OrderService;

import java.util.List;

/**
 * Реализация интерфейса OrderService
 *
 * @author Gubanov Andrey
 */
public class OrderServiceImpl extends ServiceImpl implements OrderService {

    /**
     * Сохранение заказа
     *
     * @param order заказ
     * @throws PersistentException
     */
    @Override
    public void save(Order order) throws PersistentException {
        OrderDao dao = factory.createDao(OrderDao.class);
        if (order.getIdentity() != null) {
            dao.update(order);
        } else {
            order.setIdentity(dao.create(order));
        }
    }

    /**
     * Список заказов читателя
     *
     * @param idUserInput        код пользователя
     * @param isDateActualReturn признак для поиска всех книг (false) или только не возвращенных (true)
     * @return List<Order> список заказов
     * @throws PersistentException
     */
    @Override
    public List<Order> readByIdUser(int idUserInput, boolean isDateActualReturn) throws PersistentException {
        OrderDao dao = factory.createDao(OrderDao.class);
        return dao.readByIdUser(idUserInput, isDateActualReturn);
    }

    /**
     * Список заказов библиотекаря
     *
     * @param IdLibrarian код библиотекаря
     * @return List<Order> список заказов
     * @throws PersistentException
     */
    @Override
    public List<Order> readByIdLibrarian(int IdLibrarian) throws PersistentException {
        OrderDao dao = factory.createDao(OrderDao.class);
        return dao.readByIdLibrarian(IdLibrarian);
    }

    /**
     * Чтение заказа
     *
     * @param identity код заказа
     * @return Order заказ
     * @throws PersistentException
     */
    @Override
    public Order read(Integer identity) throws PersistentException {
        OrderDao dao = factory.createDao(OrderDao.class);
        return dao.read(identity);
    }
}
