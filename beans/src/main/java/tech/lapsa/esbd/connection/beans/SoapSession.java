package tech.lapsa.esbd.connection.beans;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import javax.ejb.EJBException;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceException;

import tech.lapsa.esbd.connection.ConnectionException;
import tech.lapsa.esbd.jaxws.wsimport.IICWebService;
import tech.lapsa.esbd.jaxws.wsimport.IICWebServiceSoap;
import tech.lapsa.esbd.jaxws.wsimport.User;
import tech.lapsa.java.commons.function.MyExceptions;
import tech.lapsa.java.commons.logging.MyLogger;

public class SoapSession {

    private final MyLogger logger = MyLogger.newBuilder() //
	    .withNameOf(SoapSession.class) //
	    .build();

    private final URL wsdlLocation;
    private final String userName;
    private final String password;
    private final int connectTimeoutMilis;
    private final int requestTimeoutMilis;

    private final InstantMarker marker;

    private IICWebService service;
    private IICWebServiceSoap soap;
    private String sessionId;

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
    }

    @FunctionalInterface
    static interface SoapProcessable {
	void process(IICWebServiceSoap soap, String aSession);
    }

    void process(SoapProcessable consumer) {
	try {
	    consumer.process(soap, sessionId);
	} catch (WebServiceException e) {
	    throw new EJBException(e.getMessage());
	}
    }

    @FunctionalInterface
    static interface SoapCallable<R> {
	R call(IICWebServiceSoap soap, String aSession);
    }

    <R> R call(SoapCallable<R> consumer) {
	try {
	    return consumer.call(soap, sessionId);
	} catch (WebServiceException e) {
	    throw new EJBException(e.getMessage());
	}
    }

    public void ping() throws ConnectionException {
	initService();
	initSoap();
	pingOrInitSession();
    }

    // PRIVATE

    private void initService() throws ConnectionException {
	if (service == null) {
	    try {
		logger.TRACE.log("PING URL %1$s with TIMEOUT %2$s mills", wsdlLocation, connectTimeoutMilis);
		final URLConnection connection = wsdlLocation.openConnection();
		connection.setConnectTimeout(connectTimeoutMilis);
		connection.connect();
		logger.TRACE.log("PING URL SUCCESSFUL %1$s", wsdlLocation);
	    } catch (IOException e) {
		logger.WARN.log("PING URL FAILED %1$s (%2$s)", wsdlLocation, e.getMessage());
		final ConnectionException ex //
			= MyExceptions.format(ConnectionException::new, "PING URL FAILED %1$s (%2$s)", wsdlLocation,
				e.getMessage());
		throw ex;
	    }
	    try {
		service = new IICWebService(wsdlLocation);
		logger.TRACE.log("WS CREATED %1$s", wsdlLocation);
	    } catch (final Exception e) {
		logger.WARN.log("WS CREATION FAILED %1$s (%2$s)", wsdlLocation, e.getMessage());
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
		logger.TRACE.log("SOAP CREATING %1$s with TIMEOUT %2$s mills", wsdlLocation, connectTimeoutMilis);
		soap = service.getIICWebServiceSoap();
		logger.TRACE.log("SOAP CREATED %1$s", service.getWSDLDocumentLocation());
		final Map<String, Object> context = ((BindingProvider) soap).getRequestContext();
		context.put("com.sun.xml.internal.ws.connect.timeout", connectTimeoutMilis);
		context.put("com.sun.xml.internal.ws.request.timeout", requestTimeoutMilis);
		context.put("javax.xml.ws.client.connectionTimeout", connectTimeoutMilis);
		context.put("javax.xml.ws.client.receiveTimeout", requestTimeoutMilis);
		context.put(BindingProviderProperties.CONNECT_TIMEOUT, connectTimeoutMilis);
		context.put(BindingProviderProperties.REQUEST_TIMEOUT, requestTimeoutMilis);
	    }
	} catch (final Exception e) {
	    logger.WARN.log("SOAP CREATION FAILED %1$s (%2$s)", wsdlLocation, e.getMessage());
	    final ConnectionException ex //
		    = MyExceptions.format(ConnectionException::new, "SOAP CREATION FAILED %1$s (%2$s)", wsdlLocation,
			    e.getMessage());
	    throw ex;
	}
    }

    private synchronized void pingOrInitSession() throws ConnectionException {
	logger.TRACE.log("PING SESSION %1$s", wsdlLocation);

	if (sessionId == null)
	    logger.TRACE.log("NEW SESSION %1$s for user '%2$s'", wsdlLocation, userName);
	else
	    logger.TRACE.log("EXISTING SESSION %1$s with ID %2$s", wsdlLocation, sessionId);

	if (!marker.isExpired()) {
	    logger.TRACE.log("SESSION IS NOT EXPIRED YET %1$s", wsdlLocation);
	    return;
	}

	while (true) {
	    if (sessionId == null) {
		final User user;
		try {
		    user = soap.authenticateUser(userName, password);
		} catch (WebServiceException e) {
		    marker.expire();
		    throw MyExceptions.format(ConnectionException::new, "AUTHENTIFICATION FAILED for user '%1$s'",
			    userName);
		}
		sessionId = user.getSessionID();
	    }

	    final boolean checked;
	    try {
		checked = soap.sessionExists(sessionId, userName);
	    } catch (WebServiceException e) {
		logger.WARN.log("PING SESSION FAILED %1$s (%2$s)", wsdlLocation, e.getMessage());
		throw MyExceptions.format(ConnectionException::new, "PING SESSION FAILED %1$s (%2$s)", wsdlLocation,
			e.getMessage());
	    }

	    if (checked) {
		logger.TRACE.log("SESSION IS OK %2$s", wsdlLocation, sessionId);
		marker.mark();
		return;
	    } else {
		logger.TRACE.log("(%1$s) SESSION EXPIRED %2$s (%1$s)", wsdlLocation, sessionId);
		logger.TRACE.log("(%1$s) RE-NEW SESSION for user '%2$s' (%1$s)", wsdlLocation, userName);
		sessionId = null;
		marker.expire();
	    }
	}
    }
}
