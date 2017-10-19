package tech.lapsa.esbd.connection.beans;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Timer;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import tech.lapsa.esbd.connection.Connection;
import tech.lapsa.esbd.connection.ConnectionException;
import tech.lapsa.esbd.connection.ConnectionPool;
import tech.lapsa.esbd.jaxws.ArrayOfCLIENTPBDETAILS;
import tech.lapsa.esbd.jaxws.ArrayOfCONTRACTAGRICULTURELIST;
import tech.lapsa.esbd.jaxws.ArrayOfCONTRACTDSACCIDENT;
import tech.lapsa.esbd.jaxws.ArrayOfCONTRACTDSAIR;
import tech.lapsa.esbd.jaxws.ArrayOfCONTRACTDSANNUITY;
import tech.lapsa.esbd.jaxws.ArrayOfCONTRACTDSAUTO;
import tech.lapsa.esbd.jaxws.ArrayOfCONTRACTDSCARGO;
import tech.lapsa.esbd.jaxws.ArrayOfCONTRACTDSGPOAIR;
import tech.lapsa.esbd.jaxws.ArrayOfCONTRACTDSGPOAUTO;
import tech.lapsa.esbd.jaxws.ArrayOfCONTRACTDSGPOOTHER;
import tech.lapsa.esbd.jaxws.ArrayOfCONTRACTDSGPOWATER;
import tech.lapsa.esbd.jaxws.ArrayOfCONTRACTDSGUARANTEE;
import tech.lapsa.esbd.jaxws.ArrayOfCONTRACTDSHEALTH;
import tech.lapsa.esbd.jaxws.ArrayOfCONTRACTDSLEGALCOSTS;
import tech.lapsa.esbd.jaxws.ArrayOfCONTRACTDSLIFE;
import tech.lapsa.esbd.jaxws.ArrayOfCONTRACTDSLOAN;
import tech.lapsa.esbd.jaxws.ArrayOfCONTRACTDSLOSSES;
import tech.lapsa.esbd.jaxws.ArrayOfCONTRACTDSMORTGAGE;
import tech.lapsa.esbd.jaxws.ArrayOfCONTRACTDSOTHERLOSSES;
import tech.lapsa.esbd.jaxws.ArrayOfCONTRACTDSPROPERTY;
import tech.lapsa.esbd.jaxws.ArrayOfCONTRACTDSRAILWAYS;
import tech.lapsa.esbd.jaxws.ArrayOfCONTRACTDSTITLE;
import tech.lapsa.esbd.jaxws.ArrayOfCONTRACTDSWATER;
import tech.lapsa.esbd.jaxws.ArrayOfCONTRACTOSECO;
import tech.lapsa.esbd.jaxws.ArrayOfCONTRACTOSGPOAUDITORS;
import tech.lapsa.esbd.jaxws.ArrayOfCONTRACTOSGPODO;
import tech.lapsa.esbd.jaxws.ArrayOfCONTRACTOSGPONOTARIUS;
import tech.lapsa.esbd.jaxws.ArrayOfCONTRACTOSGPOPASSENGERS;
import tech.lapsa.esbd.jaxws.ArrayOfCONTRACTOSGPOTOUR;
import tech.lapsa.esbd.jaxws.ArrayOfCONTRACTOSRNS;
import tech.lapsa.esbd.jaxws.ArrayOfClient;
import tech.lapsa.esbd.jaxws.ArrayOfInsuranceEvent;
import tech.lapsa.esbd.jaxws.ArrayOfItem;
import tech.lapsa.esbd.jaxws.ArrayOfMIDDLEMAN;
import tech.lapsa.esbd.jaxws.ArrayOfMiddlemenPayment;
import tech.lapsa.esbd.jaxws.ArrayOfPolicy;
import tech.lapsa.esbd.jaxws.ArrayOfSodParameter;
import tech.lapsa.esbd.jaxws.ArrayOfString;
import tech.lapsa.esbd.jaxws.ArrayOfTF;
import tech.lapsa.esbd.jaxws.ArrayOfTFClasses;
import tech.lapsa.esbd.jaxws.ArrayOfUnionRecord;
import tech.lapsa.esbd.jaxws.ArrayOfUser;
import tech.lapsa.esbd.jaxws.ArrayOfUserCertificate;
import tech.lapsa.esbd.jaxws.ArrayOfVOITUREMARK;
import tech.lapsa.esbd.jaxws.ArrayOfVOITUREMODEL;
import tech.lapsa.esbd.jaxws.ArrayOfVictimObject;
import tech.lapsa.esbd.jaxws.CLIENTPBDETAILS;
import tech.lapsa.esbd.jaxws.CONTRACTAGRICULTURELIST;
import tech.lapsa.esbd.jaxws.CONTRACTDSACCIDENT;
import tech.lapsa.esbd.jaxws.CONTRACTDSAIR;
import tech.lapsa.esbd.jaxws.CONTRACTDSANNUITY;
import tech.lapsa.esbd.jaxws.CONTRACTDSAUTO;
import tech.lapsa.esbd.jaxws.CONTRACTDSCARGO;
import tech.lapsa.esbd.jaxws.CONTRACTDSGPOAIR;
import tech.lapsa.esbd.jaxws.CONTRACTDSGPOAUTO;
import tech.lapsa.esbd.jaxws.CONTRACTDSGPOOTHER;
import tech.lapsa.esbd.jaxws.CONTRACTDSGPOWATER;
import tech.lapsa.esbd.jaxws.CONTRACTDSGUARANTEE;
import tech.lapsa.esbd.jaxws.CONTRACTDSHEALTH;
import tech.lapsa.esbd.jaxws.CONTRACTDSLEGALCOSTS;
import tech.lapsa.esbd.jaxws.CONTRACTDSLIFE;
import tech.lapsa.esbd.jaxws.CONTRACTDSLOAN;
import tech.lapsa.esbd.jaxws.CONTRACTDSLOSSES;
import tech.lapsa.esbd.jaxws.CONTRACTDSMORTGAGE;
import tech.lapsa.esbd.jaxws.CONTRACTDSOTHERLOSSES;
import tech.lapsa.esbd.jaxws.CONTRACTDSPROPERTY;
import tech.lapsa.esbd.jaxws.CONTRACTDSRAILWAYS;
import tech.lapsa.esbd.jaxws.CONTRACTDSTITLE;
import tech.lapsa.esbd.jaxws.CONTRACTDSWATER;
import tech.lapsa.esbd.jaxws.CONTRACTOSECO;
import tech.lapsa.esbd.jaxws.CONTRACTOSGPOAUDITORS;
import tech.lapsa.esbd.jaxws.CONTRACTOSGPODO;
import tech.lapsa.esbd.jaxws.CONTRACTOSGPONOTARIUS;
import tech.lapsa.esbd.jaxws.CONTRACTOSGPOPASSENGERS;
import tech.lapsa.esbd.jaxws.CONTRACTOSGPOTOUR;
import tech.lapsa.esbd.jaxws.CONTRACTOSRNS;
import tech.lapsa.esbd.jaxws.Client;
import tech.lapsa.esbd.jaxws.EsbdRequest;
import tech.lapsa.esbd.jaxws.EsbdResponse;
import tech.lapsa.esbd.jaxws.IECOMMON;
import tech.lapsa.esbd.jaxws.IICWebServiceSoap;
import tech.lapsa.esbd.jaxws.InsuranceEvent;
import tech.lapsa.esbd.jaxws.Item;
import tech.lapsa.esbd.jaxws.MIDDLEMAN;
import tech.lapsa.esbd.jaxws.NewUserRequest;
import tech.lapsa.esbd.jaxws.Policy;
import tech.lapsa.esbd.jaxws.REQUEST;
import tech.lapsa.esbd.jaxws.TF;
import tech.lapsa.esbd.jaxws.VOITUREMARK;
import tech.lapsa.esbd.jaxws.VOITUREMODEL;
import tech.lapsa.esbd.jaxws.VictimObject;

