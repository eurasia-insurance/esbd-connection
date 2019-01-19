package tech.lapsa.esbd.connection;

import java.util.List;

import javax.ejb.Local;

@Local
public interface ConnectionPool {

    Connection getConnection() throws ConnectionException;

    List<ConnectionStatus> getPoolStatus();
}
