package by.epam.library.service.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import by.epam.library.service.*;
import org.apache.log4j.Logger;

import by.epam.library.dao.DaoFactory;
import by.epam.library.exception.PersistentException;

/**
 * Реализация интерфейса ServiceFactory
 *
 * @author Gubanov Andrey
 */
public class ServiceFactoryImpl implements ServiceFactory {
    private static Logger logger = Logger.getLogger(ServiceFactoryImpl.class);

    private static final Map<Class<? extends Service>, Class<? extends ServiceImpl>> SERVICES = new ConcurrentHashMap<>();

    static {
        SERVICES.put(CardService.class, CardServiceImpl.class);
        SERVICES.put(UserService.class, UserServiceImpl.class);
        SERVICES.put(RequestService.class, RequestServiceImpl.class);
        SERVICES.put(OrderService.class, OrderServiceImpl.class);
        SERVICES.put(BookService.class, BookServiceImpl.class);
        SERVICES.put(BookReturnService.class, BookReturnServiceImpl.class);
        SERVICES.put(BookIssueService.class, BookIssueServiceImpl.class);
    }

    private DaoFactory factory;

    /**
     * Конструктор
     *
     * @param factory фабрика
     * @throws PersistentException
     */
    public ServiceFactoryImpl(DaoFactory factory) throws PersistentException {
        this.factory = factory;
    }

    /**
     * Получение сервиса
     *
     * @param key    ключ
     * @param <Type> тип
     * @return сервис
     * @throws PersistentException
     */
    @Override
    @SuppressWarnings("unchecked")
    public <Type extends Service> Type getService(Class<Type> key) throws PersistentException {
        Class<? extends ServiceImpl> value = SERVICES.get(key);
        if (value != null) {
            try {
                ServiceImpl service = value.newInstance();
                service.setDaoFactory(factory);
                return (Type) service;
            } catch (InstantiationException | IllegalAccessException e) {
                logger.error("It is impossible to instance service class", e);
                throw new PersistentException(e);
            }
        }
        return null;
    }

    /**
     * Закрытие сервиса
     */
    @Override
    public void close() {
        factory.close();
    }
}
