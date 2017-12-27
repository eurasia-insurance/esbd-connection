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
import javax.ejb.Timer;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import tech.lapsa.esbd.connection.Connection;
import tech.lapsa.esbd.connection.ConnectionException;
import tech.lapsa.esbd.connection.ConnectionPool;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfCLIENTPBDETAILS;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTAGRICULTURELIST;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSACCIDENT;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSAIR;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSANNUITY;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSAUTO;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSCARGO;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSGPOAIR;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSGPOAUTO;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSGPOOTHER;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSGPOWATER;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSGUARANTEE;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSHEALTH;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSLEGALCOSTS;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSLIFE;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSLOAN;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSLOSSES;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSMORTGAGE;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSOTHERLOSSES;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSPROPERTY;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSRAILWAYS;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSTITLE;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSWATER;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTOSECO;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTOSGPOAUDITORS;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTOSGPODO;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTOSGPONOTARIUS;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTOSGPOPASSENGERS;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTOSGPOTOUR;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTOSRNS;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfClient;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfInsuranceEvent;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfItem;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfMIDDLEMAN;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfMiddlemenPayment;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfPolicy;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfSodParameter;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfString;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfTF;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfTFClasses;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfUnionRecord;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfUser;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfUserCertificate;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfVOITUREMARK;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfVOITUREMODEL;
import tech.lapsa.esbd.jaxws.wsimport.ArrayOfVictimObject;
import tech.lapsa.esbd.jaxws.wsimport.CLIENTPBDETAILS;
import tech.lapsa.esbd.jaxws.wsimport.CONTRACTAGRICULTURELIST;
import tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSACCIDENT;
import tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSAIR;
import tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSANNUITY;
import tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSAUTO;
import tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSCARGO;
import tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSGPOAIR;
import tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSGPOAUTO;
import tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSGPOOTHER;
import tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSGPOWATER;
import tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSGUARANTEE;
import tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSHEALTH;
import tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSLEGALCOSTS;
import tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSLIFE;
import tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSLOAN;
import tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSLOSSES;
import tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSMORTGAGE;
import tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSOTHERLOSSES;
import tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSPROPERTY;
import tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSRAILWAYS;
import tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSTITLE;
import tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSWATER;
import tech.lapsa.esbd.jaxws.wsimport.CONTRACTOSECO;
import tech.lapsa.esbd.jaxws.wsimport.CONTRACTOSGPOAUDITORS;
import tech.lapsa.esbd.jaxws.wsimport.CONTRACTOSGPODO;
import tech.lapsa.esbd.jaxws.wsimport.CONTRACTOSGPONOTARIUS;
import tech.lapsa.esbd.jaxws.wsimport.CONTRACTOSGPOPASSENGERS;
import tech.lapsa.esbd.jaxws.wsimport.CONTRACTOSGPOTOUR;
import tech.lapsa.esbd.jaxws.wsimport.CONTRACTOSRNS;
import tech.lapsa.esbd.jaxws.wsimport.Client;
import tech.lapsa.esbd.jaxws.wsimport.EsbdRequest;
import tech.lapsa.esbd.jaxws.wsimport.EsbdResponse;
import tech.lapsa.esbd.jaxws.wsimport.IECOMMON;
import tech.lapsa.esbd.jaxws.wsimport.IICWebServiceSoap;
import tech.lapsa.esbd.jaxws.wsimport.InsuranceEvent;
import tech.lapsa.esbd.jaxws.wsimport.Item;
import tech.lapsa.esbd.jaxws.wsimport.MIDDLEMAN;
import tech.lapsa.esbd.jaxws.wsimport.NewUserRequest;
import tech.lapsa.esbd.jaxws.wsimport.Policy;
import tech.lapsa.esbd.jaxws.wsimport.REQUEST;
import tech.lapsa.esbd.jaxws.wsimport.TF;
import tech.lapsa.esbd.jaxws.wsimport.VOITUREMARK;
import tech.lapsa.esbd.jaxws.wsimport.VOITUREMODEL;
import tech.lapsa.esbd.jaxws.wsimport.VictimObject;
import tech.lapsa.java.commons.logging.MyLogger;

@Singleton
@Startup
public class ConnectionPoolBean implements ConnectionPool {

    private static final String JNDI_ESBD_POOL_CONFIGURATION_PROPERTIES = "esbd/resource/Configuration";
    private static final String PROPERTY_USER_NAME = "esbd.user.name";
    private static final String PROPERTY_USER_PASSWORD = "esbd.user.password";
    private static final String PROPERTY_CONNECT_TIMEOUT_MILIS = "esbd.timeout.connect.milis";
    private static final String PROPERTY_REQUEST_TIMEOUT_MILIS = "esbd.timeout.request.milis";
    private static final String PROPERTY_SESSION_RECHECK_TIMEOUT_MILIS = "esbd.timeout.esbd-session-re-check.milis";
    private static final String PROPERTY_PREFXIX_WSDL_LOCATION = "esbd.wsdl-location.";

    private String esbdUserName;
    private String esbdUserPassword;
    private int connectTimeoutMilis;
    private int requestTimeoutMilis;
    private int reCheckEsbdSesionAliveTimeoutMilis;

    private final MyLogger logger = MyLogger.newBuilder() //
	    .withNameOf(ConnectionPool.class) //
	    .addInstantPrefix() //
	    .build();

    @Resource(mappedName = JNDI_ESBD_POOL_CONFIGURATION_PROPERTIES)
    private Properties config;

    private final Deque<SoapSession> activeSessions = new ConcurrentLinkedDeque<>();
    private final Deque<SoapSession> deferredSessions = new ConcurrentLinkedDeque<>();

