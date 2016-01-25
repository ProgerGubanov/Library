package by.epam.library.dao.mysql;

import java.sql.Connection;

/**
 * Created by Gubanov Andrey on 16.12.2015.
 */

abstract public class BaseDaoImpl {
	protected Connection connection;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
}
