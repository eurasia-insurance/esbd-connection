package test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;

import tech.lapsa.esbd.connection.Connection;
import tech.lapsa.esbd.connection.ConnectionException;
import tech.lapsa.esbd.connection.ConnectionPool;

public class ConnectionPoolLongTest
	extends ArquillianBaseTestCase //
{

    @Inject
    private ConnectionPool pool;

    @Test
    public void longTest() throws InterruptedException {
	for (int i = 0; i < 1000; i++) {
	    try (Connection conn1 = pool.getConnection()) {
		assertThat(conn1, not(nullValue()));
	    } catch (ConnectionException e) {
		System.out.println(e.getMessage());
	    }
	    Thread.sleep(2000);
	}
    }

}
