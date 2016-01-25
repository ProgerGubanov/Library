package by.epam.library.service;

import by.epam.library.domain.Request;
import by.epam.library.exception.PersistentException;

import java.util.List;

/**
 * Created by Gubanov Andrey on 22.01.2016.
 */

public interface RequestService extends Service {

    void save(Request request) throws PersistentException;

    List<Request> readByIdUser(int idUserInput) throws PersistentException;

    List<Request> read() throws PersistentException;

    Request read(Integer identity) throws PersistentException;

    Request readRequestByIdUserAndIdCard(int idUserInput, int idCardInput) throws PersistentException;

    int LocateRequestByIdUserAndIdCard(int idUserInput, int idCardInput) throws PersistentException;

    void delete(Integer identity) throws PersistentException;
}
