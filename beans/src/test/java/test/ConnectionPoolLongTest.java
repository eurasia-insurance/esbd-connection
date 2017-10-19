package test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.logging.Logger;

import javax.inject.Inject;

import tech.lapsa.esbd.connection.Connection;
import tech.lapsa.esbd.connection.ConnectionException;
import tech.lapsa.esbd.connection.ConnectionPool;

public class ConnectionPoolLongTest
{

    @Inject
    private Logger logger;

    @Inject
    private ConnectionPool pool;

//    @Test
    public void longTest() throws ConnectionException, InterruptedException {
	for (int i = 0; i < 1000; i++) {
	    try (Connection conn1 = pool.getConnection()) {
		assertThat(conn1, not(nullValue()));
		logger.info(conn1 + " is alive");
		Thread.sleep(2000);
	    }
	}
    }

}
