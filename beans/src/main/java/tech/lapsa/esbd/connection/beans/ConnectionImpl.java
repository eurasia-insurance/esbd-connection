package tech.lapsa.esbd.connection.beans;

import javax.xml.datatype.XMLGregorianCalendar;

import tech.lapsa.esbd.connection.Connection;
import tech.lapsa.esbd.connection.ConnectionException;
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

final class ConnectionImpl implements Connection {

    private final MyLogger logger = MyLogger.newBuilder() //
	    .withNameOf(Connection.class) //
	    .build();
    private final SoapSession session;

    ConnectionImpl(final SoapSession session) {
	this.session = session;
	this.logger.TRACE.log("CONNECTION TAKEN %1$s", session);
    }

    @Override
    public void close() throws ConnectionException {
	logger.TRACE.log("CONNECTION RELEASED %1$s", session);
    }

    @Override
    public int calculateContractPremium(final String aXml) {
	return session.callInt((soap, aSessionId) -> soap.calculateContractPremium(aSessionId, aXml));
    }

    @Override
    public int calculatePolicyPremium(final Policy aPolicy) {
	return session.callInt((soap, aSessionId) -> soap.calculatePolicyPremium(aSessionId, aPolicy));
    }

    @Override
    public void deleteNewUserRequest(final int aRequestID) {
	session.process((soap, aSessionId) -> soap.deleteNewUserRequest(aSessionId, aRequestID));
    }

    @Override
    public void deletePolicy(final int aPolicyID) {
	session.process((soap, aSessionId) -> soap.deletePolicy(aSessionId, aPolicyID));
    }

    @Override
    public EsbdResponse execute(final EsbdRequest aRequest) {
	return session.call((soap, aSessionId) -> soap.execute(aSessionId, aRequest));
    }

    @Override
    public int getClassId(final int aClientId, final String aDate, final int aTFId) {
	return session.callInt((soap, aSessionId) -> soap.getClassId(aSessionId, aClientId, aDate, aTFId));
    }

    @Override
    public String getClassText(final int aClassId) {
	return session.call((soap, aSessionId) -> soap.getClassText(aSessionId, aClassId));
    }

    @Override
    public Client getClientByID(final int aID) {
	return session.call((soap, aSessionId) -> soap.getClientByID(aSessionId, aID));
    }

    @Override
    public ArrayOfCLIENTPBDETAILS getClientPBDetailsListByID(final int aClientID) {
	return session.call((soap, aSessionId) -> soap.getClientPBDetailsListByID(aSessionId, aClientID));
    }

    @Override
    public ArrayOfClient getClientsByKeyFields(final Client aClient) {
	return session.call((soap, aSessionId) -> soap.getClientsByKeyFields(aSessionId, aClient));
    }

    @Override
    public ArrayOfClient getClientsByRNN(final String aTPRN) {
	return session.call((soap, aSessionId) -> soap.getClientsByRNN(aSessionId, aTPRN));
    }

    @Override
    public ArrayOfCONTRACTAGRICULTURELIST getContractAgricultureByContractDate(
	    final String aContractDate) {
	return session.call((soap, aSessionId) -> soap.getContractAgricultureByContractDate(aSessionId, aContractDate));
    }

    @Override
    public CONTRACTAGRICULTURELIST getContractAgricultureById(final int aCONTRACTID) {
	return session.call((soap, aSessionId) -> soap.getContractAgricultureById(aSessionId, aCONTRACTID));
    }

    @Override
    public ArrayOfCONTRACTAGRICULTURELIST getContractAgricultureByNumber(final String aContractNumber) {
	return session.call((soap, aSessionId) -> soap.getContractAgricultureByNumber(aSessionId, aContractNumber));
    }

    @Override
    public ArrayOfCONTRACTAGRICULTURELIST getContractAgricultureByPeriod(final String aDateBeg,
	    final String aDateEnd) {
	return session.call((soap, aSessionId) -> soap.getContractAgricultureByPeriod(aSessionId, aDateBeg, aDateEnd));
    }

    @Override
    public ArrayOfString getContractByAppRescDate(final int aInsuranceTypeID, final String aApproveDate,
	    final String aRescindingDate) {
	return session.call((soap, aSessionId) -> soap.getContractByAppRescDate(aSessionId, aInsuranceTypeID,
		aApproveDate, aRescindingDate));
    }

    @Override
    public ArrayOfCONTRACTDSACCIDENT getContractDsAccidentByContractDate(final String aContractDate) {
	return session.call((soap, aSessionId) -> soap.getContractDsAccidentByContractDate(aSessionId, aContractDate));
    }

    @Override
    public CONTRACTDSACCIDENT getContractDsAccidentById(final int aCONTRACTID) {
	return session.call((soap, aSessionId) -> soap.getContractDsAccidentById(aSessionId, aCONTRACTID));
    }

    @Override
    public ArrayOfCONTRACTDSACCIDENT getContractDsAccidentByNumber(final String aContractNumber) {
	return session.call((soap, aSessionId) -> soap.getContractDsAccidentByNumber(aSessionId, aContractNumber));
    }

    @Override
    public ArrayOfCONTRACTDSACCIDENT getContractDsAccidentByPeriod(final String aDateBeg,
	    final String aDateEnd) {
	return session.call((soap, aSessionId) -> soap.getContractDsAccidentByPeriod(aSessionId, aDateBeg, aDateEnd));
    }

    @Override
    public ArrayOfCONTRACTDSAIR getContractDsAirByContractDate(final String aContractDate) {
	return session.call((soap, aSessionId) -> soap.getContractDsAirByContractDate(aSessionId, aContractDate));
    }

    @Override
    public CONTRACTDSAIR getContractDsAirById(final int aCONTRACTID) {
	return session.call((soap, aSessionId) -> soap.getContractDsAirById(aSessionId, aCONTRACTID));
    }

    @Override
    public ArrayOfCONTRACTDSAIR getContractDsAirByNumber(final String aContractNumber) {
	return session.call((soap, aSessionId) -> soap.getContractDsAirByNumber(aSessionId, aContractNumber));
    }

    @Override
    public ArrayOfCONTRACTDSAIR getContractDsAirByPeriod(final String aDateBeg, final String aDateEnd) {
	return session.call((soap, aSessionId) -> soap.getContractDsAirByPeriod(aSessionId, aDateBeg, aDateEnd));
    }

    @Override
    public ArrayOfCONTRACTDSANNUITY getContractDsAnnuityByContractDate(final String aContractDate) {
	return session.call((soap, aSessionId) -> soap.getContractDsAnnuityByContractDate(aSessionId, aContractDate));
    }

    @Override
    public CONTRACTDSANNUITY getContractDsAnnuityById(final int aCONTRACTID) {
	return session.call((soap, aSessionId) -> soap.getContractDsAnnuityById(aSessionId, aCONTRACTID));
    }

