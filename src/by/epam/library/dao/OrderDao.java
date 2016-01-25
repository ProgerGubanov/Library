package by.epam.library.dao;

import java.util.List;

import by.epam.library.domain.Order;
import by.epam.library.exception.PersistentException;

/**
 * Created by Gubanov Andrey on 16.12.2015.
 */

public interface OrderDao extends Dao<Order> {
    List<Order> read() throws PersistentException;

    List<Order> readByIdUser(int idUser, boolean isDateActualReturn) throws PersistentException;

    List<Order> readByIdLibrarian(int idUser) throws PersistentException;
}