    @PostConstruct
    public void init() {
	esbdUserName = config.getProperty(PROPERTY_USER_NAME);
	esbdUserPassword = config.getProperty(PROPERTY_USER_PASSWORD);
	connectTimeoutMilis = Integer.parseInt(config.getProperty(PROPERTY_CONNECT_TIMEOUT_MILIS, "3000"));
	requestTimeoutMilis = Integer.parseInt(config.getProperty(PROPERTY_REQUEST_TIMEOUT_MILIS, "5000"));
	reCheckEsbdSesionAliveTimeoutMilis = Integer
		.parseInt(config.getProperty(PROPERTY_SESSION_RECHECK_TIMEOUT_MILIS, "5000"));

	for (final Object k : config.keySet()) {
	    final String key = (String) k;
	    if (key.startsWith(PROPERTY_PREFXIX_WSDL_LOCATION)) {
		final String urlstr = config.getProperty(key);
		try {
		    final URL wsdlLocation = new URL(urlstr);
		    final SoapSession ss = new SoapSession(wsdlLocation, esbdUserName, esbdUserPassword, logger,
			    connectTimeoutMilis, requestTimeoutMilis, reCheckEsbdSesionAliveTimeoutMilis);
		    activeSessions.add(ss);
		} catch (final MalformedURLException e) {
		    logger.SEVERE.log(e, "Failed to initialize ESBD connection with url '%1$s'", urlstr);
		}
	    }

	}
    }

    @Schedule(hour = "*", minute = "*/5")
    // пытаться восстановить соединения каждые пять минут
    public void checkDeffered(final Timer timer) {
	logger.INFO.log("ACTIVE sessions");
	activeSessions.stream() //
		.map(SoapSession::toString) //
		.forEach(logger.INFO::log);
	logger.INFO.log("DISABLED sessions");
	deferredSessions.stream() //
		.map(SoapSession::toString) //
		.forEach(logger.INFO::log);

	final int count = deferredSessions.size();
	// проделать со стеком "плохих" соединений количество итераций
	// соответствующее размеру этого стека.
	// Итераций не должно быть больше, чем изначальное количество
	// содержимого стека,
	// т.к. неудачные элементы будут возвращаться в стек и возникнут
	// повторные избыточные
	// итерации над элементами
	for (int i = 0; i < count; i++) {
	    // забрать соединение из головы стека "плохих" соединений и сделать
	    // ему ping
	    final SoapSession ss = deferredSessions.removeFirst();
	    try {
		logger.INFO.log("TRYING TO ENABLE %1$s", ss);
		ss.ping();
		// если ping прошел, то поставить соединение в голову стека
		// "хороших" соединений
		activeSessions.addFirst(ss);
		logger.INFO.log("IS ALIVE %1$s", ss);
		logger.INFO.log("ENABLED %1$s", ss);
	    } catch (final ConnectionException ignored) {
		logger.INFO.log("FAIL TO ENABLE %1$s", ss);
		// если ping не прошел вернуть в стек "плохих" соединений
		deferredSessions.addLast(ss);
	    }
	}
    }

    @Override
    public Connection getConnection() throws ConnectionException {
	try {
	    while (true) {
		final SoapSession ss = activeSessions.removeFirst();
		try {
		    ss.ping();
		    activeSessions.addLast(ss);
		    final ConnectionImpl con = new ConnectionImpl(ss.getSoap(), ss.getWsdlLocation(),
			    ss.getSessionId());
		    logger.FINER.log("CONNECTION TAKEN %1$s", con);
		    
		    return con;
		} catch (final ConnectionException e) {
		    logger.WARNING.log("PING FAILED %1$s. Error message is '%2$s'", ss, e.getMessage());
		    logger.WARNING.log("DISABLED %1$s", ss);
		    ss.reset();
		    deferredSessions.add(ss);
		}
	    }
	} catch (final NoSuchElementException e) {
	    logger.SEVERE.log("No ESBD connection available due to previous errors");
	    throw new ConnectionException("No ESBD connection available");
	}
    }

    private void connectionRelease(final ConnectionImpl con) {
	logger.FINER.log("CONNECTION RELEASED %1$s", con);
    }

    private class ConnectionImpl implements Connection {
	private static final int PRIME = 17;
	private static final int MULTIPLIER = PRIME;

	private transient final IICWebServiceSoap soap;
	private final URL wsdlLocation;
	private final String aSessionID;

	ConnectionImpl(final IICWebServiceSoap soap, final URL wsdlLocation, final String aSessionID) {
	    this.soap = soap;
	    this.wsdlLocation = wsdlLocation;
	    this.aSessionID = aSessionID;
	}

	@Override
	public int calculateContractPremium(final String aXml) {
	    return soap.calculateContractPremium(aSessionID, aXml);
	}

	@Override
	public int calculatePolicyPremium(final Policy aPolicy) {
	    return soap.calculatePolicyPremium(aSessionID, aPolicy);
	}

	@Override
	public void close() throws ConnectionException {
	    connectionRelease(this);
	}

	@Override
	public void deleteNewUserRequest(final int aRequestID) {
	    soap.deleteNewUserRequest(aSessionID, aRequestID);
	}

	@Override
	public void deletePolicy(final int aPolicyID) {
	    soap.deletePolicy(aSessionID, aPolicyID);
	}

	@Override
	public boolean equals(final Object other) {
	    if (other == null || other.getClass() != getClass())
		return false;
	    if (other == this)
		return true;
	    final ConnectionImpl that = (ConnectionImpl) other;
	    return new EqualsBuilder()
		    .append(wsdlLocation, that.wsdlLocation)
		    .append(aSessionID, that.aSessionID)
		    .isEquals();
	}

	@Override
	public EsbdResponse execute(final EsbdRequest aRequest) {
	    return soap.execute(aSessionID, aRequest);
	}

	@Override
	public int getClassId(final int aClientId, final String aDate, final int aTFId) {
	    return soap.getClassId(aSessionID, aClientId, aDate, aTFId);
	}

