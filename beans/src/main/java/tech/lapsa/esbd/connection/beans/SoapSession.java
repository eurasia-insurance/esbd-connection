package tech.lapsa.esbd.connection.beans;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.time.Instant;
import java.util.Map;

import javax.ejb.EJBException;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.SOAPFaultException;

import tech.lapsa.esbd.connection.ConnectionException;
import tech.lapsa.esbd.connection.ConnectionPool;
import tech.lapsa.esbd.jaxws.wsimport.IICWebService;
import tech.lapsa.esbd.jaxws.wsimport.IICWebServiceSoap;
import tech.lapsa.esbd.jaxws.wsimport.User;
import tech.lapsa.java.commons.function.MyExceptions;
import tech.lapsa.java.commons.function.MyStrings;
import tech.lapsa.java.commons.logging.MyLogger;
import tech.lapsa.java.commons.time.MyTemporals;

final class SoapSession {

    private final MyLogger logger;

    private final URL wsdlLocation;
    private final String userName;
    private final String password;
    private final int connectTimeoutMilis;
    private final int requestTimeoutMilis;

    private final InstantMarker marker;

    private IICWebService service;
    private IICWebServiceSoap soap;

    private SessionId sessionId;

    private User user;

    private transient ConnectionException lastCheckException;
    private transient Instant lastCheckInstant;

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((wsdlLocation == null) ? 0 : wsdlLocation.toString().hashCode());
	result = prime * result + ((userName == null) ? 0 : userName.hashCode());
	result = prime * result + ((password == null) ? 0 : password.hashCode());
	return result;
    }

    ConnectionException getLastCheckException() {
        return lastCheckException;
    }

    Instant getLastCheckInstant() {
        return lastCheckInstant;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (!(obj instanceof SoapSession))
	    return false;

	final SoapSession other = (SoapSession) obj;

	// wsdlLocation
	if (wsdlLocation == null) {
	    if (other.wsdlLocation != null)
		return false;
	} else if (!wsdlLocation.equals(other.wsdlLocation))
	    return false;

	// userName
	if (userName == null) {
	    if (other.userName != null)
		return false;
	} else if (!userName.equals(other.userName))
	    return false;

	// password
	if (password == null) {
	    if (other.password != null)
		return false;
	} else if (!password.equals(other.password))
	    return false;

	return true;
    }

    @Override
    public String toString() {
	return wsdlLocation.toString();
    }

    public SoapSession(final URL wsdlLocation,
	    final String userName,
	    final String password,
	    final int connectTimeoutMilis,
	    final int requestTimeoutMilis,
	    final long reCheckEsbdSesionAliveTimeoutMilis) {
	this.wsdlLocation = wsdlLocation;
	this.userName = userName;
	this.password = password;
	this.connectTimeoutMilis = connectTimeoutMilis;
	this.requestTimeoutMilis = requestTimeoutMilis;
	this.marker = new InstantMarker(reCheckEsbdSesionAliveTimeoutMilis);
	this.logger = MyLogger.newBuilder() //
		.withNameOf(ConnectionPool.class) //
		.addPrefix(MyStrings.format("* %1$s * ", wsdlLocation.toString()))
		.build();
    }

    @FunctionalInterface
    static interface SoapCallable {
	void call(IICWebServiceSoap soap, String aSession) throws SOAPFaultException;
    }

    @FunctionalInterface
    static interface SoapSupplier<R> {
	R get(IICWebServiceSoap soap, String aSession) throws SOAPFaultException;
    }

    @FunctionalInterface
    static interface SoapIntSupplier {
	int getAsInt(IICWebServiceSoap soap, String aSession) throws SOAPFaultException;
    }

    @FunctionalInterface
    static interface SoapDoubleSupplier {
	double getAsInt(IICWebServiceSoap soap, String aSession) throws SOAPFaultException;
    }

    <R> R call(final SoapSupplier<R> supplier) {
	try {
	    final R res = supplier.get(soap, sessionId.sesionId);
	    marker.mark(); // call is ok also session is ok too
	    return res;
	} catch (final SOAPFaultException e) {
	    marker.mark(); // call is ok also session is ok too
	    logger.DEBUG.log(e.getMessage());
	    logger.TRACE.log(e);
	    return null;
	} catch (final RuntimeException e) {
	    marker.expire(); // call is not ok
	    logger.WARN.log(e);
	    logger.TRACE.log(e);
	    throw new EJBException(e.getMessage());
	}
    }

    void callVoid(final SoapCallable consumer) {
	try {
	    consumer.call(soap, sessionId.sesionId);
	    marker.mark(); // call is ok also session is ok too
	} catch (final SOAPFaultException e) {
	    marker.mark(); // call is ok also session is ok too
	    logger.SEVERE.log(e.getMessage());
	    logger.TRACE.log(e);
	} catch (final RuntimeException e) {
	    marker.expire(); // call is not ok
	    logger.WARN.log(e);
	    logger.TRACE.log(e);
	    throw new EJBException(e.getMessage());
	}
    }

    int callInt(final SoapIntSupplier consumer, int defaultReturn) {
	try {
	    final int res = consumer.getAsInt(soap, sessionId.sesionId);
	    marker.mark(); // call is ok also session is ok too
	    return res;
	} catch (final SOAPFaultException e) {
	    marker.mark(); // call is ok also session is ok too
	    logger.TRACE.log(e);
	    return defaultReturn;
	} catch (final RuntimeException e) {
	    marker.expire(); // call is not ok
	    logger.WARN.log(e);
	    logger.TRACE.log(e);
	    throw new EJBException(e.getMessage());
	}
    }

    double callDouble(final SoapDoubleSupplier consumer, double defaultReturn) {
	try {
	    final double res = consumer.getAsInt(soap, sessionId.sesionId);
	    marker.mark(); // call is ok also session is ok too
	    return res;
	} catch (final SOAPFaultException e) {
	    marker.mark(); // call is ok also session is ok too
	    logger.TRACE.log(e);
	    return defaultReturn;
	} catch (final RuntimeException e) {
	    marker.expire(); // call is not ok
	    logger.WARN.log(e);
	    logger.TRACE.log(e);
	    throw new EJBException(e.getMessage());
	}
    }

    public void ping() throws ConnectionException {
	try {
	    lastCheckInstant = Instant.now();
	    initService();
	    initSoap();
	    pingOrInitSession();
	    lastCheckException = null;
	} catch (ConnectionException e) {
	    lastCheckException = e;
	    throw e;
	}
    }

    protected User getUser() {
	return user;
    }

    // PRIVATE

    private void initService() throws ConnectionException {
	if (service == null) {
	    try {
		logger.TRACE.log("PING URL with TIMEOUT %1$s mills", connectTimeoutMilis);
		final URLConnection connection = wsdlLocation.openConnection();
		connection.setConnectTimeout(connectTimeoutMilis);
		connection.connect();
		logger.TRACE.log("PING URL SUCCESSFUL");
	    } catch (final IOException e) {
		logger.WARN.log("PING URL FAILED (%1$s)", e.getMessage());
		logger.TRACE.log(e);
		final ConnectionException ex //
			= MyExceptions.format(ConnectionException::new, "PING URL FAILED %1$s (%2$s)", wsdlLocation,
				e.getMessage());
		throw ex;
	    }
	    try {
		service = new IICWebService(wsdlLocation);
		logger.TRACE.log("WS CREATED");
	    } catch (final Exception e) {
		logger.WARN.log("WS CREATION FAILED (%1$s)", e.getMessage());
		logger.TRACE.log(e);
		final ConnectionException ex //
			= MyExceptions.format(ConnectionException::new, "WS CREATION FAILED '%1$s' (%2$s)",
				wsdlLocation, e.getMessage());
		throw ex;
	    }
	}
    }

    private void initSoap() throws ConnectionException {
	try {
	    if (soap == null) {
		logger.TRACE.log("SOAP CREATING with TIMEOUT %1$s mills", connectTimeoutMilis);
		soap = service.getIICWebServiceSoap();
		logger.TRACE.log("SOAP CREATED");
		final Map<String, Object> context = ((BindingProvider) soap).getRequestContext();
		context.put("com.sun.xml.internal.ws.connect.timeout", connectTimeoutMilis);
		context.put("com.sun.xml.internal.ws.request.timeout", requestTimeoutMilis);
		context.put("javax.xml.ws.client.connectionTimeout", connectTimeoutMilis);
		context.put("javax.xml.ws.client.receiveTimeout", requestTimeoutMilis);
		context.put(BindingProviderProperties.CONNECT_TIMEOUT, connectTimeoutMilis);
		context.put(BindingProviderProperties.REQUEST_TIMEOUT, requestTimeoutMilis);
	    }
	} catch (final Exception e) {
	    logger.WARN.log("SOAP CREATION FAILED (%1$s)", e.getMessage());
	    logger.TRACE.log(e);
	    final ConnectionException ex //
		    = MyExceptions.format(ConnectionException::new, "SOAP CREATION FAILED %1$s (%2$s)", wsdlLocation,
			    e.getMessage());
	    throw ex;
	}
    }

    private static class SessionId {

	private final String sesionId;
	private final Instant created;

	private SessionId(final String sessionId) {
	    this.sesionId = sessionId;
	    this.created = Instant.now();
	}

	@Override
	public String toString() {
	    return MyStrings.format("'%1$s' @ [%2$s]", sesionId, MyTemporals.instant().toLocalDateTime(created));
	}
    }

    private synchronized void pingOrInitSession() throws ConnectionException {
	logger.TRACE.log("PING OR INIT SESSION");

	if (sessionId == null)
	    logger.TRACE.log("GENERATING NEW SESSION for user '%1$s'", userName);
	else
	    logger.TRACE.log("USING EXISTING SESSION %1$s", sessionId);

	if (!marker.isExpired()) {
	    logger.TRACE.log("SESSION IS NOT EXPIRED YET %1$s", sessionId);
	    return;
	}

	while (true) {
	    if (sessionId == null) {
		// final User user;
		try {
		    user = soap.authenticateUser(userName, password);
		} catch (final WebServiceException e) {
		    logger.TRACE.log(e);
		    marker.expire();
		    throw MyExceptions.format(ConnectionException::new, "AUTHENTIFICATION FAILED for user '%1$s'",
			    userName);
		}
		sessionId = new SessionId(user.getSessionID());
	    }

	    final boolean checked;
	    try {
		checked = soap.sessionExists(sessionId.sesionId, userName);
	    } catch (final WebServiceException e) {
		logger.WARN.log("PING SESSION FAILED (%1$s)", e.getMessage());
		logger.TRACE.log(e);
		throw MyExceptions.format(ConnectionException::new, "PING SESSION FAILED %1$s (%2$s)", wsdlLocation,
			e.getMessage());
	    }

	    if (checked) {
		logger.TRACE.log("SESSION IS OK %1$s", sessionId);
		marker.mark();
		return;
	    } else {
		logger.TRACE.log("SESSION IS EXPIRED %1$s", sessionId);
		logger.TRACE.log("SESSION RENEWAL IS REQUIRED %1$s", sessionId);
		sessionId = null;
		marker.expire();
	    }
	}
    }
}
