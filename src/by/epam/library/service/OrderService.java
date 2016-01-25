package by.epam.library.service;

import by.epam.library.domain.Order;
import by.epam.library.exception.PersistentException;

import java.util.List;

/**
 * Created by Gubanov Andrey on 22.01.2016.
 */

public interface OrderService extends Service {

    Order read(Integer identity) throws PersistentException;

    void save(Order order) throws PersistentException;

    List<Order> readByIdUser(int idUserInput, boolean isDateActualReturn) throws PersistentException;

    List<Order> readByIdLibrarian(int IdLibrarian) throws PersistentException;
}
