package tech.lapsa.esbd.connection.beans;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;

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

    private static final int SESSION_TTL_SECONDS = 10;
    private static final int SESSION_CHECK_INTERVAL_MINUTE = 2;

    private final MyLogger logger = MyLogger.newBuilder() //
	    .withNameOf(ConnectionPool.class) //
	    .build();

    @Resource(mappedName = JNDI_ESBD_POOL_CONFIGURATION_PROPERTIES)
    private Properties config;

    final LinkedList<SoapSession> activeSessions = new LinkedList<>();
    final Set<SoapSession> allSessions = new HashSet<>();

    @PostConstruct
    public void init() {
	final String esbdUserName = config.getProperty(PROPERTY_USER_NAME);
	final String esbdUserPassword = config.getProperty(PROPERTY_USER_PASSWORD);
	final int connectTimeoutMilis = Integer.parseInt(config.getProperty(PROPERTY_CONNECT_TIMEOUT_MILIS, "3000"));
	final int requestTimeoutMilis = Integer.parseInt(config.getProperty(PROPERTY_REQUEST_TIMEOUT_MILIS, "5000"));

	for (final Object k : config.keySet()) {
	    final String key = (String) k;
	    if (key.startsWith(PROPERTY_PREFXIX_WSDL_LOCATION)) {
		final String urlstr = config.getProperty(key);
		try {
		    final URL wsdlLocation = new URL(urlstr);
		    final SoapSession ss = new SoapSession(wsdlLocation, esbdUserName, esbdUserPassword,
			    connectTimeoutMilis, requestTimeoutMilis, SESSION_TTL_SECONDS * 1000);
		    logger.FINE.log("CREATED %1$s", ss);
		    allSessions.add(ss);
		} catch (final MalformedURLException e) {
		    logger.SEVERE.log(e, "INVALID ESBD WS URL '%1$s'", urlstr);
		}
	    }
	}
	check();
    }

    @Override
    public Connection getConnection() throws ConnectionException {
	try {
	    final SoapSession session;
	    synchronized (activeSessions) {
		activeSessions.add(session = activeSessions.remove());
	    }
	    return new ConnectionImpl(session);
	} catch (NoSuchElementException e) {
	    throw new ConnectionException("No ESBD connection available");
	}
    }

    @Schedule(hour = "*", minute = "*/" + SESSION_CHECK_INTERVAL_MINUTE)
    public void check() {
	for (SoapSession session : allSessions) {
	    logger.INFO.log("CHECKING %1$s...", session);
	    try {
		session.ping();
		if (!activeSessions.contains(session))
		    synchronized (activeSessions) {
			if (!activeSessions.contains(session)) {
			    activeSessions.add(session);
			    logger.INFO.log("ENABLING %1$s", session);
			}
		    }
	    } catch (Exception e) {
		logger.FINE.log(e);
		if (activeSessions.contains(session))
		    synchronized (activeSessions) {
			if (activeSessions.contains(session)) {
			    activeSessions.remove(session);
			    logger.WARN.log("DISABLING %1$s...", session);
			}
		    }
	    }
	}
    }

    @Schedule(hour = "*", minute = "*", second = "*/30") // dump every 30 second
    public void logStatus() {
	for (SoapSession session : allSessions) {
	    logger.FINE.log("STATE %1$s %2$s", activeSessions.contains(session) ? "ENABLED" : "DISABLED", session);
	}
    }
}