@Singleton
public class ConnectionPoolBean implements ConnectionPool {

    private static final String JNDI_ESBD_POOL_CONFIGURATION_PROPERTIES = "esbd/resource/Configuration";
    private static final String PROPERTY_ESBD_USER_NAME = "esbd.user.name";
    private static final String PROPERTY_ESBD_USER_PASSWORD = "esbd.user.password";
    private static final String PROPERTY_ESBD_CONNECT_TIMEOUT_MILIS = "esbd.timeout.request.milis";
    private static final String PROPERTY_ESBD_REQUEST_TIMEOUT_MILIS = "esbd.timeout.connect.milis";
    private static final String PROPERTY_ESBD_RECHECK_TIMEOUT_MILIS = "esbd.timeout.re-check.milis";
    private static final String PROPERTY_PREFXIX_WSDL_LOCATION = "esbd.wsdl-location.";

    private String esbdUserName;
    private String esbdUserPassword;
    private int connectTimeoutMilis;
    private int requestTimeoutMilis;
    private int reCheckTimeoutMilis;

    private final Logger logger = Logger.getLogger(ConnectionPool.class.getPackage().getName());

    @Resource(mappedName = JNDI_ESBD_POOL_CONFIGURATION_PROPERTIES)
    private Properties config;

    private Deque<SoapSession> activeSessions = new ConcurrentLinkedDeque<>();
    private Deque<SoapSession> deferredSessions = new ConcurrentLinkedDeque<>();

    @PostConstruct
    public void init() {
	esbdUserName = config.getProperty(PROPERTY_ESBD_USER_NAME);
	esbdUserPassword = config.getProperty(PROPERTY_ESBD_USER_PASSWORD);
	connectTimeoutMilis = Integer.parseInt(config.getProperty(PROPERTY_ESBD_CONNECT_TIMEOUT_MILIS, "3000"));
	requestTimeoutMilis = Integer.parseInt(config.getProperty(PROPERTY_ESBD_REQUEST_TIMEOUT_MILIS, "5000"));
	reCheckTimeoutMilis = Integer.parseInt(config.getProperty(PROPERTY_ESBD_RECHECK_TIMEOUT_MILIS, "2000"));

	for (Object k : config.keySet()) {
	    String key = (String) k;
	    if (key.startsWith(PROPERTY_PREFXIX_WSDL_LOCATION)) {
		String urlstr = config.getProperty(key);
		try {
		    URL wsdlLocation = new URL(urlstr);
		    SoapSession ss = new SoapSession(wsdlLocation, esbdUserName, esbdUserPassword, logger,
			    connectTimeoutMilis, requestTimeoutMilis, reCheckTimeoutMilis);
		    activeSessions.add(ss);
		} catch (MalformedURLException e) {
		    logger.log(Level.SEVERE,
			    String.format("Failed to initialize ESBD connection with url '%1$s'", urlstr), e);
		}
	    }

	}
    }

    @Schedule(hour = "*", minute = "*", second = "*/30")
    // пытаться восстановить соединения каждую минуту
    public void checkDeffered(Timer timer) {
	int count = deferredSessions.size();
	// проделать со стеком "плохих" соединений количество итераций
	// соответствующее размеру этого стека
	for (int i = 0; i < count; i++) {
	    // забрать соединение из головы стека "плохих" соединений и сделать
	    // ему ping
	    SoapSession ss = deferredSessions.removeFirst();
	    try {
		logger.fine(String.format("TRYING TO ENABLE %1$s", ss));
		ss.ping();
		// если ping прошел, то поставить соединение в голову стека
		// "хороших" соединений
		activeSessions.addFirst(ss);
		logger.info(String.format("IS ALIVE %1$s", ss));
		logger.info(String.format("ENABLED %1$s", ss));
	    } catch (ConnectionException ignored) {
		logger.fine(String.format("FAIL TO ENABLE %1$s", ss));
		// если ping не прошел вернуть в стек "плохих" соединений
		deferredSessions.addLast(ss);
	    }
	}
    }

