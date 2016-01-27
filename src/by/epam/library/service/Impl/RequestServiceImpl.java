package by.epam.library.service.Impl;

import by.epam.library.dao.RequestDao;
import by.epam.library.domain.Request;
import by.epam.library.exception.PersistentException;
import by.epam.library.service.RequestService;

import java.util.List;

/**
 * Created by Gubanov Andrey on 22.01.2016.
 */

public class RequestServiceImpl extends ServiceImpl implements RequestService {

    @Override
    public void save(Request request) throws PersistentException {
        RequestDao dao = factory.createDao(RequestDao.class);
        if (request.getIdentity() != null) {
            dao.update(request);
        } else {
            request.setIdentity(dao.create(request));
        }
    }

    @Override
    public List<Request> readByIdUser(int idUserInput) throws PersistentException {
        RequestDao dao = factory.createDao(RequestDao.class);
        return dao.readByIdUser(idUserInput);
    }

    @Override
    public Request read(Integer identity) throws PersistentException {
        RequestDao dao = factory.createDao(RequestDao.class);
        return dao.read(identity);
    }

    @Override
    public Request readRequestByIdUserAndIdCard(int idUserInput, int idCardInput) throws PersistentException {
        RequestDao dao = factory.createDao(RequestDao.class);
        return dao.readRequestByIdUserAndIdCard(idUserInput, idCardInput);
    }

    @Override
    public List<Request> read() throws PersistentException {
        RequestDao dao = factory.createDao(RequestDao.class);
        return dao.read();
    }

    @Override
    public void delete(Integer identity) throws PersistentException {
        RequestDao dao = factory.createDao(RequestDao.class);
        dao.delete(identity);
    }

    @Override
    public int LocateRequestByIdUserAndIdCard(int idUserInput, int idCardInput) throws PersistentException {
        RequestDao dao = factory.createDao(RequestDao.class);
        return dao.LocateRequestByIdUserAndIdCard(idUserInput, idCardInput);
    }

}
