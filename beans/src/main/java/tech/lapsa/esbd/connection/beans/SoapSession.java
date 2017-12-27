package tech.lapsa.esbd.connection.beans;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.time.Instant;
import java.util.Map;

import javax.xml.ws.BindingProvider;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import tech.lapsa.esbd.connection.ConnectionException;
import tech.lapsa.esbd.jaxws.wsimport.IICWebService;
import tech.lapsa.esbd.jaxws.wsimport.IICWebServiceSoap;
import tech.lapsa.esbd.jaxws.wsimport.User;
import tech.lapsa.java.commons.function.MyExceptions;
import tech.lapsa.java.commons.logging.MyLogger;

public class SoapSession {
    private static final int PRIME = 23;
    private static final int MULTIPLIER = PRIME;

    private final URL wsdlLocation;
    private final String userName;
    private final String password;
    private final MyLogger logger;
    private final int connectTimeoutMilis;
    private final int requestTimeoutMilis;
    private final int reCheckEsbdSesionAliveTimeoutMilis;

    private IICWebService service;
    private IICWebServiceSoap soap;
    private String sessionId;
    private Instant lastSessionCheckOKInstant;

    @Override
    public int hashCode() {
	return new HashCodeBuilder(PRIME, MULTIPLIER)
		.append(wsdlLocation)
		.toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
	if (other == null || other.getClass() != getClass())
	    return false;
	if (other == this)
	    return true;
	final SoapSession that = (SoapSession) other;
	return new EqualsBuilder()
		.append(wsdlLocation, that.wsdlLocation)
		.isEquals();
    }

    @Override
    public String toString() {
	return String.format("%1$s('%2$s')", this.getClass().getSimpleName(), wsdlLocation.toString());
    }

    public SoapSession(final URL wsdlLocation,
	    final String userName,
	    final String password,
	    final MyLogger logger,
	    final int connectTimeoutMilis,
	    final int requestTimeoutMilis,
	    final int reCheckEsbdSesionAliveTimeoutMilis) {
	this.wsdlLocation = wsdlLocation;
	this.userName = userName;
	this.password = password;
	this.logger = logger;
	this.connectTimeoutMilis = connectTimeoutMilis;
	this.requestTimeoutMilis = requestTimeoutMilis;
	this.reCheckEsbdSesionAliveTimeoutMilis = reCheckEsbdSesionAliveTimeoutMilis;
    }

    public IICWebServiceSoap getSoap() throws ConnectionException {
	initService();
	initSoap();
	return soap;
    }

    public String getSessionId() throws ConnectionException {
	initService();
	initSoap();
	initSession();
	return sessionId;
    }

    public void ping() throws ConnectionException {
	initService();
	initSoap();
	initSession();
    }

    public URL getWsdlLocation() {
	return wsdlLocation;
    }

    public void reset() {
	notChecked();
    }

    // PRIVATE

    private synchronized void okChecked() {
	lastSessionCheckOKInstant = Instant.now();
    }

    private synchronized void notChecked() {
	lastSessionCheckOKInstant = null;
    }

    private synchronized boolean checkingExpired() {
	return lastSessionCheckOKInstant == null
		|| lastSessionCheckOKInstant.isBefore(Instant.now().minusMillis(reCheckEsbdSesionAliveTimeoutMilis));
    }

    private void initService() throws ConnectionException {
	if (service == null) {
	    try {
		logger.INFO.log("PING URL %1$s with TIMEOUT %2$s mills", wsdlLocation, connectTimeoutMilis);
		final URLConnection connection = wsdlLocation.openConnection();
		connection.setConnectTimeout(connectTimeoutMilis);
		connection.connect();
		logger.INFO.log("PING URL SUCCESSFUL %1$s", wsdlLocation);
	    } catch (IOException e) {
		logger.SEVERE.log("PING URL FAILED %1$s", wsdlLocation);
		final ConnectionException ex = MyExceptions.format(ConnectionException::new, e,
			"Failed to PING %1$s (%2$s)", wsdlLocation, e.getMessage());
		logger.SEVERE.log(ex);
		throw ex;
	    }
	    try {
		service = new IICWebService(wsdlLocation);
		logger.TRACE.log("WS CREATED %1$s", wsdlLocation);
	    } catch (final Exception e) {
		logger.SEVERE.log("WS CREATION FAILED %1$s", wsdlLocation);
		final ConnectionException ex = MyExceptions.format(ConnectionException::new, e,
			"Failed initialize WS '%1$s' (%2$s)", IICWebService.class.getName(), e.getMessage());
		logger.SEVERE.log(ex);
		throw ex;
	    }
	}
    }

    private void initSoap() throws ConnectionException {
	try {
	    if (soap == null) {
		soap = service.getIICWebServiceSoap();
		logger.TRACE.log("SOAP CREATED %1$s", service.getWSDLDocumentLocation());
		final Map<String, Object> context = ((BindingProvider) soap).getRequestContext();
		context.put("com.sun.xml.internal.ws.connect.timeout", connectTimeoutMilis);
		context.put("com.sun.xml.internal.ws.request.timeout", requestTimeoutMilis);
		context.put("com.sun.xml.ws.request.timeout", requestTimeoutMilis);
		context.put("com.sun.xml.ws.connect.timeout", connectTimeoutMilis);
		context.put("javax.xml.ws.client.connectionTimeout", connectTimeoutMilis);
		context.put("javax.xml.ws.client.receiveTimeout", requestTimeoutMilis);
		context.put(BindingProviderProperties.REQUEST_TIMEOUT, requestTimeoutMilis);
		context.put(BindingProviderProperties.CONNECT_TIMEOUT, connectTimeoutMilis);
	    }
	} catch (final Exception e) {
	    logger.TRACE.log(e);
	    final ConnectionException ex = MyExceptions.format(ConnectionException::new, e,
		    "Failed initialize SOAP '%1$s' (%2$s)", service.getWSDLDocumentLocation(), e.getMessage());
	    logger.SEVERE.log(ex);
	    throw ex;
	}
    }

    private void initSession() throws ConnectionException {
	if (isSessionAvailable())
	    return;
	try {
	    if (sessionId == null)
		logger.INFO.log("NEW %1$s for user '%2$s'", this, userName);
	    else
		logger.INFO.log("RE-ESTABLISHED %1$s for user '%2$s'", this, userName);

	    final User user = soap.authenticateUser(userName, password);
	    sessionId = user.getSessionID();
	    okChecked();
	    logger.INFO.log("ESTABLISHED SUCCESSUFUL %1$s aSessionID = '%2$s'", this, sessionId);
	} catch (final Exception e) {
	    notChecked();
	    final String message = String.format("FAILED TO ESTABLISH %1$s error message = '%2$s'", this,
		    e.getMessage());
	    throw new ConnectionException(message, e);
	}
    }

    private synchronized boolean isSessionAvailable() throws ConnectionException {
	// if sessionId is not set - false
	if (sessionId == null)
	    return false;

	// if not checked or last check OK is early than X time later - true
	if (checkingExpired())
	    return true;

	notChecked();
	try {
	    final boolean checked = soap.sessionExists(sessionId, userName);
	    if (checked)
		okChecked();
	    return checked;
	} catch (final Exception e) {
	    final String message = String.format("FAILED TO CHECK %1$s aSessionID = '%2$s' error message = '%3$s'",
		    this, // 1
		    sessionId, // 2
		    e.getMessage()// 3
	    );
	    throw new ConnectionException(message, e);
	}
    }
}
