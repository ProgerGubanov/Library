package by.epam.library.dao;

import java.util.List;

import by.epam.library.domain.Request;
import by.epam.library.exception.PersistentException;

/**
 * Created by Gubanov Andrey on 16.12.2015.
 */

public interface RequestDao extends Dao<Request> {
    List<Request> read() throws PersistentException;

    List<Request> readByIdUser(int idUser) throws PersistentException;

    Request readRequestByIdUserAndIdCard(int idUserInput, int idCardInput) throws PersistentException;

    int LocateRequestByIdUserAndIdCard(int idUserInput, int idCardInput) throws PersistentException;
}
