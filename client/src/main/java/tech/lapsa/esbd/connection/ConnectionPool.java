package tech.lapsa.esbd.connection;

import javax.ejb.Local;

@Local
public interface ConnectionPool {

    Connection getConnection() throws ConnectionException;
}
