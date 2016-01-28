package by.epam.library.dao.mysql;

import java.sql.Connection;

/**
 * Абстрактный базовый класс DAO
 *
 * @author Gubanov Andrey
 */
abstract public class BaseDaoImpl {
    protected Connection connection;

    /**
     * Установка соединения
     *
     * @param connection соединение
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
