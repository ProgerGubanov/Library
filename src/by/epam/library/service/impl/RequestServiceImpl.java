package by.epam.library.service.impl;

import by.epam.library.dao.RequestDao;
import by.epam.library.domain.Request;
import by.epam.library.exception.PersistentException;
import by.epam.library.service.RequestService;

import java.util.List;

/**
 * Реализация интерфейса RequestService
 *
 * @author Gubanov Andrey
 */
public class RequestServiceImpl extends ServiceImpl implements RequestService {

    /**
     * Сохранение заявки
     *
     * @param request заявка
     * @throws PersistentException
     */
    @Override
    public void save(Request request) throws PersistentException {
        RequestDao dao = factory.createDao(RequestDao.class);
        if (request.getIdentity() != null) {
            dao.update(request);
        } else {
            request.setIdentity(dao.create(request));
        }
    }

    /**
     * Список заявок по читателю
     *
     * @param idUserInput код читателя
     * @return List<Request> список заявок
     * @throws PersistentException
     */
    @Override
    public List<Request> readByIdUser(int idUserInput) throws PersistentException {
        RequestDao dao = factory.createDao(RequestDao.class);
        return dao.readByIdUser(idUserInput);
    }

    /**
     * Чтение заявки
     *
     * @param identity код заявки
     * @return Request заявка
     * @throws PersistentException
     */
    @Override
    public Request read(Integer identity) throws PersistentException {
        RequestDao dao = factory.createDao(RequestDao.class);
        return dao.read(identity);
    }

    /**
     * Чтение заявки по читателю и книге
     *
     * @param idUserInput код читателя
     * @param idCardInput код карточки книги
     * @return Request заявка
     * @throws PersistentException
     */
    @Override
    public Request readRequestByIdUserAndIdCard(int idUserInput, int idCardInput) throws PersistentException {
        RequestDao dao = factory.createDao(RequestDao.class);
        return dao.readRequestByIdUserAndIdCard(idUserInput, idCardInput);
    }

    /**
     * Список заявок
     *
     * @return List<Request> список заявок
     * @throws PersistentException
     */
    @Override
    public List<Request> read() throws PersistentException {
        RequestDao dao = factory.createDao(RequestDao.class);
        return dao.read();
    }

    /**
     * Удаление заявки
     *
     * @param identity код заявки
     * @throws PersistentException
     */
    @Override
    public void delete(Integer identity) throws PersistentException {
        RequestDao dao = factory.createDao(RequestDao.class);
        dao.delete(identity);
    }

    /**
     * Определение количества заявок по читателю и книге
     *
     * @param idUserInput код читателя
     * @param idCardInput код карточки
     * @return int количество заявок по читателю и книге
     * @throws PersistentException
     */
    @Override
    public int LocateRequestByIdUserAndIdCard(int idUserInput, int idCardInput) throws PersistentException {
        RequestDao dao = factory.createDao(RequestDao.class);
        return dao.LocateRequestByIdUserAndIdCard(idUserInput, idCardInput);
    }

}