	@Override
	public String getClassText(final int aClassId) {
	    return soap.getClassText(aSessionID, aClassId);
	}

	@Override
	public Client getClientByID(final int aID) {
	    return soap.getClientByID(aSessionID, aID);
	}

	@Override
	public ArrayOfCLIENTPBDETAILS getClientPBDetailsListByID(final int aClientID) {
	    return soap.getClientPBDetailsListByID(aSessionID, aClientID);
	}

	@Override
	public ArrayOfClient getClientsByKeyFields(final Client aClient) {
	    return soap.getClientsByKeyFields(aSessionID, aClient);
	}

	@Override
	public ArrayOfClient getClientsByRNN(final String aTPRN) {
	    return soap.getClientsByRNN(aSessionID, aTPRN);
	}

	@Override
	public ArrayOfCONTRACTAGRICULTURELIST getContractAgricultureByContractDate(
		final String aContractDate) {
	    return soap.getContractAgricultureByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTAGRICULTURELIST getContractAgricultureById(final int aCONTRACTID) {
	    return soap.getContractAgricultureById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTAGRICULTURELIST getContractAgricultureByNumber(final String aContractNumber) {
	    return soap.getContractAgricultureByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTAGRICULTURELIST getContractAgricultureByPeriod(final String aDateBeg,
		final String aDateEnd) {
	    return soap.getContractAgricultureByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfString getContractByAppRescDate(final int aInsuranceTypeID, final String aApproveDate,
		final String aRescindingDate) {
	    return soap.getContractByAppRescDate(aSessionID, aInsuranceTypeID, aApproveDate, aRescindingDate);
	}

	@Override
	public ArrayOfCONTRACTDSACCIDENT getContractDsAccidentByContractDate(final String aContractDate) {
	    return soap.getContractDsAccidentByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTDSACCIDENT getContractDsAccidentById(final int aCONTRACTID) {
	    return soap.getContractDsAccidentById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTDSACCIDENT getContractDsAccidentByNumber(final String aContractNumber) {
	    return soap.getContractDsAccidentByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTDSACCIDENT getContractDsAccidentByPeriod(final String aDateBeg,
		final String aDateEnd) {
	    return soap.getContractDsAccidentByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTDSAIR getContractDsAirByContractDate(final String aContractDate) {
	    return soap.getContractDsAirByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTDSAIR getContractDsAirById(final int aCONTRACTID) {
	    return soap.getContractDsAirById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTDSAIR getContractDsAirByNumber(final String aContractNumber) {
	    return soap.getContractDsAirByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTDSAIR getContractDsAirByPeriod(final String aDateBeg, final String aDateEnd) {
	    return soap.getContractDsAirByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTDSANNUITY getContractDsAnnuityByContractDate(final String aContractDate) {
	    return soap.getContractDsAnnuityByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTDSANNUITY getContractDsAnnuityById(final int aCONTRACTID) {
	    return soap.getContractDsAnnuityById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTDSANNUITY getContractDsAnnuityByNumber(final String aContractNumber) {
	    return soap.getContractDsAnnuityByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTDSANNUITY getContractDsAnnuityByPeriod(final String aDateBeg, final String aDateEnd) {
	    return soap.getContractDsAnnuityByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTDSAUTO getContractDsAutoByContractDate(final String aContractDate) {
	    return soap.getContractDsAutoByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTDSAUTO getContractDsAutoById(final int aCONTRACTID) {
	    return soap.getContractDsAutoById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTDSAUTO getContractDsAutoByNumber(final String aContractNumber) {
	    return soap.getContractDsAutoByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTDSAUTO getContractDsAutoByPeriod(final String aDateBeg, final String aDateEnd) {
	    return soap.getContractDsAutoByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTDSCARGO getContractDsCargoByContractDate(final String aContractDate) {
	    return soap.getContractDsCargoByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTDSCARGO getContractDsCargoById(final int aCONTRACTID) {
	    return soap.getContractDsCargoById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTDSCARGO getContractDsCargoByNumber(final String aContractNumber) {
	    return soap.getContractDsCargoByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTDSCARGO getContractDsCargoByPeriod(final String aDateBeg, final String aDateEnd) {
	    return soap.getContractDsCargoByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTDSGPOAIR getContractDsGpoAirByContractDate(final String aContractDate) {
	    return soap.getContractDsGpoAirByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTDSGPOAIR getContractDsGpoAirById(final int aCONTRACTID) {
	    return soap.getContractDsGpoAirById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTDSGPOAIR getContractDsGpoAirByNumber(final String aContractNumber) {
	    return soap.getContractDsGpoAirByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTDSGPOAIR getContractDsGpoAirByPeriod(final String aDateBeg, final String aDateEnd) {
	    return soap.getContractDsGpoAirByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTDSGPOAUTO getContractDsGpoAutoByContractDate(final String aContractDate) {
	    return soap.getContractDsGpoAutoByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTDSGPOAUTO getContractDsGpoAutoById(final int aCONTRACTID) {
	    return soap.getContractDsGpoAutoById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTDSGPOAUTO getContractDsGpoAutoByNumber(final String aContractNumber) {
	    return soap.getContractDsGpoAutoByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTDSGPOAUTO getContractDsGpoAutoByPeriod(final String aDateBeg, final String aDateEnd) {
	    return soap.getContractDsGpoAutoByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTDSGPOOTHER getContractDsGpoOtherByContractDate(final String aContractDate) {
	    return soap.getContractDsGpoOtherByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTDSGPOOTHER getContractDsGpoOtherById(final int aCONTRACTID) {
	    return soap.getContractDsGpoOtherById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTDSGPOOTHER getContractDsGpoOtherByNumber(final String aContractNumber) {
	    return soap.getContractDsGpoOtherByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTDSGPOOTHER getContractDsGpoOtherByPeriod(final String aDateBeg,
		final String aDateEnd) {
	    return soap.getContractDsGpoOtherByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTDSGPOWATER getContractDsGpoWaterByContractDate(final String aContractDate) {
	    return soap.getContractDsGpoWaterByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTDSGPOWATER getContractDsGpoWaterById(final int aCONTRACTID) {
	    return soap.getContractDsGpoWaterById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTDSGPOWATER getContractDsGpoWaterByNumber(final String aContractNumber) {
	    return soap.getContractDsGpoWaterByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTDSGPOWATER getContractDsGpoWaterByPeriod(final String aDateBeg,
		final String aDateEnd) {
	    return soap.getContractDsGpoWaterByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTDSGUARANTEE getContractDsGuaranteeByContractDate(final String aContractDate) {
	    return soap.getContractDsGuaranteeByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTDSGUARANTEE getContractDsGuaranteeById(final int aCONTRACTID) {
	    return soap.getContractDsGuaranteeById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTDSGUARANTEE getContractDsGuaranteeByNumber(final String aContractNumber) {
	    return soap.getContractDsGuaranteeByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTDSGUARANTEE getContractDsGuaranteeByPeriod(final String aDateBeg,
		final String aDateEnd) {
	    return soap.getContractDsGuaranteeByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTDSHEALTH getContractDsHealthByContractDate(final String aContractDate) {
	    return soap.getContractDsHealthByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTDSHEALTH getContractDsHealthById(final int aCONTRACTID) {
	    return soap.getContractDsHealthById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTDSHEALTH getContractDsHealthByNumber(final String aContractNumber) {
	    return soap.getContractDsHealthByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTDSHEALTH getContractDsHealthByPeriod(final String aDateBeg, final String aDateEnd) {
	    return soap.getContractDsHealthByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTDSLEGALCOSTS getContractDsLegalCostsByContractDate(final String aContractDate) {
	    return soap.getContractDsLegalCostsByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTDSLEGALCOSTS getContractDsLegalCostsById(final int aCONTRACTID) {
	    return soap.getContractDsLegalCostsById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTDSLEGALCOSTS getContractDsLegalCostsByNumber(final String aContractNumber) {
	    return soap.getContractDsLegalCostsByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTDSLEGALCOSTS getContractDsLegalCostsByPeriod(final String aDateBeg,
		final String aDateEnd) {
	    return soap.getContractDsLegalCostsByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTDSLIFE getContractDsLifeByContractDate(final String aContractDate) {
	    return soap.getContractDsLifeByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTDSLIFE getContractDsLifeById(final int aCONTRACTID) {
	    return soap.getContractDsLifeById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTDSLIFE getContractDsLifeByNumber(final String aContractNumber) {
	    return soap.getContractDsLifeByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTDSLIFE getContractDsLifeByPeriod(final String aDateBeg, final String aDateEnd) {
	    return soap.getContractDsLifeByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTDSLOAN getContractDsLoanByContractDate(final String aContractDate) {
	    return soap.getContractDsLoanByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTDSLOAN getContractDsLoanById(final int aCONTRACTID) {
	    return soap.getContractDsLoanById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTDSLOAN getContractDsLoanByNumber(final String aContractNumber) {
	    return soap.getContractDsLoanByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTDSLOAN getContractDsLoanByPeriod(final String aDateBeg, final String aDateEnd) {
	    return soap.getContractDsLoanByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTDSLOSSES getContractDsLossesByContractDate(final String aContractDate) {
	    return soap.getContractDsLossesByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTDSLOSSES getContractDsLossesById(final int aCONTRACTID) {
	    return soap.getContractDsLossesById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTDSLOSSES getContractDsLossesByNumber(final String aContractNumber) {
	    return soap.getContractDsLossesByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTDSLOSSES getContractDsLossesByPeriod(final String aDateBeg, final String aDateEnd) {
	    return soap.getContractDsLossesByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTDSMORTGAGE getContractDsMortgageByContractDate(final String aContractDate) {
	    return soap.getContractDsMortgageByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTDSMORTGAGE getContractDsMortgageById(final int aCONTRACTID) {
	    return soap.getContractDsMortgageById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTDSMORTGAGE getContractDsMortgageByNumber(final String aContractNumber) {
	    return soap.getContractDsMortgageByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTDSMORTGAGE getContractDsMortgageByPeriod(final String aDateBeg,
		final String aDateEnd) {
	    return soap.getContractDsMortgageByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTDSOTHERLOSSES getContractDsOtherLossesByContractDate(
		final String aContractDate) {
	    return soap.getContractDsOtherLossesByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTDSOTHERLOSSES getContractDsOtherLossesById(final int aCONTRACTID) {
	    return soap.getContractDsOtherLossesById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTDSOTHERLOSSES getContractDsOtherLossesByNumber(final String aContractNumber) {
	    return soap.getContractDsOtherLossesByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTDSOTHERLOSSES getContractDsOtherLossesByPeriod(final String aDateBeg,
		final String aDateEnd) {
	    return soap.getContractDsOtherLossesByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTDSPROPERTY getContractDsPropertyByContractDate(final String aContractDate) {
	    return soap.getContractDsPropertyByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTDSPROPERTY getContractDsPropertyById(final int aCONTRACTID) {
	    return soap.getContractDsPropertyById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTDSPROPERTY getContractDsPropertyByNumber(final String aContractNumber) {
	    return soap.getContractDsPropertyByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTDSPROPERTY getContractDsPropertyByPeriod(final String aDateBeg,
		final String aDateEnd) {
	    return soap.getContractDsPropertyByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTDSRAILWAYS getContractDsRailwaysByContractDate(final String aContractDate) {
	    return soap.getContractDsRailwaysByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTDSRAILWAYS getContractDsRailwaysById(final int aCONTRACTID) {
	    return soap.getContractDsRailwaysById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTDSRAILWAYS getContractDsRailwaysByNumber(final String aContractNumber) {
	    return soap.getContractDsRailwaysByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTDSRAILWAYS getContractDsRailwaysByPeriod(final String aDateBeg,
		final String aDateEnd) {
	    return soap.getContractDsRailwaysByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTDSTITLE getContractDsTitleByContractDate(final String aContractDate) {
	    return soap.getContractDsTitleByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTDSTITLE getContractDsTitleById(final int aCONTRACTID) {
	    return soap.getContractDsTitleById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTDSTITLE getContractDsTitleByNumber(final String aContractNumber) {
	    return soap.getContractDsTitleByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTDSTITLE getContractDsTitleByPeriod(final String aDateBeg, final String aDateEnd) {
	    return soap.getContractDsTitleByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTDSWATER getContractDsWaterByContractDate(final String aContractDate) {
	    return soap.getContractDsWaterByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTDSWATER getContractDsWaterById(final int aCONTRACTID) {
	    return soap.getContractDsWaterById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTDSWATER getContractDsWaterByNumber(final String aContractNumber) {
	    return soap.getContractDsWaterByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTDSWATER getContractDsWaterByPeriod(final String aDateBeg, final String aDateEnd) {
	    return soap.getContractDsWaterByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTOSECO getContractOsEcoByContractDate(final String aContractDate) {
	    return soap.getContractOsEcoByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTOSECO getContractOsEcoById(final int aCONTRACTID) {
	    return soap.getContractOsEcoById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTOSECO getContractOsEcoByNumber(final String aContractNumber) {
	    return soap.getContractOsEcoByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTOSECO getContractOsEcoByPeriod(final String aDateBeg, final String aDateEnd) {
	    return soap.getContractOsEcoByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTOSGPOAUDITORS getContractOsgpoAuditorsByContractDate(
		final String aContractDate) {
	    return soap.getContractOsgpoAuditorsByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTOSGPOAUDITORS getContractOsgpoAuditorsById(final int aCONTRACTID) {
	    return soap.getContractOsgpoAuditorsById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTOSGPOAUDITORS getContractOsgpoAuditorsByNumber(final String aContractNumber) {
	    return soap.getContractOsgpoAuditorsByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTOSGPOAUDITORS getContractOsgpoAuditorsByPeriod(final String aDateBeg,
		final String aDateEnd) {
	    return soap.getContractOsgpoAuditorsByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTOSGPODO getContractOsgpoDoByContractDate(final String aContractDate) {
	    return soap.getContractOsgpoDoByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTOSGPODO getContractOsgpoDoById(final int aCONTRACTID) {
	    return soap.getContractOsgpoDoById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTOSGPODO getContractOsgpoDoByNumber(final String aContractNumber) {
	    return soap.getContractOsgpoDoByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTOSGPODO getContractOsgpoDoByPeriod(final String aDateBeg, final String aDateEnd) {
	    return soap.getContractOsgpoDoByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTOSGPONOTARIUS getContractOsgpoNotariusByContractDate(
		final String aContractDate) {
	    return soap.getContractOsgpoNotariusByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTOSGPONOTARIUS getContractOsgpoNotariusById(final int aCONTRACTID) {
	    return soap.getContractOsgpoNotariusById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTOSGPONOTARIUS getContractOsgpoNotariusByNumber(final String aContractNumber) {
	    return soap.getContractOsgpoNotariusByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTOSGPONOTARIUS getContractOsgpoNotariusByPeriod(final String aDateBeg,
		final String aDateEnd) {
	    return soap.getContractOsgpoNotariusByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTOSGPOPASSENGERS getContractOsgpoPassengersByContractDate(
		final String aContractDate) {
	    return soap.getContractOsgpoPassengersByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTOSGPOPASSENGERS getContractOsgpoPassengersById(final int aCONTRACTID) {
	    return soap.getContractOsgpoPassengersById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTOSGPOPASSENGERS getContractOsgpoPassengersByNumber(
		final String aContractNumber) {
	    return soap.getContractOsgpoPassengersByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTOSGPOPASSENGERS getContractOsgpoPassengersByPeriod(final String aDateBeg,
		final String aDateEnd) {
	    return soap.getContractOsgpoPassengersByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTOSGPOTOUR getContractOsgpoTourByContractDate(final String aContractDate) {
	    return soap.getContractOsgpoTourByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTOSGPOTOUR getContractOsgpoTourById(final int aCONTRACTID) {
	    return soap.getContractOsgpoTourById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTOSGPOTOUR getContractOsgpoTourByNumber(final String aContractNumber) {
	    return soap.getContractOsgpoTourByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTOSGPOTOUR getContractOsgpoTourByPeriod(final String aDateBeg, final String aDateEnd) {
	    return soap.getContractOsgpoTourByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTOSRNS getContractOSRNSByContractDate(final String aContractDate) {
	    return soap.getContractOSRNSByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTOSRNS getContractOSRNSById(final int aCONTRACTID) {
	    return soap.getContractOSRNSById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTOSRNS getContractOSRNSByNumber(final String aContractNumber) {
	    return soap.getContractOSRNSByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTOSRNS getContractOSRNSByPeriod(final String aDateBeg, final String aDateEnd) {
	    return soap.getContractOSRNSByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public IECOMMON getIECOMMONById(final int aIECOMMONID) {
	    return soap.getIECOMMONById(aSessionID, aIECOMMONID);
	}

	@Override
	public ArrayOfString getIECOMMONBYPARAMS(final IECOMMON aIECOMMON) {
	    return soap.getIECOMMONBYPARAMS(aSessionID, aIECOMMON);
	}

	@Override
	public ArrayOfInsuranceEvent getInsuranceEvents(final InsuranceEvent aInsuranceEvent) {
	    return soap.getInsuranceEvents(aSessionID, aInsuranceEvent);
	}

	@Override
	public ArrayOfItem getItems(final String aTableName) {
	    return soap.getItems(aSessionID, aTableName);
	}

	@Override
	public String getLastContract(final String aContractID) {
	    return soap.getLastContract(aSessionID, aContractID);
	}

	@Override
	public Item getMarkUpFactorXML(final int idClient, final int idEmplType, final int idProfRisk) {
	    return soap.getMarkUpFactorXML(aSessionID, idClient, idEmplType, idProfRisk);
	}

	@Override
	public ArrayOfMIDDLEMAN getMiddlemenByKeyFields(final MIDDLEMAN aMiddleman) {
	    return soap.getMiddlemenByKeyFields(aSessionID, aMiddleman);
	}

	@Override
	public ArrayOfMiddlemenPayment getMiddlemenPaymentsByCreatedOrChangedDateTime(final String aDateTime1,
		final String aDateTime2) {
	    return soap.getMiddlemenPaymentsByCreatedOrChangedDateTime(aSessionID, aDateTime1, aDateTime2);
	}

	@Override
	public String getOSRNSPREMIUM(final String aXml) {
	    return soap.getOSRNSPREMIUM(aSessionID, aXml);
	}

	@Override
	public ArrayOfPolicy getPoliciesByCreatedOrChangedDateTime(final int aBranchId, final String aDateTime1,
		final String aDateTime2) {
	    return soap.getPoliciesByCreatedOrChangedDateTime(aSessionID, aBranchId, aDateTime1, aDateTime2);
	}

	@Override
	public ArrayOfPolicy getPoliciesByNumber(final String aPolicyNumber) {
	    return soap.getPoliciesByNumber(aSessionID, aPolicyNumber);
	}

	@Override
	public ArrayOfPolicy getPoliciesByPolicyDate(final String aPolicyDate1, final String aPolicyDate2) {
	    return soap.getPoliciesByPolicyDate(aSessionID, aPolicyDate1, aPolicyDate2);
	}

	@Override
	public ArrayOfItem getPoliciesInfoByReason(final String aCondition, final String aPolicyDate1,
		final String aPolicyDate2) {
	    return soap.getPoliciesInfoByReason(aSessionID, aCondition, aPolicyDate1, aPolicyDate2);
	}

	@Override
	public Policy getPolicyByGlobalID(final String aGlobalID) {
	    return soap.getPolicyByGlobalID(aSessionID, aGlobalID);
	}

	@Override
	public Policy getPolicyByID(final int aPolicyID) {
	    return soap.getPolicyByID(aSessionID, aPolicyID);
	}

	@Override
	public String getReport(final String reportName, final ArrayOfSodParameter parametrIn) {
	    return soap.getReport(aSessionID, reportName, parametrIn);
	}

	@Override
	public REQUEST getREQUESTBYID(final int aRequestID) {
	    return soap.getREQUESTBYID(aSessionID, aRequestID);
	}

	@Override
	public XMLGregorianCalendar getServerDateTime() {
	    return soap.getServerDateTime(aSessionID);
	}

	@Override
	public ArrayOfTF getTFByEngineNumber(final String aEngineNumber) {
	    return soap.getTFByEngineNumber(aSessionID, aEngineNumber);
	}

	@Override
	public ArrayOfTF getTFByKeyFields(final TF aTF) {
	    return soap.getTFByKeyFields(aSessionID, aTF);
	}

	@Override
	public ArrayOfTF getTFByNumber(final String aTFNUMBER) {
	    return soap.getTFByNumber(aSessionID, aTFNUMBER);
	}

	@Override
	public ArrayOfTF getTFByVIN(final String aVIN) {
	    return soap.getTFByVIN(aSessionID, aVIN);
	}

	@Override
	public ArrayOfTFClasses getTFClasses(final int aClientId) {
	    return soap.getTFClasses(aSessionID, aClientId);
	}

	@Override
	public ArrayOfUnionRecord getUnionRecords(final String aTableName, final String aDateFrom,
		final String aDateTo) {
	    return soap.getUnionRecords(aSessionID, aTableName, aDateFrom, aDateTo);
	}

	@Override
	public ArrayOfUserCertificate getUserCertificates() {
	    return soap.getUserCertificates(aSessionID);
	}

	@Override
	public ArrayOfUser getUsers() {
	    return soap.getUsers(aSessionID);
	}

	@Override
	public ArrayOfVictimObject getVictimObjects(final VictimObject aVictimObject) {
	    return soap.getVictimObjects(aSessionID, aVictimObject);
	}

	@Override
	public ArrayOfVOITUREMARK getVoitureMarks(final VOITUREMARK aSearchParams) {
	    return soap.getVoitureMarks(aSessionID, aSearchParams);
	}

	@Override
	public int getVoitureModelIdByName(final String aVoitureMarkName, final String aVoitureModelName) {
	    return soap.getVoitureModelIdByName(aSessionID, aVoitureMarkName, aVoitureModelName);
	}

	@Override
	public ArrayOfVOITUREMODEL getVoitureModels(final VOITUREMODEL aSearchParams) {
	    return soap.getVoitureModels(aSessionID, aSearchParams);
	}

	@Override
	public int hashCode() {
	    return new HashCodeBuilder(PRIME, MULTIPLIER)
		    .append(wsdlLocation)
		    .append(aSessionID)
		    .toHashCode();
	}

	@Override
	public boolean sessionExists(final String aUserName) {
	    return soap.sessionExists(aSessionID, aUserName);
	}

	@Override
	public Client setClient(final Client aClient) {
	    return soap.setClient(aSessionID, aClient);
	}

	@Override
	public CLIENTPBDETAILS setClientPBDetails(final CLIENTPBDETAILS aClientPBDetails) {
	    return soap.setClientPBDetails(aSessionID, aClientPBDetails);
	}

	@Override
	public CONTRACTAGRICULTURELIST setContractAgriculture(
		final CONTRACTAGRICULTURELIST aCONTRACTAGRICULTURELIST) {
	    return soap.setContractAgriculture(aSessionID, aCONTRACTAGRICULTURELIST);
	}

	@Override
	public CONTRACTDSACCIDENT setContractDsAccident(final CONTRACTDSACCIDENT aCONTRACTDSACCIDENT) {
	    return soap.setContractDsAccident(aSessionID, aCONTRACTDSACCIDENT);
	}

	@Override
	public CONTRACTDSAIR setContractDsAir(final CONTRACTDSAIR aCONTRACTDSAIR) {
	    return soap.setContractDsAir(aSessionID, aCONTRACTDSAIR);
	}

	@Override
	public CONTRACTDSANNUITY setContractDsAnnuity(final CONTRACTDSANNUITY aCONTRACTDSANNUITY) {
	    return soap.setContractDsAnnuity(aSessionID, aCONTRACTDSANNUITY);
	}

	@Override
	public CONTRACTDSAUTO setContractDsAuto(final CONTRACTDSAUTO aCONTRACTDSAUTO) {
	    return soap.setContractDsAuto(aSessionID, aCONTRACTDSAUTO);
	}

	@Override
	public CONTRACTDSCARGO setContractDsCargo(final CONTRACTDSCARGO aCONTRACTDSCARGO) {
	    return soap.setContractDsCargo(aSessionID, aCONTRACTDSCARGO);
	}

	@Override
	public CONTRACTDSGPOAIR setContractDsGpoAir(final CONTRACTDSGPOAIR aCONTRACTDSGPOAIR) {
	    return soap.setContractDsGpoAir(aSessionID, aCONTRACTDSGPOAIR);
	}

	@Override
	public CONTRACTDSGPOAUTO setContractDsGpoAuto(final CONTRACTDSGPOAUTO aCONTRACTDSGPOAUTO) {
	    return soap.setContractDsGpoAuto(aSessionID, aCONTRACTDSGPOAUTO);
	}

	@Override
	public CONTRACTDSGPOOTHER setContractDsGpoOther(final CONTRACTDSGPOOTHER aCONTRACTDSGPOOTHER) {
	    return soap.setContractDsGpoOther(aSessionID, aCONTRACTDSGPOOTHER);
	}

	@Override
	public CONTRACTDSGPOWATER setContractDsGpoWater(final CONTRACTDSGPOWATER aCONTRACTDSGPOWATER) {
	    return soap.setContractDsGpoWater(aSessionID, aCONTRACTDSGPOWATER);
	}

	@Override
	public CONTRACTDSGUARANTEE setContractDsGuarantee(final CONTRACTDSGUARANTEE aCONTRACTDSGUARANTEE) {
	    return soap.setContractDsGuarantee(aSessionID, aCONTRACTDSGUARANTEE);
	}

	@Override
	public CONTRACTDSHEALTH setContractDsHealth(final CONTRACTDSHEALTH aCONTRACTDSHEALTH) {
	    return soap.setContractDsHealth(aSessionID, aCONTRACTDSHEALTH);
	}

	@Override
	public CONTRACTDSLEGALCOSTS setContractDsLegalCosts(final CONTRACTDSLEGALCOSTS aCONTRACTDSLEGALCOSTS) {
	    return soap.setContractDsLegalCosts(aSessionID, aCONTRACTDSLEGALCOSTS);
	}

	@Override
	public CONTRACTDSLIFE setContractDsLife(final CONTRACTDSLIFE aCONTRACTDSLIFE) {
	    return soap.setContractDsLife(aSessionID, aCONTRACTDSLIFE);
	}

	@Override
	public CONTRACTDSLOAN setContractDsLoan(final CONTRACTDSLOAN aCONTRACTDSLOAN) {
	    return soap.setContractDsLoan(aSessionID, aCONTRACTDSLOAN);
	}

	@Override
	public CONTRACTDSLOSSES setContractDsLosses(final CONTRACTDSLOSSES aCONTRACTDSLOSSES) {
	    return soap.setContractDsLosses(aSessionID, aCONTRACTDSLOSSES);
	}

	@Override
	public CONTRACTDSMORTGAGE setContractDsMortgage(final CONTRACTDSMORTGAGE aCONTRACTDSMORTGAGE) {
	    return soap.setContractDsMortgage(aSessionID, aCONTRACTDSMORTGAGE);
	}

	@Override
	public CONTRACTDSOTHERLOSSES setContractDsOtherLosses(
		final CONTRACTDSOTHERLOSSES aCONTRACTDSOTHERLOSSES) {
	    return soap.setContractDsOtherLosses(aSessionID, aCONTRACTDSOTHERLOSSES);
	}

	@Override
	public CONTRACTDSPROPERTY setContractDsProperty(final CONTRACTDSPROPERTY aCONTRACTDSPROPERTY) {
	    return soap.setContractDsProperty(aSessionID, aCONTRACTDSPROPERTY);
	}

	@Override
	public CONTRACTDSRAILWAYS setContractDsRailways(final CONTRACTDSRAILWAYS aCONTRACTDSRAILWAYS) {
	    return soap.setContractDsRailways(aSessionID, aCONTRACTDSRAILWAYS);
	}

	@Override
	public CONTRACTDSTITLE setContractDsTitle(final CONTRACTDSTITLE aCONTRACTDSTITLE) {
	    return soap.setContractDsTitle(aSessionID, aCONTRACTDSTITLE);
	}

	@Override
	public CONTRACTDSWATER setContractDsWater(final CONTRACTDSWATER aCONTRACTDSWATER) {
	    return soap.setContractDsWater(aSessionID, aCONTRACTDSWATER);
	}

	@Override
	public String setContractDuplicate(final String aParamsXML) {
	    return soap.setContractDuplicate(aSessionID, aParamsXML);
	}

	@Override
	public CONTRACTOSECO setContractOsEco(final CONTRACTOSECO aCONTRACTOSECO) {
	    return soap.setContractOsEco(aSessionID, aCONTRACTOSECO);
	}

	@Override
	public CONTRACTOSGPOAUDITORS setContractOsgpoAuditors(
		final CONTRACTOSGPOAUDITORS aCONTRACTOSGPOAUDITORS) {
	    return soap.setContractOsgpoAuditors(aSessionID, aCONTRACTOSGPOAUDITORS);
	}

	@Override
	public CONTRACTOSGPODO setContractOsgpoDo(final CONTRACTOSGPODO aCONTRACTOSGPODO) {
	    return soap.setContractOsgpoDo(aSessionID, aCONTRACTOSGPODO);
	}

	@Override
	public CONTRACTOSGPONOTARIUS setContractOsgpoNotarius(
		final CONTRACTOSGPONOTARIUS aCONTRACTOSGPONOTARIUS) {
	    return soap.setContractOsgpoNotarius(aSessionID, aCONTRACTOSGPONOTARIUS);
	}

	@Override
	public CONTRACTOSGPOPASSENGERS setContractOsgpoPassengers(
		final CONTRACTOSGPOPASSENGERS aCONTRACTOSGPOPASSENGERS) {
	    return soap.setContractOsgpoPassengers(aSessionID, aCONTRACTOSGPOPASSENGERS);
	}

	@Override
	public CONTRACTOSGPOTOUR setContractOsgpoTour(final CONTRACTOSGPOTOUR aCONTRACTOSGPOTOUR) {
	    return soap.setContractOsgpoTour(aSessionID, aCONTRACTOSGPOTOUR);
	}

	@Override
	public CONTRACTOSRNS setContractOSRNS(final CONTRACTOSRNS aCONTRACTOSRNS) {
	    return soap.setContractOSRNS(aSessionID, aCONTRACTOSRNS);
	}

	@Override
	public String setContractRescinding(final int aCONTRACTID, final int aRESCINDINGREASONID,
		final String aRESCINDINGDATE) {
	    return soap.setContractRescinding(aSessionID, aCONTRACTID, aRESCINDINGREASONID, aRESCINDINGDATE);
	}

	@Override
	public IECOMMON setIECOMMON(final IECOMMON aIECOMMON) {
	    return soap.setIECOMMON(aSessionID, aIECOMMON);
	}

	@Override
	public InsuranceEvent setInsuranceEvent(final InsuranceEvent aInsuranceEvent) {
	    return soap.setInsuranceEvent(aSessionID, aInsuranceEvent);
	}

	@Override
	public void setInsuranceEventMistake(final int aInsEventID, final String aDate) {
	    soap.setInsuranceEventMistake(aSessionID, aInsEventID, aDate);
	}

	@Override
	public MIDDLEMAN setMiddleman(final MIDDLEMAN aMiddleman) {
	    return soap.setMiddleman(aSessionID, aMiddleman);
	}

	@Override
	public NewUserRequest setNewUserRequest(final NewUserRequest aNewUserRequest) {
	    return soap.setNewUserRequest(aSessionID, aNewUserRequest);
	}

	@Override
	public void setPerpetratorMistake(final int aPerpetratorID, final String aDate) {
	    soap.setPerpetratorMistake(aSessionID, aPerpetratorID, aDate);
	}

	@Override
	public Policy setPolicy(final Policy aPolicy) {
	    return soap.setPolicy(aSessionID, aPolicy);
	}

	@Override
	public Policy setPolicyDuplicate(final int aOriginalPolicyId, final String aDuplicateNumber,
		final String aDuplicateDate, final String aDescription) {
	    return soap.setPolicyDuplicate(aSessionID, aOriginalPolicyId, aDuplicateNumber, aDuplicateDate,
		    aDescription);
	}

	@Override
	public Policy setPolicyDuplicateXML(final String aParamsXML) {
	    return soap.setPolicyDuplicateXML(aSessionID, aParamsXML);
	}

	@Override
	public int setPolicyRescindingReason(final int aPolicyId, final int aRescindingReasonId,
		final String aRescindingDate) {
	    return soap.setPolicyRescindingReason(aSessionID, aPolicyId, aRescindingReasonId, aRescindingDate);
	}

	@Override
	public REQUEST setRequest(final REQUEST aREQUEST) {
	    return soap.setRequest(aSessionID, aREQUEST);
	}

	@Override
	public TF setTF(final TF aTF) {
	    return soap.setTF(aSessionID, aTF);
	}

	@Override
	public InsuranceEvent setVictimObject(final VictimObject aVictimObject) {
	    return soap.setVictimObject(aSessionID, aVictimObject);
	}

	@Override
	public VOITUREMARK setVoitureMark(final VOITUREMARK aVoitureMark) {
	    return soap.setVoitureMark(aSessionID, aVoitureMark);
	}

	@Override
	public VOITUREMODEL setVoitureModel(final VOITUREMODEL aVoitureModel) {
	    return soap.setVoitureModel(aSessionID, aVoitureModel);
	}

	@Override
	public String toString() {
	    return String.format("%1$s('%2$s')", Connection.class.getSimpleName(), wsdlLocation);
	}
    }

}