    @Override
    public ArrayOfCONTRACTDSANNUITY getContractDsAnnuityByNumber(final String aContractNumber) {
	return session.call((soap, aSessionId) -> soap.getContractDsAnnuityByNumber(aSessionId, aContractNumber));
    }

    @Override
    public ArrayOfCONTRACTDSANNUITY getContractDsAnnuityByPeriod(final String aDateBeg, final String aDateEnd) {
	return session.call((soap, aSessionId) -> soap.getContractDsAnnuityByPeriod(aSessionId, aDateBeg, aDateEnd));
    }

    @Override
    public ArrayOfCONTRACTDSAUTO getContractDsAutoByContractDate(final String aContractDate) {
	return session.call((soap, aSessionId) -> soap.getContractDsAutoByContractDate(aSessionId, aContractDate));
    }

    @Override
    public CONTRACTDSAUTO getContractDsAutoById(final int aCONTRACTID) {
	return session.call((soap, aSessionId) -> soap.getContractDsAutoById(aSessionId, aCONTRACTID));
    }

    @Override
    public ArrayOfCONTRACTDSAUTO getContractDsAutoByNumber(final String aContractNumber) {
	return session.call((soap, aSessionId) -> soap.getContractDsAutoByNumber(aSessionId, aContractNumber));
    }

    @Override
    public ArrayOfCONTRACTDSAUTO getContractDsAutoByPeriod(final String aDateBeg, final String aDateEnd) {
	return session.call((soap, aSessionId) -> soap.getContractDsAutoByPeriod(aSessionId, aDateBeg, aDateEnd));
    }

    @Override
    public ArrayOfCONTRACTDSCARGO getContractDsCargoByContractDate(final String aContractDate) {
	return session.call((soap, aSessionId) -> soap.getContractDsCargoByContractDate(aSessionId, aContractDate));
    }

    @Override
    public CONTRACTDSCARGO getContractDsCargoById(final int aCONTRACTID) {
	return session.call((soap, aSessionId) -> soap.getContractDsCargoById(aSessionId, aCONTRACTID));
    }

    @Override
    public ArrayOfCONTRACTDSCARGO getContractDsCargoByNumber(final String aContractNumber) {
	return session.call((soap, aSessionId) -> soap.getContractDsCargoByNumber(aSessionId, aContractNumber));
    }

    @Override
    public ArrayOfCONTRACTDSCARGO getContractDsCargoByPeriod(final String aDateBeg, final String aDateEnd) {
	return session.call((soap, aSessionId) -> soap.getContractDsCargoByPeriod(aSessionId, aDateBeg, aDateEnd));
    }

    @Override
    public ArrayOfCONTRACTDSGPOAIR getContractDsGpoAirByContractDate(final String aContractDate) {
	return session.call((soap, aSessionId) -> soap.getContractDsGpoAirByContractDate(aSessionId, aContractDate));
    }

    @Override
    public CONTRACTDSGPOAIR getContractDsGpoAirById(final int aCONTRACTID) {
	return session.call((soap, aSessionId) -> soap.getContractDsGpoAirById(aSessionId, aCONTRACTID));
    }

    @Override
    public ArrayOfCONTRACTDSGPOAIR getContractDsGpoAirByNumber(final String aContractNumber) {
	return session.call((soap, aSessionId) -> soap.getContractDsGpoAirByNumber(aSessionId, aContractNumber));
    }

    @Override
    public ArrayOfCONTRACTDSGPOAIR getContractDsGpoAirByPeriod(final String aDateBeg, final String aDateEnd) {
	return session.call((soap, aSessionId) -> soap.getContractDsGpoAirByPeriod(aSessionId, aDateBeg, aDateEnd));
    }

    @Override
    public ArrayOfCONTRACTDSGPOAUTO getContractDsGpoAutoByContractDate(final String aContractDate) {
	return session.call((soap, aSessionId) -> soap.getContractDsGpoAutoByContractDate(aSessionId, aContractDate));
    }

    @Override
    public CONTRACTDSGPOAUTO getContractDsGpoAutoById(final int aCONTRACTID) {
	return session.call((soap, aSessionId) -> soap.getContractDsGpoAutoById(aSessionId, aCONTRACTID));
    }

    @Override
    public ArrayOfCONTRACTDSGPOAUTO getContractDsGpoAutoByNumber(final String aContractNumber) {
	return session.call((soap, aSessionId) -> soap.getContractDsGpoAutoByNumber(aSessionId, aContractNumber));
    }

    @Override
    public ArrayOfCONTRACTDSGPOAUTO getContractDsGpoAutoByPeriod(final String aDateBeg, final String aDateEnd) {
	return session.call((soap, aSessionId) -> soap.getContractDsGpoAutoByPeriod(aSessionId, aDateBeg, aDateEnd));
    }

    @Override
    public ArrayOfCONTRACTDSGPOOTHER getContractDsGpoOtherByContractDate(final String aContractDate) {
	return session.call((soap, aSessionId) -> soap.getContractDsGpoOtherByContractDate(aSessionId, aContractDate));
    }

    @Override
    public CONTRACTDSGPOOTHER getContractDsGpoOtherById(final int aCONTRACTID) {
	return session.call((soap, aSessionId) -> soap.getContractDsGpoOtherById(aSessionId, aCONTRACTID));
    }

    @Override
    public ArrayOfCONTRACTDSGPOOTHER getContractDsGpoOtherByNumber(final String aContractNumber) {
	return session.call((soap, aSessionId) -> soap.getContractDsGpoOtherByNumber(aSessionId, aContractNumber));
    }

    @Override
    public ArrayOfCONTRACTDSGPOOTHER getContractDsGpoOtherByPeriod(final String aDateBeg, final String aDateEnd) {
	return session.call((soap, aSessionId) -> soap.getContractDsGpoOtherByPeriod(aSessionId, aDateBeg, aDateEnd));
    }

    @Override
    public ArrayOfCONTRACTDSGPOWATER getContractDsGpoWaterByContractDate(final String aContractDate) {
	return session.call((soap, aSessionId) -> soap.getContractDsGpoWaterByContractDate(aSessionId, aContractDate));
    }

    @Override
    public CONTRACTDSGPOWATER getContractDsGpoWaterById(final int aCONTRACTID) {
	return session.call((soap, aSessionId) -> soap.getContractDsGpoWaterById(aSessionId, aCONTRACTID));
    }

    @Override
    public ArrayOfCONTRACTDSGPOWATER getContractDsGpoWaterByNumber(final String aContractNumber) {
	return session.call((soap, aSessionId) -> soap.getContractDsGpoWaterByNumber(aSessionId, aContractNumber));
    }

