package by.epam.library.service.Impl;

import by.epam.library.dao.OrderDao;
import by.epam.library.domain.Order;
import by.epam.library.exception.PersistentException;
import by.epam.library.service.OrderService;

import java.util.List;

/**
 * Created by Gubanov Andrey on 22.01.2016.
 */

public class OrderServiceImpl extends ServiceImpl implements OrderService {

    @Override
    public void save(Order order) throws PersistentException {
        OrderDao dao = factory.createDao(OrderDao.class);
        if (order.getIdentity() != null) {
            dao.update(order);
        } else {
            order.setIdentity(dao.create(order));
        }
    }

    @Override
    public List<Order> readByIdUser(int idUserInput, boolean isDateActualReturn) throws PersistentException {
        OrderDao dao = factory.createDao(OrderDao.class);
        return dao.readByIdUser(idUserInput, isDateActualReturn);
    }

    @Override
    public List<Order> readByIdLibrarian(int IdLibrarian) throws PersistentException {
        OrderDao dao = factory.createDao(OrderDao.class);
        return dao.readByIdLibrarian(IdLibrarian);
    }

    @Override
    public Order read(Integer identity) throws PersistentException {
        OrderDao dao = factory.createDao(OrderDao.class);
        return dao.read(identity);
    }
}
