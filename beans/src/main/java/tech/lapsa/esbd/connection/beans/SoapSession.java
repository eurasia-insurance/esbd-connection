package tech.lapsa.esbd.connection.beans;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.xml.ws.BindingProvider;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import tech.lapsa.esbd.connection.ConnectionException;
import tech.lapsa.esbd.jaxws.wsimport.IICWebService;
import tech.lapsa.esbd.jaxws.wsimport.IICWebServiceSoap;
import tech.lapsa.esbd.jaxws.wsimport.User;
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
    private final int reCheckTimeoutMilis;

    private IICWebService service;
    private IICWebServiceSoap soap;
    private String sessionId;
    private LocalDateTime lastCheckOK;

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

    public SoapSession(final URL wsdlLocation, final String userName, final String password, final MyLogger logger,
	    final int connectTimeoutMilis,
	    final int requestTimeoutMilis, final int reCheckTimeoutMilis) {
	this.wsdlLocation = wsdlLocation;
	this.userName = userName;
	this.password = password;
	this.logger = logger;
	this.connectTimeoutMilis = connectTimeoutMilis;
	this.requestTimeoutMilis = requestTimeoutMilis;
	this.reCheckTimeoutMilis = reCheckTimeoutMilis;
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

    private void okChecked() {
	lastCheckOK = LocalDateTime.now();
    }

    private void notChecked() {
	lastCheckOK = null;
    }

    private void initService() throws ConnectionException {
	try {
	    if (service == null)
		service = new IICWebService(wsdlLocation);
	} catch (final Exception e) {
	    final String message = String.format("Failed initialize web-service '%1$s' with error message '%2$s'",
		    IICWebService.class.getSimpleName(), e.getMessage());
	    throw new ConnectionException(message);
	}
	if (service == null) {
	    final String message = String.format("Failed initialize web-service '%1$s'. Value is null",
		    IICWebService.class.getSimpleName());
	    throw new ConnectionException(message);
	}
    }

    private void initSoap() throws ConnectionException {
	try {
	    if (soap == null) {
		soap = service.getIICWebServiceSoap();
		((BindingProvider) soap).getRequestContext().put(BindingProviderProperties.REQUEST_TIMEOUT,
			requestTimeoutMilis);
		((BindingProvider) soap).getRequestContext().put(BindingProviderProperties.CONNECT_TIMEOUT,
			connectTimeoutMilis);
	    }
	} catch (final Exception e) {
	    final String message = String.format("Failed initialize SOAP '%1$s'. with error message '%2$s'",
		    IICWebServiceSoap.class.getSimpleName(), e.getMessage());
	    throw new ConnectionException(message);
	}
	if (soap == null) {
	    final String message = String.format("Failed initialize SOAP '%1$s'. Value is null",
		    IICWebServiceSoap.class.getSimpleName());
	    throw new ConnectionException(message);
	}
    }

    private void initSession() throws ConnectionException {
	if (checkSession())
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

    private boolean checkSession() throws ConnectionException {
	// if sessionId is not set - false
	if (sessionId == null)
	    return false;

	// if not checked or last check OK is early than X time later - true
	if (lastCheckOK != null
		&& lastCheckOK.isAfter(LocalDateTime.now().minus(reCheckTimeoutMilis, ChronoUnit.MILLIS)))
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
