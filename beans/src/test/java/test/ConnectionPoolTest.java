package test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;

import tech.lapsa.esbd.connection.Connection;
import tech.lapsa.esbd.connection.ConnectionException;
import tech.lapsa.esbd.connection.ConnectionPool;

public class ConnectionPoolTest extends ArquillianBaseTestCase {

    @Inject
    private ConnectionPool pool;

    @Test
    public void testNotNull() {
	assertThat(pool, not(nullValue()));
    }

    @Test
    public void testGetConnection() throws ConnectionException {
	Connection prev = null;
	for (int i = 0; i < 20; i++)
	    try (Connection conn1 = pool.getConnection()) {
		assertThat(conn1, not(nullValue()));
		assertThat(conn1, is(not(prev)));
		prev = conn1;
	    }
    }

}