    @Override
    public ArrayOfCONTRACTDSGPOWATER getContractDsGpoWaterByPeriod(final String aDateBeg, final String aDateEnd) {
	return session.call((soap, aSessionId) -> soap.getContractDsGpoWaterByPeriod(aSessionId, aDateBeg, aDateEnd));
    }

    @Override
    public ArrayOfCONTRACTDSGUARANTEE getContractDsGuaranteeByContractDate(final String aContractDate) {
	return session.call((soap, aSessionId) -> soap.getContractDsGuaranteeByContractDate(aSessionId, aContractDate));
    }

    @Override
    public CONTRACTDSGUARANTEE getContractDsGuaranteeById(final int aCONTRACTID) {
	return session.call((soap, aSessionId) -> soap.getContractDsGuaranteeById(aSessionId, aCONTRACTID));
    }

    @Override
    public ArrayOfCONTRACTDSGUARANTEE getContractDsGuaranteeByNumber(final String aContractNumber) {
	return session.call((soap, aSessionId) -> soap.getContractDsGuaranteeByNumber(aSessionId, aContractNumber));
    }

    @Override
    public ArrayOfCONTRACTDSGUARANTEE getContractDsGuaranteeByPeriod(final String aDateBeg, final String aDateEnd) {
	return session.call((soap, aSessionId) -> soap.getContractDsGuaranteeByPeriod(aSessionId, aDateBeg, aDateEnd));
    }

    @Override
    public ArrayOfCONTRACTDSHEALTH getContractDsHealthByContractDate(final String aContractDate) {
	return session.call((soap, aSessionId) -> soap.getContractDsHealthByContractDate(aSessionId, aContractDate));
    }

    @Override
    public CONTRACTDSHEALTH getContractDsHealthById(final int aCONTRACTID) {
	return session.call((soap, aSessionId) -> soap.getContractDsHealthById(aSessionId, aCONTRACTID));
    }

    @Override
    public ArrayOfCONTRACTDSHEALTH getContractDsHealthByNumber(final String aContractNumber) {
	return session.call((soap, aSessionId) -> soap.getContractDsHealthByNumber(aSessionId, aContractNumber));
    }

    @Override
    public ArrayOfCONTRACTDSHEALTH getContractDsHealthByPeriod(final String aDateBeg, final String aDateEnd) {
	return session.call((soap, aSessionId) -> soap.getContractDsHealthByPeriod(aSessionId, aDateBeg, aDateEnd));
    }

    @Override
    public ArrayOfCONTRACTDSLEGALCOSTS getContractDsLegalCostsByContractDate(final String aContractDate) {
	return session
		.call((soap, aSessionId) -> soap.getContractDsLegalCostsByContractDate(aSessionId, aContractDate));
    }

    @Override
    public CONTRACTDSLEGALCOSTS getContractDsLegalCostsById(final int aCONTRACTID) {
	return session.call((soap, aSessionId) -> soap.getContractDsLegalCostsById(aSessionId, aCONTRACTID));
    }

    @Override
    public ArrayOfCONTRACTDSLEGALCOSTS getContractDsLegalCostsByNumber(final String aContractNumber) {
	return session.call((soap, aSessionId) -> soap.getContractDsLegalCostsByNumber(aSessionId, aContractNumber));
    }

    @Override
    public ArrayOfCONTRACTDSLEGALCOSTS getContractDsLegalCostsByPeriod(final String aDateBeg, final String aDateEnd) {
	return session.call((soap, aSessionId) -> soap.getContractDsLegalCostsByPeriod(aSessionId, aDateBeg, aDateEnd));
    }

    @Override
    public ArrayOfCONTRACTDSLIFE getContractDsLifeByContractDate(final String aContractDate) {
	return session.call((soap, aSessionId) -> soap.getContractDsLifeByContractDate(aSessionId, aContractDate));
    }

    @Override
    public CONTRACTDSLIFE getContractDsLifeById(final int aCONTRACTID) {
	return session.call((soap, aSessionId) -> soap.getContractDsLifeById(aSessionId, aCONTRACTID));
    }

    @Override
    public ArrayOfCONTRACTDSLIFE getContractDsLifeByNumber(final String aContractNumber) {
	return session.call((soap, aSessionId) -> soap.getContractDsLifeByNumber(aSessionId, aContractNumber));
    }

    @Override
    public ArrayOfCONTRACTDSLIFE getContractDsLifeByPeriod(final String aDateBeg, final String aDateEnd) {
	return session.call((soap, aSessionId) -> soap.getContractDsLifeByPeriod(aSessionId, aDateBeg, aDateEnd));
    }

    @Override
    public ArrayOfCONTRACTDSLOAN getContractDsLoanByContractDate(final String aContractDate) {
	return session.call((soap, aSessionId) -> soap.getContractDsLoanByContractDate(aSessionId, aContractDate));
    }

    @Override
    public CONTRACTDSLOAN getContractDsLoanById(final int aCONTRACTID) {
	return session.call((soap, aSessionId) -> soap.getContractDsLoanById(aSessionId, aCONTRACTID));
    }

    @Override
    public ArrayOfCONTRACTDSLOAN getContractDsLoanByNumber(final String aContractNumber) {
	return session.call((soap, aSessionId) -> soap.getContractDsLoanByNumber(aSessionId, aContractNumber));
    }

    @Override
    public ArrayOfCONTRACTDSLOAN getContractDsLoanByPeriod(final String aDateBeg, final String aDateEnd) {
	return session.call((soap, aSessionId) -> soap.getContractDsLoanByPeriod(aSessionId, aDateBeg, aDateEnd));
    }

    @Override
    public ArrayOfCONTRACTDSLOSSES getContractDsLossesByContractDate(final String aContractDate) {
	return session.call((soap, aSessionId) -> soap.getContractDsLossesByContractDate(aSessionId, aContractDate));
    }

    @Override
    public CONTRACTDSLOSSES getContractDsLossesById(final int aCONTRACTID) {
	return session.call((soap, aSessionId) -> soap.getContractDsLossesById(aSessionId, aCONTRACTID));
    }

    @Override
    public ArrayOfCONTRACTDSLOSSES getContractDsLossesByNumber(final String aContractNumber) {
	return session.call((soap, aSessionId) -> soap.getContractDsLossesByNumber(aSessionId, aContractNumber));
    }

    @Override
    public ArrayOfCONTRACTDSLOSSES getContractDsLossesByPeriod(final String aDateBeg, final String aDateEnd) {
	return session.call((soap, aSessionId) -> soap.getContractDsLossesByPeriod(aSessionId, aDateBeg, aDateEnd));
    }

    @Override
    public ArrayOfCONTRACTDSMORTGAGE getContractDsMortgageByContractDate(final String aContractDate) {
	return session.call((soap, aSessionId) -> soap.getContractDsMortgageByContractDate(aSessionId, aContractDate));
    }