    @Override
    public Connection getConnection() throws ConnectionException {
	try {
	    while (true) {
		SoapSession ss = activeSessions.removeFirst();
		try {
		    ss.ping();
		    activeSessions.addLast(ss);
		    ConnectionImpl con = new ConnectionImpl(ss.getSoap(), ss.getWsdlLocation(),
			    ss.getSessionId());
		    logger.finer(String.format("CONNECTION TAKEN %1$s", con));
		    return con;
		} catch (ConnectionException e) {
		    logger.log(Level.WARNING,
			    String.format("PING FAILED %1$s. Error message is '%2$s'", ss, e.getMessage()));
		    logger.log(Level.WARNING, String.format("DISABLED %1$s", ss));
		    ss.reset();
		    deferredSessions.add(ss);
		}
	    }
	} catch (NoSuchElementException e) {
	    logger.severe("No ESBD connection available due to previous errors");
	    throw new ConnectionException("No ESBD connection available");
	}
    }

    private void connectionRelease(ConnectionImpl con) {
	logger.finer(String.format("CONNECTION RELEASED %1$s", con));
    }

    private class ConnectionImpl implements Connection {
	private static final int PRIME = 17;
	private static final int MULTIPLIER = PRIME;

	private transient final IICWebServiceSoap soap;
	private final URL wsdlLocation;
	private final String aSessionID;

	ConnectionImpl(IICWebServiceSoap soap, URL wsdlLocation, String aSessionID) {
	    this.soap = soap;
	    this.wsdlLocation = wsdlLocation;
	    this.aSessionID = aSessionID;
	}

	@Override
	public int calculateContractPremium(String aXml) {
	    return soap.calculateContractPremium(aSessionID, aXml);
	}

	@Override
	public int calculatePolicyPremium(Policy aPolicy) {
	    return soap.calculatePolicyPremium(aSessionID, aPolicy);
	}

	@Override
	public void close() throws ConnectionException {
	    connectionRelease(this);
	}

	@Override
	public void deleteNewUserRequest(int aRequestID) {
	    soap.deleteNewUserRequest(aSessionID, aRequestID);
	}

	@Override
	public void deletePolicy(int aPolicyID) {
	    soap.deletePolicy(aSessionID, aPolicyID);
	}

	@Override
	public boolean equals(Object other) {
	    if (other == null || other.getClass() != getClass())
		return false;
	    if (other == this)
		return true;
	    ConnectionImpl that = (ConnectionImpl) other;
	    return new EqualsBuilder()
		    .append(wsdlLocation, that.wsdlLocation)
		    .append(aSessionID, that.aSessionID)
		    .isEquals();
	}

	@Override
	public EsbdResponse execute(EsbdRequest aRequest) {
	    return soap.execute(aSessionID, aRequest);
	}

	@Override
	public int getClassId(int aClientId, String aDate, int aTFId) {
	    return soap.getClassId(aSessionID, aClientId, aDate, aTFId);
	}

	@Override
	public String getClassText(int aClassId) {
	    return soap.getClassText(aSessionID, aClassId);
	}

	@Override
	public Client getClientByID(int aID) {
	    return soap.getClientByID(aSessionID, aID);
	}

	@Override
	public ArrayOfCLIENTPBDETAILS getClientPBDetailsListByID(int aClientID) {
	    return soap.getClientPBDetailsListByID(aSessionID, aClientID);
	}

	@Override
	public ArrayOfClient getClientsByKeyFields(Client aClient) {
	    return soap.getClientsByKeyFields(aSessionID, aClient);
	}

	@Override
	public ArrayOfClient getClientsByRNN(String aTPRN) {
	    return soap.getClientsByRNN(aSessionID, aTPRN);
	}

