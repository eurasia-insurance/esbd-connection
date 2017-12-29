package tech.lapsa.esbd.connection.beans;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.ConcurrentLinkedDeque;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import tech.lapsa.esbd.connection.Connection;
import tech.lapsa.esbd.connection.ConnectionException;
import tech.lapsa.esbd.connection.ConnectionPool;
import tech.lapsa.java.commons.logging.MyLogger;

@Singleton
@Startup
public class ConnectionPoolBean implements ConnectionPool {

    private static final String JNDI_ESBD_POOL_CONFIGURATION_PROPERTIES = "esbd/resource/Configuration";
    private static final String PROPERTY_USER_NAME = "esbd.user.name";
    private static final String PROPERTY_USER_PASSWORD = "esbd.user.password";
    private static final String PROPERTY_CONNECT_TIMEOUT_MILIS = "esbd.timeout.connect.milis";
    private static final String PROPERTY_REQUEST_TIMEOUT_MILIS = "esbd.timeout.request.milis";
    private static final String PROPERTY_PREFXIX_WSDL_LOCATION = "esbd.wsdl-location.";

    private String esbdUserName;
    private String esbdUserPassword;
    private int connectTimeoutMilis;
    private int requestTimeoutMilis;

    private final MyLogger logger = MyLogger.newBuilder() //
	    .withNameOf(ConnectionPool.class) //
	    .build();

    @Resource(mappedName = JNDI_ESBD_POOL_CONFIGURATION_PROPERTIES)
    private Properties config;

    @PostConstruct
    public void init() {
	esbdUserName = config.getProperty(PROPERTY_USER_NAME);
	esbdUserPassword = config.getProperty(PROPERTY_USER_PASSWORD);
	connectTimeoutMilis = Integer.parseInt(config.getProperty(PROPERTY_CONNECT_TIMEOUT_MILIS, "3000"));
	requestTimeoutMilis = Integer.parseInt(config.getProperty(PROPERTY_REQUEST_TIMEOUT_MILIS, "5000"));

	for (final Object k : config.keySet()) {
	    final String key = (String) k;
	    if (key.startsWith(PROPERTY_PREFXIX_WSDL_LOCATION)) {
		final String urlstr = config.getProperty(key);
		try {
		    final URL wsdlLocation = new URL(urlstr);
		    final SoapSession ss = new SoapSession(wsdlLocation, esbdUserName, esbdUserPassword,
			    connectTimeoutMilis, requestTimeoutMilis, SESSION_TTL_SECONDS * 1000);
		    addSession(ss);
		} catch (final MalformedURLException e) {
		    logger.SEVERE.log(e, "INVALID ESBD WS URL '%1$s'", urlstr);
		}
	    }

	}
    }

    private static final int SESSION_TTL_SECONDS = 10;
    private static final int SESSION_CHECK_INTERVAL_MINUTE = 5;

    @Schedule(hour = "*", minute = "*/" + SESSION_CHECK_INTERVAL_MINUTE)
    // пинговать соединения
    public void poolCheck() {
	checkDisabled();
	checkActive();
    }

    @Override
    public Connection getConnection() throws ConnectionException {
	final SoapSession session = getActive();
	return new ConnectionImpl(session);
    }

    // PRIVATE

    private final Deque<SoapSession> activeSessions = new ConcurrentLinkedDeque<>();
    private final Deque<SoapSession> disabledSessions = new ConcurrentLinkedDeque<>();

    private void addSession(final SoapSession ss) {
	checkActive(ss);
    }

    private void checkActive() {
	final int size = activeSessions.size();
	if (size == 0)
	    return;
	for (int i = 0; i < size; i++) {
	    final SoapSession ss = activeSessions.poll();
	    if (ss == null)
		continue;
	    checkActive(ss);
	}
    }

    private void checkDisabled() {
	final int size = disabledSessions.size();
	if (size == 0)
	    return;
	for (int i = 0; i < size; i++) {
	    final SoapSession ss = disabledSessions.poll();
	    if (ss == null)
		continue;
	    checkDisabled(ss);
	}
    }

    private boolean checkActive(final SoapSession ss) {
	try {
	    logger.DEBUG.log("REFRESHING %1$s...", ss);
	    ss.ping();
	    activeSessions.offer(ss);
	    logger.DEBUG.log("ALIVE %1$s", ss);
	    return true;
	} catch (final ConnectionException ignored) {
	    disabledSessions.offer(ss);
	    logger.WARN.log("DISABLED %1$s...", ss);
	    return false;
	}
    }

    private void checkDisabled(final SoapSession ss) {
	try {
	    logger.INFO.log("TRYING TO RESTORE %1$s...", ss);
	    ss.ping();
	    activeSessions.offer(ss);
	    logger.INFO.log("RESTORED %1$s", ss);
	} catch (final ConnectionException ignored) {
	    disabledSessions.offer(ss);
	    logger.WARN.log("FAIL TO RESTORE %1$s...", ss);
	}
    }

    private SoapSession getActive() throws ConnectionException {
	try {
	    while (true) {
		final SoapSession ss = activeSessions.pop();
		if (checkActive(ss))
		    return ss;
	    }
	} catch (final NoSuchElementException e) {
	    throw new ConnectionException("No ESBD session available");
	}
    }
}