    @Override
    public CONTRACTDSMORTGAGE getContractDsMortgageById(final int aCONTRACTID) {
	return session.call((soap, aSessionId) -> soap.getContractDsMortgageById(aSessionId, aCONTRACTID));
    }

    @Override
    public ArrayOfCONTRACTDSMORTGAGE getContractDsMortgageByNumber(final String aContractNumber) {
	return session.call((soap, aSessionId) -> soap.getContractDsMortgageByNumber(aSessionId, aContractNumber));
    }

    @Override
    public ArrayOfCONTRACTDSMORTGAGE getContractDsMortgageByPeriod(final String aDateBeg, final String aDateEnd) {
	return session.call((soap, aSessionId) -> soap.getContractDsMortgageByPeriod(aSessionId, aDateBeg, aDateEnd));
    }

    @Override
    public ArrayOfCONTRACTDSOTHERLOSSES getContractDsOtherLossesByContractDate(final String aContractDate) {
	return session
		.call((soap, aSessionId) -> soap.getContractDsOtherLossesByContractDate(aSessionId, aContractDate));
    }

    @Override
    public CONTRACTDSOTHERLOSSES getContractDsOtherLossesById(final int aCONTRACTID) {
	return session.call((soap, aSessionId) -> soap.getContractDsOtherLossesById(aSessionId, aCONTRACTID));
    }

    @Override
    public ArrayOfCONTRACTDSOTHERLOSSES getContractDsOtherLossesByNumber(final String aContractNumber) {
	return session.call((soap, aSessionId) -> soap.getContractDsOtherLossesByNumber(aSessionId, aContractNumber));
    }

    @Override
    public ArrayOfCONTRACTDSOTHERLOSSES getContractDsOtherLossesByPeriod(final String aDateBeg,
	    final String aDateEnd) {
	return session
		.call((soap, aSessionId) -> soap.getContractDsOtherLossesByPeriod(aSessionId, aDateBeg, aDateEnd));
    }

    @Override
    public ArrayOfCONTRACTDSPROPERTY getContractDsPropertyByContractDate(final String aContractDate) {
	return session.call((soap, aSessionId) -> soap.getContractDsPropertyByContractDate(aSessionId, aContractDate));
    }

    @Override
    public CONTRACTDSPROPERTY getContractDsPropertyById(final int aCONTRACTID) {
	return session.call((soap, aSessionId) -> soap.getContractDsPropertyById(aSessionId, aCONTRACTID));
    }

    @Override
    public ArrayOfCONTRACTDSPROPERTY getContractDsPropertyByNumber(final String aContractNumber) {
	return session.call((soap, aSessionId) -> soap.getContractDsPropertyByNumber(aSessionId, aContractNumber));
    }

    @Override
    public ArrayOfCONTRACTDSPROPERTY getContractDsPropertyByPeriod(final String aDateBeg, final String aDateEnd) {
	return session.call((soap, aSessionId) -> soap.getContractDsPropertyByPeriod(aSessionId, aDateBeg, aDateEnd));
    }

    @Override
    public ArrayOfCONTRACTDSRAILWAYS getContractDsRailwaysByContractDate(final String aContractDate) {
	return session.call((soap, aSessionId) -> soap.getContractDsRailwaysByContractDate(aSessionId, aContractDate));
    }

    @Override
    public CONTRACTDSRAILWAYS getContractDsRailwaysById(final int aCONTRACTID) {
	return session.call((soap, aSessionId) -> soap.getContractDsRailwaysById(aSessionId, aCONTRACTID));
    }

    @Override
    public ArrayOfCONTRACTDSRAILWAYS getContractDsRailwaysByNumber(final String aContractNumber) {
	return session.call((soap, aSessionId) -> soap.getContractDsRailwaysByNumber(aSessionId, aContractNumber));
    }

    @Override
    public ArrayOfCONTRACTDSRAILWAYS getContractDsRailwaysByPeriod(final String aDateBeg, final String aDateEnd) {
	return session.call((soap, aSessionId) -> soap.getContractDsRailwaysByPeriod(aSessionId, aDateBeg, aDateEnd));
    }

    @Override
    public ArrayOfCONTRACTDSTITLE getContractDsTitleByContractDate(final String aContractDate) {
	return session.call((soap, aSessionId) -> soap.getContractDsTitleByContractDate(aSessionId, aContractDate));
    }

    @Override
    public CONTRACTDSTITLE getContractDsTitleById(final int aCONTRACTID) {
	return session.call((soap, aSessionId) -> soap.getContractDsTitleById(aSessionId, aCONTRACTID));
    }

    @Override
    public ArrayOfCONTRACTDSTITLE getContractDsTitleByNumber(final String aContractNumber) {
	return session.call((soap, aSessionId) -> soap.getContractDsTitleByNumber(aSessionId, aContractNumber));
    }

    @Override
    public ArrayOfCONTRACTDSTITLE getContractDsTitleByPeriod(final String aDateBeg, final String aDateEnd) {
	return session.call((soap, aSessionId) -> soap.getContractDsTitleByPeriod(aSessionId, aDateBeg, aDateEnd));
    }

    @Override
    public ArrayOfCONTRACTDSWATER getContractDsWaterByContractDate(final String aContractDate) {
	return session.call((soap, aSessionId) -> soap.getContractDsWaterByContractDate(aSessionId, aContractDate));
    }

    @Override
    public CONTRACTDSWATER getContractDsWaterById(final int aCONTRACTID) {
	return session.call((soap, aSessionId) -> soap.getContractDsWaterById(aSessionId, aCONTRACTID));
    }

    @Override
    public ArrayOfCONTRACTDSWATER getContractDsWaterByNumber(final String aContractNumber) {
	return session.call((soap, aSessionId) -> soap.getContractDsWaterByNumber(aSessionId, aContractNumber));
    }

    @Override
    public ArrayOfCONTRACTDSWATER getContractDsWaterByPeriod(final String aDateBeg, final String aDateEnd) {
	return session.call((soap, aSessionId) -> soap.getContractDsWaterByPeriod(aSessionId, aDateBeg, aDateEnd));
    }

    @Override
    public ArrayOfCONTRACTOSECO getContractOsEcoByContractDate(final String aContractDate) {
	return session.call((soap, aSessionId) -> soap.getContractOsEcoByContractDate(aSessionId, aContractDate));
    }

    @Override
    public CONTRACTOSECO getContractOsEcoById(final int aCONTRACTID) {
	return session.call((soap, aSessionId) -> soap.getContractOsEcoById(aSessionId, aCONTRACTID));
    }

