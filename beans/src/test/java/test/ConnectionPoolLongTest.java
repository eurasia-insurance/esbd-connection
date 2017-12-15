package test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import javax.inject.Inject;

import tech.lapsa.esbd.connection.Connection;
import tech.lapsa.esbd.connection.ConnectionException;
import tech.lapsa.esbd.connection.ConnectionPool;
import tech.lapsa.java.commons.logging.MyLogger;

public class ConnectionPoolLongTest {

    private final MyLogger logger = MyLogger.getDefault();

    @Inject
    private ConnectionPool pool;

    // @Test
    public void longTest() throws ConnectionException, InterruptedException {
	for (int i = 0; i < 1000; i++)
	    try (Connection conn1 = pool.getConnection()) {
		assertThat(conn1, not(nullValue()));
		logger.INFO.log(conn1 + " is alive");
		Thread.sleep(2000);
	    }
    }

}
