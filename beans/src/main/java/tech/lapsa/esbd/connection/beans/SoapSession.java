package tech.lapsa.esbd.connection.beans;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.logging.Logger;

import javax.xml.ws.BindingProvider;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import tech.lapsa.esbd.connection.ConnectionException;
import tech.lapsa.esbd.jaxws.IICWebService;
import tech.lapsa.esbd.jaxws.IICWebServiceSoap;
import tech.lapsa.esbd.jaxws.User;

public class SoapSession {
    private static final int PRIME = 23;
    private static final int MULTIPLIER = PRIME;

    private final URL wsdlLocation;
    private final String userName;
    private final String password;
    private final Logger logger;
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
    public boolean equals(Object other) {
	if (other == null || other.getClass() != getClass())
	    return false;
	if (other == this)
	    return true;
	SoapSession that = (SoapSession) other;
	return new EqualsBuilder()
		.append(wsdlLocation, that.wsdlLocation)
		.isEquals();
    }

    @Override
    public String toString() {
	return String.format("%1$s('%2$s')", this.getClass().getSimpleName(), wsdlLocation.toString());
    }

    public SoapSession(URL wsdlLocation, String userName, String password, Logger logger, int connectTimeoutMilis,
	    int requestTimeoutMilis, int reCheckTimeoutMilis) {
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
	} catch (Exception e) {
	    String message = String.format("Failed initialize web-service '%1$s' with error message '%2$s'",
		    IICWebService.class.getSimpleName(), e.getMessage());
	    throw new ConnectionException(message);
	}
	if (service == null) {
	    String message = String.format("Failed initialize web-service '%1$s'. Value is null",
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
	} catch (Exception e) {
	    String message = String.format("Failed initialize SOAP '%1$s'. with error message '%2$s'",
		    IICWebServiceSoap.class.getSimpleName(), e.getMessage());
	    throw new ConnectionException(message);
	}
	if (soap == null) {
	    String message = String.format("Failed initialize SOAP '%1$s'. Value is null",
		    IICWebServiceSoap.class.getSimpleName());
	    throw new ConnectionException(message);
	}
    }

    private void initSession() throws ConnectionException {
	if (checkSession())
	    return;
	try {
	    if (sessionId == null)
		logger.info(String.format("NEW %1$s for user '%2$s'", this, userName));
	    else
		logger.info(String.format("RE-ESTABLISHED %1$s for user '%2$s'", this, userName));

	    User user = soap.authenticateUser(userName, password);
	    sessionId = user.getSessionID();
	    okChecked();
	    logger.info(String.format("ESTABLISHED SUCCESSUFUL %1$s aSessionID = '%2$s'", this, sessionId));
	} catch (Exception e) {
	    notChecked();
	    String message = String.format("FAILED TO ESTABLISH %1$s error message = '%2$s'", this, e.getMessage());
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
	    boolean checked = soap.sessionExists(sessionId, userName);
	    if (checked)
		okChecked();
	    return checked;
	} catch (Exception e) {
	    String message = String.format("FAILED TO CHECK %1$s aSessionID = '%2$s' error message = '%3$s'",
		    this, // 1
		    sessionId, // 2
		    e.getMessage()// 3
	    );
	    throw new ConnectionException(message, e);
	}
    }
}