    @Override
    public ArrayOfCONTRACTOSECO getContractOsEcoByNumber(final String aContractNumber) {
	return session.call((soap, aSessionId) -> soap.getContractOsEcoByNumber(aSessionId, aContractNumber));
    }

    @Override
    public ArrayOfCONTRACTOSECO getContractOsEcoByPeriod(final String aDateBeg, final String aDateEnd) {
	return session.call((soap, aSessionId) -> soap.getContractOsEcoByPeriod(aSessionId, aDateBeg, aDateEnd));
    }

    @Override
    public ArrayOfCONTRACTOSGPOAUDITORS getContractOsgpoAuditorsByContractDate(final String aContractDate) {
	return session
		.call((soap, aSessionId) -> soap.getContractOsgpoAuditorsByContractDate(aSessionId, aContractDate));
    }

    @Override
    public CONTRACTOSGPOAUDITORS getContractOsgpoAuditorsById(final int aCONTRACTID) {
	return session.call((soap, aSessionId) -> soap.getContractOsgpoAuditorsById(aSessionId, aCONTRACTID));
    }

    @Override
    public ArrayOfCONTRACTOSGPOAUDITORS getContractOsgpoAuditorsByNumber(final String aContractNumber) {
	return session.call((soap, aSessionId) -> soap.getContractOsgpoAuditorsByNumber(aSessionId, aContractNumber));
    }

    @Override
    public ArrayOfCONTRACTOSGPOAUDITORS getContractOsgpoAuditorsByPeriod(final String aDateBeg, final String aDateEnd) {
	return session
		.call((soap, aSessionId) -> soap.getContractOsgpoAuditorsByPeriod(aSessionId, aDateBeg, aDateEnd));
    }

    @Override
    public ArrayOfCONTRACTOSGPODO getContractOsgpoDoByContractDate(final String aContractDate) {
	return session.call((soap, aSessionId) -> soap.getContractOsgpoDoByContractDate(aSessionId, aContractDate));
    }

    @Override
    public CONTRACTOSGPODO getContractOsgpoDoById(final int aCONTRACTID) {
	return session.call((soap, aSessionId) -> soap.getContractOsgpoDoById(aSessionId, aCONTRACTID));
    }

    @Override
    public ArrayOfCONTRACTOSGPODO getContractOsgpoDoByNumber(final String aContractNumber) {
	return session.call((soap, aSessionId) -> soap.getContractOsgpoDoByNumber(aSessionId, aContractNumber));
    }

    @Override
    public ArrayOfCONTRACTOSGPODO getContractOsgpoDoByPeriod(final String aDateBeg, final String aDateEnd) {
	return session.call((soap, aSessionId) -> soap.getContractOsgpoDoByPeriod(aSessionId, aDateBeg, aDateEnd));
    }

    @Override
    public ArrayOfCONTRACTOSGPONOTARIUS getContractOsgpoNotariusByContractDate(final String aContractDate) {
	return session
		.call((soap, aSessionId) -> soap.getContractOsgpoNotariusByContractDate(aSessionId, aContractDate));
    }

    @Override
    public CONTRACTOSGPONOTARIUS getContractOsgpoNotariusById(final int aCONTRACTID) {
	return session.call((soap, aSessionId) -> soap.getContractOsgpoNotariusById(aSessionId, aCONTRACTID));
    }

    @Override
    public ArrayOfCONTRACTOSGPONOTARIUS getContractOsgpoNotariusByNumber(final String aContractNumber) {
	return session.call((soap, aSessionId) -> soap.getContractOsgpoNotariusByNumber(aSessionId, aContractNumber));
    }

    @Override
    public ArrayOfCONTRACTOSGPONOTARIUS getContractOsgpoNotariusByPeriod(final String aDateBeg, final String aDateEnd) {
	return session
		.call((soap, aSessionId) -> soap.getContractOsgpoNotariusByPeriod(aSessionId, aDateBeg, aDateEnd));
    }

    @Override
    public ArrayOfCONTRACTOSGPOPASSENGERS getContractOsgpoPassengersByContractDate(final String aContractDate) {
	return session
		.call((soap, aSessionId) -> soap.getContractOsgpoPassengersByContractDate(aSessionId, aContractDate));
    }

    @Override
    public CONTRACTOSGPOPASSENGERS getContractOsgpoPassengersById(final int aCONTRACTID) {
	return session.call((soap, aSessionId) -> soap.getContractOsgpoPassengersById(aSessionId, aCONTRACTID));
    }

    @Override
    public ArrayOfCONTRACTOSGPOPASSENGERS getContractOsgpoPassengersByNumber(final String aContractNumber) {
	return session.call((soap, aSessionId) -> soap.getContractOsgpoPassengersByNumber(aSessionId, aContractNumber));
    }

    @Override
    public ArrayOfCONTRACTOSGPOPASSENGERS getContractOsgpoPassengersByPeriod(final String aDateBeg,
	    final String aDateEnd) {
	return session
		.call((soap, aSessionId) -> soap.getContractOsgpoPassengersByPeriod(aSessionId, aDateBeg, aDateEnd));
    }

    @Override
    public ArrayOfCONTRACTOSGPOTOUR getContractOsgpoTourByContractDate(final String aContractDate) {
	return session.call((soap, aSessionId) -> soap.getContractOsgpoTourByContractDate(aSessionId, aContractDate));
    }

    @Override
    public CONTRACTOSGPOTOUR getContractOsgpoTourById(final int aCONTRACTID) {
	return session.call((soap, aSessionId) -> soap.getContractOsgpoTourById(aSessionId, aCONTRACTID));
    }

    @Override
    public ArrayOfCONTRACTOSGPOTOUR getContractOsgpoTourByNumber(final String aContractNumber) {
	return session.call((soap, aSessionId) -> soap.getContractOsgpoTourByNumber(aSessionId, aContractNumber));
    }

    @Override
    public ArrayOfCONTRACTOSGPOTOUR getContractOsgpoTourByPeriod(final String aDateBeg, final String aDateEnd) {
	return session.call((soap, aSessionId) -> soap.getContractOsgpoTourByPeriod(aSessionId, aDateBeg, aDateEnd));
    }

    @Override
    public ArrayOfCONTRACTOSRNS getContractOSRNSByContractDate(final String aContractDate) {
	return session.call((soap, aSessionId) -> soap.getContractOSRNSByContractDate(aSessionId, aContractDate));
    }

    @Override
    public CONTRACTOSRNS getContractOSRNSById(final int aCONTRACTID) {
	return session.call((soap, aSessionId) -> soap.getContractOSRNSById(aSessionId, aCONTRACTID));
    }

    @Override
    public ArrayOfCONTRACTOSRNS getContractOSRNSByNumber(final String aContractNumber) {
	return session.call((soap, aSessionId) -> soap.getContractOSRNSByNumber(aSessionId, aContractNumber));
    }