	@Override
	public ArrayOfCONTRACTAGRICULTURELIST getContractAgricultureByContractDate(
		String aContractDate) {
	    return soap.getContractAgricultureByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTAGRICULTURELIST getContractAgricultureById(int aCONTRACTID) {
	    return soap.getContractAgricultureById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTAGRICULTURELIST getContractAgricultureByNumber(String aContractNumber) {
	    return soap.getContractAgricultureByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTAGRICULTURELIST getContractAgricultureByPeriod(String aDateBeg,
		String aDateEnd) {
	    return soap.getContractAgricultureByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfString getContractByAppRescDate(int aInsuranceTypeID, String aApproveDate,
		String aRescindingDate) {
	    return soap.getContractByAppRescDate(aSessionID, aInsuranceTypeID, aApproveDate, aRescindingDate);
	}

	@Override
	public ArrayOfCONTRACTDSACCIDENT getContractDsAccidentByContractDate(String aContractDate) {
	    return soap.getContractDsAccidentByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTDSACCIDENT getContractDsAccidentById(int aCONTRACTID) {
	    return soap.getContractDsAccidentById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTDSACCIDENT getContractDsAccidentByNumber(String aContractNumber) {
	    return soap.getContractDsAccidentByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTDSACCIDENT getContractDsAccidentByPeriod(String aDateBeg,
		String aDateEnd) {
	    return soap.getContractDsAccidentByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTDSAIR getContractDsAirByContractDate(String aContractDate) {
	    return soap.getContractDsAirByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTDSAIR getContractDsAirById(int aCONTRACTID) {
	    return soap.getContractDsAirById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTDSAIR getContractDsAirByNumber(String aContractNumber) {
	    return soap.getContractDsAirByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTDSAIR getContractDsAirByPeriod(String aDateBeg, String aDateEnd) {
	    return soap.getContractDsAirByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTDSANNUITY getContractDsAnnuityByContractDate(String aContractDate) {
	    return soap.getContractDsAnnuityByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTDSANNUITY getContractDsAnnuityById(int aCONTRACTID) {
	    return soap.getContractDsAnnuityById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTDSANNUITY getContractDsAnnuityByNumber(String aContractNumber) {
	    return soap.getContractDsAnnuityByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTDSANNUITY getContractDsAnnuityByPeriod(String aDateBeg, String aDateEnd) {
	    return soap.getContractDsAnnuityByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTDSAUTO getContractDsAutoByContractDate(String aContractDate) {
	    return soap.getContractDsAutoByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTDSAUTO getContractDsAutoById(int aCONTRACTID) {
	    return soap.getContractDsAutoById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTDSAUTO getContractDsAutoByNumber(String aContractNumber) {
	    return soap.getContractDsAutoByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTDSAUTO getContractDsAutoByPeriod(String aDateBeg, String aDateEnd) {
	    return soap.getContractDsAutoByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTDSCARGO getContractDsCargoByContractDate(String aContractDate) {
	    return soap.getContractDsCargoByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTDSCARGO getContractDsCargoById(int aCONTRACTID) {
	    return soap.getContractDsCargoById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTDSCARGO getContractDsCargoByNumber(String aContractNumber) {
	    return soap.getContractDsCargoByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTDSCARGO getContractDsCargoByPeriod(String aDateBeg, String aDateEnd) {
	    return soap.getContractDsCargoByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTDSGPOAIR getContractDsGpoAirByContractDate(String aContractDate) {
	    return soap.getContractDsGpoAirByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTDSGPOAIR getContractDsGpoAirById(int aCONTRACTID) {
	    return soap.getContractDsGpoAirById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTDSGPOAIR getContractDsGpoAirByNumber(String aContractNumber) {
	    return soap.getContractDsGpoAirByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTDSGPOAIR getContractDsGpoAirByPeriod(String aDateBeg, String aDateEnd) {
	    return soap.getContractDsGpoAirByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTDSGPOAUTO getContractDsGpoAutoByContractDate(String aContractDate) {
	    return soap.getContractDsGpoAutoByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTDSGPOAUTO getContractDsGpoAutoById(int aCONTRACTID) {
	    return soap.getContractDsGpoAutoById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTDSGPOAUTO getContractDsGpoAutoByNumber(String aContractNumber) {
	    return soap.getContractDsGpoAutoByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTDSGPOAUTO getContractDsGpoAutoByPeriod(String aDateBeg, String aDateEnd) {
	    return soap.getContractDsGpoAutoByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTDSGPOOTHER getContractDsGpoOtherByContractDate(String aContractDate) {
	    return soap.getContractDsGpoOtherByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTDSGPOOTHER getContractDsGpoOtherById(int aCONTRACTID) {
	    return soap.getContractDsGpoOtherById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTDSGPOOTHER getContractDsGpoOtherByNumber(String aContractNumber) {
	    return soap.getContractDsGpoOtherByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTDSGPOOTHER getContractDsGpoOtherByPeriod(String aDateBeg,
		String aDateEnd) {
	    return soap.getContractDsGpoOtherByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTDSGPOWATER getContractDsGpoWaterByContractDate(String aContractDate) {
	    return soap.getContractDsGpoWaterByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTDSGPOWATER getContractDsGpoWaterById(int aCONTRACTID) {
	    return soap.getContractDsGpoWaterById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTDSGPOWATER getContractDsGpoWaterByNumber(String aContractNumber) {
	    return soap.getContractDsGpoWaterByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTDSGPOWATER getContractDsGpoWaterByPeriod(String aDateBeg,
		String aDateEnd) {
	    return soap.getContractDsGpoWaterByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTDSGUARANTEE getContractDsGuaranteeByContractDate(String aContractDate) {
	    return soap.getContractDsGuaranteeByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTDSGUARANTEE getContractDsGuaranteeById(int aCONTRACTID) {
	    return soap.getContractDsGuaranteeById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTDSGUARANTEE getContractDsGuaranteeByNumber(String aContractNumber) {
	    return soap.getContractDsGuaranteeByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTDSGUARANTEE getContractDsGuaranteeByPeriod(String aDateBeg,
		String aDateEnd) {
	    return soap.getContractDsGuaranteeByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTDSHEALTH getContractDsHealthByContractDate(String aContractDate) {
	    return soap.getContractDsHealthByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTDSHEALTH getContractDsHealthById(int aCONTRACTID) {
	    return soap.getContractDsHealthById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTDSHEALTH getContractDsHealthByNumber(String aContractNumber) {
	    return soap.getContractDsHealthByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTDSHEALTH getContractDsHealthByPeriod(String aDateBeg, String aDateEnd) {
	    return soap.getContractDsHealthByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTDSLEGALCOSTS getContractDsLegalCostsByContractDate(String aContractDate) {
	    return soap.getContractDsLegalCostsByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTDSLEGALCOSTS getContractDsLegalCostsById(int aCONTRACTID) {
	    return soap.getContractDsLegalCostsById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTDSLEGALCOSTS getContractDsLegalCostsByNumber(String aContractNumber) {
	    return soap.getContractDsLegalCostsByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTDSLEGALCOSTS getContractDsLegalCostsByPeriod(String aDateBeg,
		String aDateEnd) {
	    return soap.getContractDsLegalCostsByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTDSLIFE getContractDsLifeByContractDate(String aContractDate) {
	    return soap.getContractDsLifeByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTDSLIFE getContractDsLifeById(int aCONTRACTID) {
	    return soap.getContractDsLifeById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTDSLIFE getContractDsLifeByNumber(String aContractNumber) {
	    return soap.getContractDsLifeByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTDSLIFE getContractDsLifeByPeriod(String aDateBeg, String aDateEnd) {
	    return soap.getContractDsLifeByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTDSLOAN getContractDsLoanByContractDate(String aContractDate) {
	    return soap.getContractDsLoanByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTDSLOAN getContractDsLoanById(int aCONTRACTID) {
	    return soap.getContractDsLoanById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTDSLOAN getContractDsLoanByNumber(String aContractNumber) {
	    return soap.getContractDsLoanByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTDSLOAN getContractDsLoanByPeriod(String aDateBeg, String aDateEnd) {
	    return soap.getContractDsLoanByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTDSLOSSES getContractDsLossesByContractDate(String aContractDate) {
	    return soap.getContractDsLossesByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTDSLOSSES getContractDsLossesById(int aCONTRACTID) {
	    return soap.getContractDsLossesById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTDSLOSSES getContractDsLossesByNumber(String aContractNumber) {
	    return soap.getContractDsLossesByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTDSLOSSES getContractDsLossesByPeriod(String aDateBeg, String aDateEnd) {
	    return soap.getContractDsLossesByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTDSMORTGAGE getContractDsMortgageByContractDate(String aContractDate) {
	    return soap.getContractDsMortgageByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTDSMORTGAGE getContractDsMortgageById(int aCONTRACTID) {
	    return soap.getContractDsMortgageById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTDSMORTGAGE getContractDsMortgageByNumber(String aContractNumber) {
	    return soap.getContractDsMortgageByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTDSMORTGAGE getContractDsMortgageByPeriod(String aDateBeg,
		String aDateEnd) {
	    return soap.getContractDsMortgageByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTDSOTHERLOSSES getContractDsOtherLossesByContractDate(
		String aContractDate) {
	    return soap.getContractDsOtherLossesByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTDSOTHERLOSSES getContractDsOtherLossesById(int aCONTRACTID) {
	    return soap.getContractDsOtherLossesById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTDSOTHERLOSSES getContractDsOtherLossesByNumber(String aContractNumber) {
	    return soap.getContractDsOtherLossesByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTDSOTHERLOSSES getContractDsOtherLossesByPeriod(String aDateBeg,
		String aDateEnd) {
	    return soap.getContractDsOtherLossesByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTDSPROPERTY getContractDsPropertyByContractDate(String aContractDate) {
	    return soap.getContractDsPropertyByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTDSPROPERTY getContractDsPropertyById(int aCONTRACTID) {
	    return soap.getContractDsPropertyById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTDSPROPERTY getContractDsPropertyByNumber(String aContractNumber) {
	    return soap.getContractDsPropertyByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTDSPROPERTY getContractDsPropertyByPeriod(String aDateBeg,
		String aDateEnd) {
	    return soap.getContractDsPropertyByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTDSRAILWAYS getContractDsRailwaysByContractDate(String aContractDate) {
	    return soap.getContractDsRailwaysByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTDSRAILWAYS getContractDsRailwaysById(int aCONTRACTID) {
	    return soap.getContractDsRailwaysById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTDSRAILWAYS getContractDsRailwaysByNumber(String aContractNumber) {
	    return soap.getContractDsRailwaysByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTDSRAILWAYS getContractDsRailwaysByPeriod(String aDateBeg,
		String aDateEnd) {
	    return soap.getContractDsRailwaysByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTDSTITLE getContractDsTitleByContractDate(String aContractDate) {
	    return soap.getContractDsTitleByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTDSTITLE getContractDsTitleById(int aCONTRACTID) {
	    return soap.getContractDsTitleById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTDSTITLE getContractDsTitleByNumber(String aContractNumber) {
	    return soap.getContractDsTitleByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTDSTITLE getContractDsTitleByPeriod(String aDateBeg, String aDateEnd) {
	    return soap.getContractDsTitleByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTDSWATER getContractDsWaterByContractDate(String aContractDate) {
	    return soap.getContractDsWaterByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTDSWATER getContractDsWaterById(int aCONTRACTID) {
	    return soap.getContractDsWaterById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTDSWATER getContractDsWaterByNumber(String aContractNumber) {
	    return soap.getContractDsWaterByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTDSWATER getContractDsWaterByPeriod(String aDateBeg, String aDateEnd) {
	    return soap.getContractDsWaterByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTOSECO getContractOsEcoByContractDate(String aContractDate) {
	    return soap.getContractOsEcoByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTOSECO getContractOsEcoById(int aCONTRACTID) {
	    return soap.getContractOsEcoById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTOSECO getContractOsEcoByNumber(String aContractNumber) {
	    return soap.getContractOsEcoByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTOSECO getContractOsEcoByPeriod(String aDateBeg, String aDateEnd) {
	    return soap.getContractOsEcoByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTOSGPOAUDITORS getContractOsgpoAuditorsByContractDate(
		String aContractDate) {
	    return soap.getContractOsgpoAuditorsByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTOSGPOAUDITORS getContractOsgpoAuditorsById(int aCONTRACTID) {
	    return soap.getContractOsgpoAuditorsById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTOSGPOAUDITORS getContractOsgpoAuditorsByNumber(String aContractNumber) {
	    return soap.getContractOsgpoAuditorsByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTOSGPOAUDITORS getContractOsgpoAuditorsByPeriod(String aDateBeg,
		String aDateEnd) {
	    return soap.getContractOsgpoAuditorsByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTOSGPODO getContractOsgpoDoByContractDate(String aContractDate) {
	    return soap.getContractOsgpoDoByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTOSGPODO getContractOsgpoDoById(int aCONTRACTID) {
	    return soap.getContractOsgpoDoById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTOSGPODO getContractOsgpoDoByNumber(String aContractNumber) {
	    return soap.getContractOsgpoDoByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTOSGPODO getContractOsgpoDoByPeriod(String aDateBeg, String aDateEnd) {
	    return soap.getContractOsgpoDoByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTOSGPONOTARIUS getContractOsgpoNotariusByContractDate(
		String aContractDate) {
	    return soap.getContractOsgpoNotariusByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTOSGPONOTARIUS getContractOsgpoNotariusById(int aCONTRACTID) {
	    return soap.getContractOsgpoNotariusById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTOSGPONOTARIUS getContractOsgpoNotariusByNumber(String aContractNumber) {
	    return soap.getContractOsgpoNotariusByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTOSGPONOTARIUS getContractOsgpoNotariusByPeriod(String aDateBeg,
		String aDateEnd) {
	    return soap.getContractOsgpoNotariusByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTOSGPOPASSENGERS getContractOsgpoPassengersByContractDate(
		String aContractDate) {
	    return soap.getContractOsgpoPassengersByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTOSGPOPASSENGERS getContractOsgpoPassengersById(int aCONTRACTID) {
	    return soap.getContractOsgpoPassengersById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTOSGPOPASSENGERS getContractOsgpoPassengersByNumber(
		String aContractNumber) {
	    return soap.getContractOsgpoPassengersByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTOSGPOPASSENGERS getContractOsgpoPassengersByPeriod(String aDateBeg,
		String aDateEnd) {
	    return soap.getContractOsgpoPassengersByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTOSGPOTOUR getContractOsgpoTourByContractDate(String aContractDate) {
	    return soap.getContractOsgpoTourByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTOSGPOTOUR getContractOsgpoTourById(int aCONTRACTID) {
	    return soap.getContractOsgpoTourById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTOSGPOTOUR getContractOsgpoTourByNumber(String aContractNumber) {
	    return soap.getContractOsgpoTourByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTOSGPOTOUR getContractOsgpoTourByPeriod(String aDateBeg, String aDateEnd) {
	    return soap.getContractOsgpoTourByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public ArrayOfCONTRACTOSRNS getContractOSRNSByContractDate(String aContractDate) {
	    return soap.getContractOSRNSByContractDate(aSessionID, aContractDate);
	}

	@Override
	public CONTRACTOSRNS getContractOSRNSById(int aCONTRACTID) {
	    return soap.getContractOSRNSById(aSessionID, aCONTRACTID);
	}

	@Override
	public ArrayOfCONTRACTOSRNS getContractOSRNSByNumber(String aContractNumber) {
	    return soap.getContractOSRNSByNumber(aSessionID, aContractNumber);
	}

	@Override
	public ArrayOfCONTRACTOSRNS getContractOSRNSByPeriod(String aDateBeg, String aDateEnd) {
	    return soap.getContractOSRNSByPeriod(aSessionID, aDateBeg, aDateEnd);
	}

	@Override
	public IECOMMON getIECOMMONById(int aIECOMMONID) {
	    return soap.getIECOMMONById(aSessionID, aIECOMMONID);
	}

	@Override
	public ArrayOfString getIECOMMONBYPARAMS(IECOMMON aIECOMMON) {
	    return soap.getIECOMMONBYPARAMS(aSessionID, aIECOMMON);
	}

	@Override
	public ArrayOfInsuranceEvent getInsuranceEvents(InsuranceEvent aInsuranceEvent) {
	    return soap.getInsuranceEvents(aSessionID, aInsuranceEvent);
	}

	@Override
	public ArrayOfItem getItems(String aTableName) {
	    return soap.getItems(aSessionID, aTableName);
	}

	@Override
	public String getLastContract(String aContractID) {
	    return soap.getLastContract(aSessionID, aContractID);
	}

	@Override
	public Item getMarkUpFactorXML(int idClient, int idEmplType, int idProfRisk) {
	    return soap.getMarkUpFactorXML(aSessionID, idClient, idEmplType, idProfRisk);
	}

	@Override
	public ArrayOfMIDDLEMAN getMiddlemenByKeyFields(MIDDLEMAN aMiddleman) {
	    return soap.getMiddlemenByKeyFields(aSessionID, aMiddleman);
	}

	@Override
	public ArrayOfMiddlemenPayment getMiddlemenPaymentsByCreatedOrChangedDateTime(String aDateTime1,
		String aDateTime2) {
	    return soap.getMiddlemenPaymentsByCreatedOrChangedDateTime(aSessionID, aDateTime1, aDateTime2);
	}

	@Override
	public String getOSRNSPREMIUM(String aXml) {
	    return soap.getOSRNSPREMIUM(aSessionID, aXml);
	}

	@Override
	public ArrayOfPolicy getPoliciesByCreatedOrChangedDateTime(int aBranchId, String aDateTime1,
		String aDateTime2) {
	    return soap.getPoliciesByCreatedOrChangedDateTime(aSessionID, aBranchId, aDateTime1, aDateTime2);
	}

	@Override
	public ArrayOfPolicy getPoliciesByNumber(String aPolicyNumber) {
	    return soap.getPoliciesByNumber(aSessionID, aPolicyNumber);
	}

	@Override
	public ArrayOfPolicy getPoliciesByPolicyDate(String aPolicyDate1, String aPolicyDate2) {
	    return soap.getPoliciesByPolicyDate(aSessionID, aPolicyDate1, aPolicyDate2);
	}

	@Override
	public ArrayOfItem getPoliciesInfoByReason(String aCondition, String aPolicyDate1,
		String aPolicyDate2) {
	    return soap.getPoliciesInfoByReason(aSessionID, aCondition, aPolicyDate1, aPolicyDate2);
	}

	@Override
	public Policy getPolicyByGlobalID(String aGlobalID) {
	    return soap.getPolicyByGlobalID(aSessionID, aGlobalID);
	}

	@Override
	public Policy getPolicyByID(int aPolicyID) {
	    return soap.getPolicyByID(aSessionID, aPolicyID);
	}

	@Override
	public String getReport(String reportName, ArrayOfSodParameter parametrIn) {
	    return soap.getReport(aSessionID, reportName, parametrIn);
	}

	@Override
	public REQUEST getREQUESTBYID(int aRequestID) {
	    return soap.getREQUESTBYID(aSessionID, aRequestID);
	}

	@Override
	public XMLGregorianCalendar getServerDateTime() {
	    return soap.getServerDateTime(aSessionID);
	}

	@Override
	public ArrayOfTF getTFByEngineNumber(String aEngineNumber) {
	    return soap.getTFByEngineNumber(aSessionID, aEngineNumber);
	}

	@Override
	public ArrayOfTF getTFByKeyFields(TF aTF) {
	    return soap.getTFByKeyFields(aSessionID, aTF);
	}

	@Override
	public ArrayOfTF getTFByNumber(String aTFNUMBER) {
	    return soap.getTFByNumber(aSessionID, aTFNUMBER);
	}

	@Override
	public ArrayOfTF getTFByVIN(String aVIN) {
	    return soap.getTFByVIN(aSessionID, aVIN);
	}

	@Override
	public ArrayOfTFClasses getTFClasses(int aClientId) {
	    return soap.getTFClasses(aSessionID, aClientId);
	}

	@Override
	public ArrayOfUnionRecord getUnionRecords(String aTableName, String aDateFrom, String aDateTo) {
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
	public ArrayOfVictimObject getVictimObjects(VictimObject aVictimObject) {
	    return soap.getVictimObjects(aSessionID, aVictimObject);
	}

	@Override
	public ArrayOfVOITUREMARK getVoitureMarks(VOITUREMARK aSearchParams) {
	    return soap.getVoitureMarks(aSessionID, aSearchParams);
	}

	@Override
	public int getVoitureModelIdByName(String aVoitureMarkName, String aVoitureModelName) {
	    return soap.getVoitureModelIdByName(aSessionID, aVoitureMarkName, aVoitureModelName);
	}

	@Override
	public ArrayOfVOITUREMODEL getVoitureModels(VOITUREMODEL aSearchParams) {
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
	public boolean sessionExists(String aUserName) {
	    return soap.sessionExists(aSessionID, aUserName);
	}

	@Override
	public Client setClient(Client aClient) {
	    return soap.setClient(aSessionID, aClient);
	}

	@Override
	public CLIENTPBDETAILS setClientPBDetails(CLIENTPBDETAILS aClientPBDetails) {
	    return soap.setClientPBDetails(aSessionID, aClientPBDetails);
	}

	@Override
	public CONTRACTAGRICULTURELIST setContractAgriculture(
		CONTRACTAGRICULTURELIST aCONTRACTAGRICULTURELIST) {
	    return soap.setContractAgriculture(aSessionID, aCONTRACTAGRICULTURELIST);
	}

	@Override
	public CONTRACTDSACCIDENT setContractDsAccident(CONTRACTDSACCIDENT aCONTRACTDSACCIDENT) {
	    return soap.setContractDsAccident(aSessionID, aCONTRACTDSACCIDENT);
	}

	@Override
	public CONTRACTDSAIR setContractDsAir(CONTRACTDSAIR aCONTRACTDSAIR) {
	    return soap.setContractDsAir(aSessionID, aCONTRACTDSAIR);
	}

	@Override
	public CONTRACTDSANNUITY setContractDsAnnuity(CONTRACTDSANNUITY aCONTRACTDSANNUITY) {
	    return soap.setContractDsAnnuity(aSessionID, aCONTRACTDSANNUITY);
	}

	@Override
	public CONTRACTDSAUTO setContractDsAuto(CONTRACTDSAUTO aCONTRACTDSAUTO) {
	    return soap.setContractDsAuto(aSessionID, aCONTRACTDSAUTO);
	}

	@Override
	public CONTRACTDSCARGO setContractDsCargo(CONTRACTDSCARGO aCONTRACTDSCARGO) {
	    return soap.setContractDsCargo(aSessionID, aCONTRACTDSCARGO);
	}

	@Override
	public CONTRACTDSGPOAIR setContractDsGpoAir(CONTRACTDSGPOAIR aCONTRACTDSGPOAIR) {
	    return soap.setContractDsGpoAir(aSessionID, aCONTRACTDSGPOAIR);
	}

	@Override
	public CONTRACTDSGPOAUTO setContractDsGpoAuto(CONTRACTDSGPOAUTO aCONTRACTDSGPOAUTO) {
	    return soap.setContractDsGpoAuto(aSessionID, aCONTRACTDSGPOAUTO);
	}

	@Override
	public CONTRACTDSGPOOTHER setContractDsGpoOther(CONTRACTDSGPOOTHER aCONTRACTDSGPOOTHER) {
	    return soap.setContractDsGpoOther(aSessionID, aCONTRACTDSGPOOTHER);
	}

	@Override
	public CONTRACTDSGPOWATER setContractDsGpoWater(CONTRACTDSGPOWATER aCONTRACTDSGPOWATER) {
	    return soap.setContractDsGpoWater(aSessionID, aCONTRACTDSGPOWATER);
	}

	@Override
	public CONTRACTDSGUARANTEE setContractDsGuarantee(CONTRACTDSGUARANTEE aCONTRACTDSGUARANTEE) {
	    return soap.setContractDsGuarantee(aSessionID, aCONTRACTDSGUARANTEE);
	}

	@Override
	public CONTRACTDSHEALTH setContractDsHealth(CONTRACTDSHEALTH aCONTRACTDSHEALTH) {
	    return soap.setContractDsHealth(aSessionID, aCONTRACTDSHEALTH);
	}

	@Override
	public CONTRACTDSLEGALCOSTS setContractDsLegalCosts(CONTRACTDSLEGALCOSTS aCONTRACTDSLEGALCOSTS) {
	    return soap.setContractDsLegalCosts(aSessionID, aCONTRACTDSLEGALCOSTS);
	}

	@Override
	public CONTRACTDSLIFE setContractDsLife(CONTRACTDSLIFE aCONTRACTDSLIFE) {
	    return soap.setContractDsLife(aSessionID, aCONTRACTDSLIFE);
	}

	@Override
	public CONTRACTDSLOAN setContractDsLoan(CONTRACTDSLOAN aCONTRACTDSLOAN) {
	    return soap.setContractDsLoan(aSessionID, aCONTRACTDSLOAN);
	}

	@Override
	public CONTRACTDSLOSSES setContractDsLosses(CONTRACTDSLOSSES aCONTRACTDSLOSSES) {
	    return soap.setContractDsLosses(aSessionID, aCONTRACTDSLOSSES);
	}

	@Override
	public CONTRACTDSMORTGAGE setContractDsMortgage(CONTRACTDSMORTGAGE aCONTRACTDSMORTGAGE) {
	    return soap.setContractDsMortgage(aSessionID, aCONTRACTDSMORTGAGE);
	}

	@Override
	public CONTRACTDSOTHERLOSSES setContractDsOtherLosses(
		CONTRACTDSOTHERLOSSES aCONTRACTDSOTHERLOSSES) {
	    return soap.setContractDsOtherLosses(aSessionID, aCONTRACTDSOTHERLOSSES);
	}

	@Override
	public CONTRACTDSPROPERTY setContractDsProperty(CONTRACTDSPROPERTY aCONTRACTDSPROPERTY) {
	    return soap.setContractDsProperty(aSessionID, aCONTRACTDSPROPERTY);
	}

	@Override
	public CONTRACTDSRAILWAYS setContractDsRailways(CONTRACTDSRAILWAYS aCONTRACTDSRAILWAYS) {
	    return soap.setContractDsRailways(aSessionID, aCONTRACTDSRAILWAYS);
	}

	@Override
	public CONTRACTDSTITLE setContractDsTitle(CONTRACTDSTITLE aCONTRACTDSTITLE) {
	    return soap.setContractDsTitle(aSessionID, aCONTRACTDSTITLE);
	}

	@Override
	public CONTRACTDSWATER setContractDsWater(CONTRACTDSWATER aCONTRACTDSWATER) {
	    return soap.setContractDsWater(aSessionID, aCONTRACTDSWATER);
	}

	@Override
	public String setContractDuplicate(String aParamsXML) {
	    return soap.setContractDuplicate(aSessionID, aParamsXML);
	}

	@Override
	public CONTRACTOSECO setContractOsEco(CONTRACTOSECO aCONTRACTOSECO) {
	    return soap.setContractOsEco(aSessionID, aCONTRACTOSECO);
	}

	@Override
	public CONTRACTOSGPOAUDITORS setContractOsgpoAuditors(
		CONTRACTOSGPOAUDITORS aCONTRACTOSGPOAUDITORS) {
	    return soap.setContractOsgpoAuditors(aSessionID, aCONTRACTOSGPOAUDITORS);
	}

	@Override
	public CONTRACTOSGPODO setContractOsgpoDo(CONTRACTOSGPODO aCONTRACTOSGPODO) {
	    return soap.setContractOsgpoDo(aSessionID, aCONTRACTOSGPODO);
	}

	@Override
	public CONTRACTOSGPONOTARIUS setContractOsgpoNotarius(
		CONTRACTOSGPONOTARIUS aCONTRACTOSGPONOTARIUS) {
	    return soap.setContractOsgpoNotarius(aSessionID, aCONTRACTOSGPONOTARIUS);
	}

	@Override
	public CONTRACTOSGPOPASSENGERS setContractOsgpoPassengers(
		CONTRACTOSGPOPASSENGERS aCONTRACTOSGPOPASSENGERS) {
	    return soap.setContractOsgpoPassengers(aSessionID, aCONTRACTOSGPOPASSENGERS);
	}

	@Override
	public CONTRACTOSGPOTOUR setContractOsgpoTour(CONTRACTOSGPOTOUR aCONTRACTOSGPOTOUR) {
	    return soap.setContractOsgpoTour(aSessionID, aCONTRACTOSGPOTOUR);
	}

	@Override
	public CONTRACTOSRNS setContractOSRNS(CONTRACTOSRNS aCONTRACTOSRNS) {
	    return soap.setContractOSRNS(aSessionID, aCONTRACTOSRNS);
	}

	@Override
	public String setContractRescinding(int aCONTRACTID, int aRESCINDINGREASONID,
		String aRESCINDINGDATE) {
	    return soap.setContractRescinding(aSessionID, aCONTRACTID, aRESCINDINGREASONID, aRESCINDINGDATE);
	}

	@Override
	public IECOMMON setIECOMMON(IECOMMON aIECOMMON) {
	    return soap.setIECOMMON(aSessionID, aIECOMMON);
	}

	@Override
	public InsuranceEvent setInsuranceEvent(InsuranceEvent aInsuranceEvent) {
	    return soap.setInsuranceEvent(aSessionID, aInsuranceEvent);
	}

	@Override
	public void setInsuranceEventMistake(int aInsEventID, String aDate) {
	    soap.setInsuranceEventMistake(aSessionID, aInsEventID, aDate);
	}

	@Override
	public MIDDLEMAN setMiddleman(MIDDLEMAN aMiddleman) {
	    return soap.setMiddleman(aSessionID, aMiddleman);
	}

	@Override
	public NewUserRequest setNewUserRequest(NewUserRequest aNewUserRequest) {
	    return soap.setNewUserRequest(aSessionID, aNewUserRequest);
	}

	@Override
	public void setPerpetratorMistake(int aPerpetratorID, String aDate) {
	    soap.setPerpetratorMistake(aSessionID, aPerpetratorID, aDate);
	}

	@Override
	public Policy setPolicy(Policy aPolicy) {
	    return soap.setPolicy(aSessionID, aPolicy);
	}

	@Override
	public Policy setPolicyDuplicate(int aOriginalPolicyId, String aDuplicateNumber,
		String aDuplicateDate, String aDescription) {
	    return soap.setPolicyDuplicate(aSessionID, aOriginalPolicyId, aDuplicateNumber, aDuplicateDate,
		    aDescription);
	}

	@Override
	public Policy setPolicyDuplicateXML(String aParamsXML) {
	    return soap.setPolicyDuplicateXML(aSessionID, aParamsXML);
	}

	@Override
	public int setPolicyRescindingReason(int aPolicyId, int aRescindingReasonId,
		String aRescindingDate) {
	    return soap.setPolicyRescindingReason(aSessionID, aPolicyId, aRescindingReasonId, aRescindingDate);
	}

	@Override
	public REQUEST setRequest(REQUEST aREQUEST) {
	    return soap.setRequest(aSessionID, aREQUEST);
	}

	@Override
	public TF setTF(TF aTF) {
	    return soap.setTF(aSessionID, aTF);
	}

	@Override
	public InsuranceEvent setVictimObject(VictimObject aVictimObject) {
	    return soap.setVictimObject(aSessionID, aVictimObject);
	}

	@Override
	public VOITUREMARK setVoitureMark(VOITUREMARK aVoitureMark) {
	    return soap.setVoitureMark(aSessionID, aVoitureMark);
	}

	@Override
	public VOITUREMODEL setVoitureModel(VOITUREMODEL aVoitureModel) {
	    return soap.setVoitureModel(aSessionID, aVoitureModel);
	}

	@Override
	public String toString() {
	    return String.format("%1$s('%2$s')", Connection.class.getSimpleName(), wsdlLocation);
	}
    }

}