    @Override
    public ArrayOfCONTRACTOSRNS getContractOSRNSByPeriod(final String aDateBeg, final String aDateEnd) {
	return session.call((soap, aSessionId) -> soap.getContractOSRNSByPeriod(aSessionId, aDateBeg, aDateEnd));
    }

    @Override
    public IECOMMON getIECOMMONById(final int aIECOMMONID) {
	return session.call((soap, aSessionId) -> soap.getIECOMMONById(aSessionId, aIECOMMONID));
    }

    @Override
    public ArrayOfString getIECOMMONBYPARAMS(final IECOMMON aIECOMMON) {
	return session.call((soap, aSessionId) -> soap.getIECOMMONBYPARAMS(aSessionId, aIECOMMON));
    }

    @Override
    public ArrayOfInsuranceEvent getInsuranceEvents(final InsuranceEvent aInsuranceEvent) {
	return session.call((soap, aSessionId) -> soap.getInsuranceEvents(aSessionId, aInsuranceEvent));
    }

    @Override
    public ArrayOfItem getItems(final String aTableName) {
	return session.call((soap, aSessionId) -> soap.getItems(aSessionId, aTableName));
    }

    @Override
    public String getLastContract(final String aContractID) {
	return session.call((soap, aSessionId) -> soap.getLastContract(aSessionId, aContractID));
    }

    @Override
    public Item getMarkUpFactorXML(final int idClient, final int idEmplType, final int idProfRisk) {
	return session
		.call((soap, aSessionId) -> soap.getMarkUpFactorXML(aSessionId, idClient, idEmplType, idProfRisk));
    }

    @Override
    public ArrayOfMIDDLEMAN getMiddlemenByKeyFields(final MIDDLEMAN aMiddleman) {
	return session.call((soap, aSessionId) -> soap.getMiddlemenByKeyFields(aSessionId, aMiddleman));
    }

    @Override
    public ArrayOfMiddlemenPayment getMiddlemenPaymentsByCreatedOrChangedDateTime(final String aDateTime1,
	    final String aDateTime2) {
	return session.call((soap, aSessionId) -> soap.getMiddlemenPaymentsByCreatedOrChangedDateTime(aSessionId,
		aDateTime1, aDateTime2));
    }

    @Override
    public String getOSRNSPREMIUM(final String aXml) {
	return session.call((soap, aSessionId) -> soap.getOSRNSPREMIUM(aSessionId, aXml));
    }

    @Override
    public ArrayOfPolicy getPoliciesByCreatedOrChangedDateTime(final int aBranchId, final String aDateTime1,
	    final String aDateTime2) {
	return session.call((soap, aSessionId) -> soap.getPoliciesByCreatedOrChangedDateTime(aSessionId, aBranchId,
		aDateTime1, aDateTime2));
    }

    @Override
    public ArrayOfPolicy getPoliciesByNumber(final String aPolicyNumber) {
	return session.call((soap, aSessionId) -> soap.getPoliciesByNumber(aSessionId, aPolicyNumber));
    }

    @Override
    public ArrayOfPolicy getPoliciesByPolicyDate(final String aPolicyDate1, final String aPolicyDate2) {
	return session.call((soap, aSessionId) -> soap.getPoliciesByPolicyDate(aSessionId, aPolicyDate1, aPolicyDate2));
    }

    @Override
    public ArrayOfItem getPoliciesInfoByReason(final String aCondition, final String aPolicyDate1,
	    final String aPolicyDate2) {
	return session.call(
		(soap, aSessionId) -> soap.getPoliciesInfoByReason(aSessionId, aCondition, aPolicyDate1, aPolicyDate2));
    }

    @Override
    public Policy getPolicyByGlobalID(final String aGlobalID) {
	return session.call((soap, aSessionId) -> soap.getPolicyByGlobalID(aSessionId, aGlobalID));
    }

    @Override
    public Policy getPolicyByID(final int aPolicyID) {
	return session.call((soap, aSessionId) -> soap.getPolicyByID(aSessionId, aPolicyID));
    }

    @Override
    public String getReport(final String reportName, final ArrayOfSodParameter parametrIn) {
	return session.call((soap, aSessionId) -> soap.getReport(aSessionId, reportName, parametrIn));
    }

    @Override
    public REQUEST getREQUESTBYID(final int aRequestID) {
	return session.call((soap, aSessionId) -> soap.getREQUESTBYID(aSessionId, aRequestID));
    }

    @Override
    public XMLGregorianCalendar getServerDateTime() {
	return session.call((soap, aSessionId) -> soap.getServerDateTime(aSessionId));
    }

    @Override
    public ArrayOfTF getTFByEngineNumber(final String aEngineNumber) {
	return session.call((soap, aSessionId) -> soap.getTFByEngineNumber(aSessionId, aEngineNumber));
    }

    @Override
    public ArrayOfTF getTFByKeyFields(final TF aTF) {
	return session.call((soap, aSessionId) -> soap.getTFByKeyFields(aSessionId, aTF));
    }

    @Override
    public ArrayOfTF getTFByNumber(final String aTFNUMBER) {
	return session.call((soap, aSessionId) -> soap.getTFByNumber(aSessionId, aTFNUMBER));
    }

    @Override
    public ArrayOfTF getTFByVIN(final String aVIN) {
	return session.call((soap, aSessionId) -> soap.getTFByVIN(aSessionId, aVIN));
    }

    @Override
    public ArrayOfTFClasses getTFClasses(final int aClientId) {
	return session.call((soap, aSessionId) -> soap.getTFClasses(aSessionId, aClientId));
    }

    @Override
    public ArrayOfUnionRecord getUnionRecords(final String aTableName, final String aDateFrom,
	    final String aDateTo) {
	return session.call((soap, aSessionId) -> soap.getUnionRecords(aSessionId, aTableName, aDateFrom, aDateTo));
    }

    @Override
    public ArrayOfUserCertificate getUserCertificates() {
	return session.call((soap, aSessionId) -> soap.getUserCertificates(aSessionId));
    }

    @Override
    public ArrayOfUser getUsers() {
	return session.call((soap, aSessionId) -> soap.getUsers(aSessionId));
    }

    @Override
    public ArrayOfVictimObject getVictimObjects(final VictimObject aVictimObject) {
	return session.call((soap, aSessionId) -> soap.getVictimObjects(aSessionId, aVictimObject));
    }

    @Override
    public ArrayOfVOITUREMARK getVoitureMarks(final VOITUREMARK aSearchParams) {
	return session.call((soap, aSessionId) -> soap.getVoitureMarks(aSessionId, aSearchParams));
    }

    @Override
    public int getVoitureModelIdByName(final String aVoitureMarkName, final String aVoitureModelName) {
	return session.callInt(
		(soap, aSessionId) -> soap.getVoitureModelIdByName(aSessionId, aVoitureMarkName, aVoitureModelName));
    }

    @Override
    public ArrayOfVOITUREMODEL getVoitureModels(final VOITUREMODEL aSearchParams) {
	return session.call((soap, aSessionId) -> soap.getVoitureModels(aSessionId, aSearchParams));
    }

    @Override
    public boolean sessionExists(final String aUserName) {
	return session.call((soap, aSessionId) -> soap.sessionExists(aSessionId, aUserName));
    }

    @Override
    public Client setClient(final Client aClient) {
	return session.call((soap, aSessionId) -> soap.setClient(aSessionId, aClient));
    }

    @Override
    public CLIENTPBDETAILS setClientPBDetails(final CLIENTPBDETAILS aClientPBDetails) {
	return session.call((soap, aSessionId) -> soap.setClientPBDetails(aSessionId, aClientPBDetails));
    }

    @Override
    public CONTRACTAGRICULTURELIST setContractAgriculture(final CONTRACTAGRICULTURELIST aCONTRACTAGRICULTURELIST) {
	return session.call((soap, aSessionId) -> soap.setContractAgriculture(aSessionId, aCONTRACTAGRICULTURELIST));
    }

    @Override
    public CONTRACTDSACCIDENT setContractDsAccident(final CONTRACTDSACCIDENT aCONTRACTDSACCIDENT) {
	return session.call((soap, aSessionId) -> soap.setContractDsAccident(aSessionId, aCONTRACTDSACCIDENT));
    }

    @Override
    public CONTRACTDSAIR setContractDsAir(final CONTRACTDSAIR aCONTRACTDSAIR) {
	return session.call((soap, aSessionId) -> soap.setContractDsAir(aSessionId, aCONTRACTDSAIR));
    }

    @Override
    public CONTRACTDSANNUITY setContractDsAnnuity(final CONTRACTDSANNUITY aCONTRACTDSANNUITY) {
	return session.call((soap, aSessionId) -> soap.setContractDsAnnuity(aSessionId, aCONTRACTDSANNUITY));
    }

    @Override
    public CONTRACTDSAUTO setContractDsAuto(final CONTRACTDSAUTO aCONTRACTDSAUTO) {
	return session.call((soap, aSessionId) -> soap.setContractDsAuto(aSessionId, aCONTRACTDSAUTO));
    }

    @Override
    public CONTRACTDSCARGO setContractDsCargo(final CONTRACTDSCARGO aCONTRACTDSCARGO) {
	return session.call((soap, aSessionId) -> soap.setContractDsCargo(aSessionId, aCONTRACTDSCARGO));
    }

    @Override
    public CONTRACTDSGPOAIR setContractDsGpoAir(final CONTRACTDSGPOAIR aCONTRACTDSGPOAIR) {
	return session.call((soap, aSessionId) -> soap.setContractDsGpoAir(aSessionId, aCONTRACTDSGPOAIR));
    }

    @Override
    public CONTRACTDSGPOAUTO setContractDsGpoAuto(final CONTRACTDSGPOAUTO aCONTRACTDSGPOAUTO) {
	return session.call((soap, aSessionId) -> soap.setContractDsGpoAuto(aSessionId, aCONTRACTDSGPOAUTO));
    }

    @Override
    public CONTRACTDSGPOOTHER setContractDsGpoOther(final CONTRACTDSGPOOTHER aCONTRACTDSGPOOTHER) {
	return session.call((soap, aSessionId) -> soap.setContractDsGpoOther(aSessionId, aCONTRACTDSGPOOTHER));
    }

    @Override
    public CONTRACTDSGPOWATER setContractDsGpoWater(final CONTRACTDSGPOWATER aCONTRACTDSGPOWATER) {
	return session.call((soap, aSessionId) -> soap.setContractDsGpoWater(aSessionId, aCONTRACTDSGPOWATER));
    }

    @Override
    public CONTRACTDSGUARANTEE setContractDsGuarantee(final CONTRACTDSGUARANTEE aCONTRACTDSGUARANTEE) {
	return session.call((soap, aSessionId) -> soap.setContractDsGuarantee(aSessionId, aCONTRACTDSGUARANTEE));
    }

    @Override
    public CONTRACTDSHEALTH setContractDsHealth(final CONTRACTDSHEALTH aCONTRACTDSHEALTH) {
	return session.call((soap, aSessionId) -> soap.setContractDsHealth(aSessionId, aCONTRACTDSHEALTH));
    }

    @Override
    public CONTRACTDSLEGALCOSTS setContractDsLegalCosts(final CONTRACTDSLEGALCOSTS aCONTRACTDSLEGALCOSTS) {
	return session.call((soap, aSessionId) -> soap.setContractDsLegalCosts(aSessionId, aCONTRACTDSLEGALCOSTS));
    }

    @Override
    public CONTRACTDSLIFE setContractDsLife(final CONTRACTDSLIFE aCONTRACTDSLIFE) {
	return session.call((soap, aSessionId) -> soap.setContractDsLife(aSessionId, aCONTRACTDSLIFE));
    }

    @Override
    public CONTRACTDSLOAN setContractDsLoan(final CONTRACTDSLOAN aCONTRACTDSLOAN) {
	return session.call((soap, aSessionId) -> soap.setContractDsLoan(aSessionId, aCONTRACTDSLOAN));
    }

    @Override
    public CONTRACTDSLOSSES setContractDsLosses(final CONTRACTDSLOSSES aCONTRACTDSLOSSES) {
	return session.call((soap, aSessionId) -> soap.setContractDsLosses(aSessionId, aCONTRACTDSLOSSES));
    }

    @Override
    public CONTRACTDSMORTGAGE setContractDsMortgage(final CONTRACTDSMORTGAGE aCONTRACTDSMORTGAGE) {
	return session.call((soap, aSessionId) -> soap.setContractDsMortgage(aSessionId, aCONTRACTDSMORTGAGE));
    }

    @Override
    public CONTRACTDSOTHERLOSSES setContractDsOtherLosses(final CONTRACTDSOTHERLOSSES aCONTRACTDSOTHERLOSSES) {
	return session.call((soap, aSessionId) -> soap.setContractDsOtherLosses(aSessionId, aCONTRACTDSOTHERLOSSES));
    }

    @Override
    public CONTRACTDSPROPERTY setContractDsProperty(final CONTRACTDSPROPERTY aCONTRACTDSPROPERTY) {
	return session.call((soap, aSessionId) -> soap.setContractDsProperty(aSessionId, aCONTRACTDSPROPERTY));
    }

    @Override
    public CONTRACTDSRAILWAYS setContractDsRailways(final CONTRACTDSRAILWAYS aCONTRACTDSRAILWAYS) {
	return session.call((soap, aSessionId) -> soap.setContractDsRailways(aSessionId, aCONTRACTDSRAILWAYS));
    }

    @Override
    public CONTRACTDSTITLE setContractDsTitle(final CONTRACTDSTITLE aCONTRACTDSTITLE) {
	return session.call((soap, aSessionId) -> soap.setContractDsTitle(aSessionId, aCONTRACTDSTITLE));
    }

    @Override
    public CONTRACTDSWATER setContractDsWater(final CONTRACTDSWATER aCONTRACTDSWATER) {
	return session.call((soap, aSessionId) -> soap.setContractDsWater(aSessionId, aCONTRACTDSWATER));
    }

    @Override
    public String setContractDuplicate(final String aParamsXML) {
	return session.call((soap, aSessionId) -> soap.setContractDuplicate(aSessionId, aParamsXML));
    }

    @Override
    public CONTRACTOSECO setContractOsEco(final CONTRACTOSECO aCONTRACTOSECO) {
	return session.call((soap, aSessionId) -> soap.setContractOsEco(aSessionId, aCONTRACTOSECO));
    }

    @Override
    public CONTRACTOSGPOAUDITORS setContractOsgpoAuditors(final CONTRACTOSGPOAUDITORS aCONTRACTOSGPOAUDITORS) {
	return session.call((soap, aSessionId) -> soap.setContractOsgpoAuditors(aSessionId, aCONTRACTOSGPOAUDITORS));
    }

    @Override
    public CONTRACTOSGPODO setContractOsgpoDo(final CONTRACTOSGPODO aCONTRACTOSGPODO) {
	return session.call((soap, aSessionId) -> soap.setContractOsgpoDo(aSessionId, aCONTRACTOSGPODO));
    }

    @Override
    public CONTRACTOSGPONOTARIUS setContractOsgpoNotarius(final CONTRACTOSGPONOTARIUS aCONTRACTOSGPONOTARIUS) {
	return session.call((soap, aSessionId) -> soap.setContractOsgpoNotarius(aSessionId, aCONTRACTOSGPONOTARIUS));
    }

    @Override
    public CONTRACTOSGPOPASSENGERS setContractOsgpoPassengers(final CONTRACTOSGPOPASSENGERS aCONTRACTOSGPOPASSENGERS) {
	return session
		.call((soap, aSessionId) -> soap.setContractOsgpoPassengers(aSessionId, aCONTRACTOSGPOPASSENGERS));
    }

    @Override
    public CONTRACTOSGPOTOUR setContractOsgpoTour(final CONTRACTOSGPOTOUR aCONTRACTOSGPOTOUR) {
	return session.call((soap, aSessionId) -> soap.setContractOsgpoTour(aSessionId, aCONTRACTOSGPOTOUR));
    }

    @Override
    public CONTRACTOSRNS setContractOSRNS(final CONTRACTOSRNS aCONTRACTOSRNS) {
	return session.call((soap, aSessionId) -> soap.setContractOSRNS(aSessionId, aCONTRACTOSRNS));
    }

    @Override
    public String setContractRescinding(final int aCONTRACTID, final int aRESCINDINGREASONID,
	    final String aRESCINDINGDATE) {
	return session.call((soap, aSessionId) -> soap.setContractRescinding(aSessionId, aCONTRACTID,
		aRESCINDINGREASONID, aRESCINDINGDATE));
    }

    @Override
    public IECOMMON setIECOMMON(final IECOMMON aIECOMMON) {
	return session.call((soap, aSessionId) -> soap.setIECOMMON(aSessionId, aIECOMMON));
    }

    @Override
    public InsuranceEvent setInsuranceEvent(final InsuranceEvent aInsuranceEvent) {
	return session.call((soap, aSessionId) -> soap.setInsuranceEvent(aSessionId, aInsuranceEvent));
    }

    @Override
    public void setInsuranceEventMistake(final int aInsEventID, final String aDate) {
	session.process((soap, aSessionId) -> soap.setInsuranceEventMistake(aSessionId, aInsEventID, aDate));
    }

    @Override
    public MIDDLEMAN setMiddleman(final MIDDLEMAN aMiddleman) {
	return session.call((soap, aSessionId) -> soap.setMiddleman(aSessionId, aMiddleman));
    }

    @Override
    public NewUserRequest setNewUserRequest(final NewUserRequest aNewUserRequest) {
	return session.call((soap, aSessionId) -> soap.setNewUserRequest(aSessionId, aNewUserRequest));
    }

    @Override
    public void setPerpetratorMistake(final int aPerpetratorID, final String aDate) {
	session.process((soap, aSessionId) -> soap.setPerpetratorMistake(aSessionId, aPerpetratorID, aDate));
    }

    @Override
    public Policy setPolicy(final Policy aPolicy) {
	return session.call((soap, aSessionId) -> soap.setPolicy(aSessionId, aPolicy));
    }

    @Override
    public Policy setPolicyDuplicate(final int aOriginalPolicyId, final String aDuplicateNumber,
	    final String aDuplicateDate, final String aDescription) {
	return session.call((soap, aSessionId) -> soap.setPolicyDuplicate(aSessionId, aOriginalPolicyId,
		aDuplicateNumber, aDuplicateDate, aDescription));
    }

    @Override
    public Policy setPolicyDuplicateXML(final String aParamsXML) {
	return session.call((soap, aSessionId) -> soap.setPolicyDuplicateXML(aSessionId, aParamsXML));
    }

    @Override
    public int setPolicyRescindingReason(final int aPolicyId, final int aRescindingReasonId,
	    final String aRescindingDate) {
	return session.callInt((soap, aSessionId) -> soap.setPolicyRescindingReason(aSessionId, aPolicyId,
		aRescindingReasonId, aRescindingDate));
    }

    @Override
    public REQUEST setRequest(final REQUEST aREQUEST) {
	return session.call((soap, aSessionId) -> soap.setRequest(aSessionId, aREQUEST));
    }

    @Override
    public TF setTF(final TF aTF) {
	return session.call((soap, aSessionId) -> soap.setTF(aSessionId, aTF));
    }

    @Override
    public InsuranceEvent setVictimObject(final VictimObject aVictimObject) {
	return session.call((soap, aSessionId) -> soap.setVictimObject(aSessionId, aVictimObject));
    }

    @Override
    public VOITUREMARK setVoitureMark(final VOITUREMARK aVoitureMark) {
	return session.call((soap, aSessionId) -> soap.setVoitureMark(aSessionId, aVoitureMark));
    }

    @Override
    public VOITUREMODEL setVoitureModel(final VOITUREMODEL aVoitureModel) {
	return session.call((soap, aSessionId) -> soap.setVoitureModel(aSessionId, aVoitureModel));
    }
}