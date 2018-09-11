package tech.lapsa.esbd.connection;

import javax.xml.datatype.XMLGregorianCalendar;

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

public interface Connection extends AutoCloseable {

    User currentUser();
    
    @Override
    void close() throws ConnectionException;

    /**
     * Расчет страховой премии по обязательным видам страхования, кроме ОС ГПО
     * ВТС
     *
     * @param aXml
     * @return returns int
     */
    public int calculateContractPremium(String aXml);

    /**
     * Расчет страховой премии ОС ГПО ВТС
     *
     * @param aPolicy
     * @return returns int
     */
    public int calculatePolicyPremium(Policy aPolicy);

    /**
     * Удаление заявки на создание пользователя
     *
     * @param aRequestID
     */
    public void deleteNewUserRequest(int aRequestID);

    /**
     * Удаление полиса ОС ГПО ВТС
     *
     * @param aPolicyID
     */
    public void deletePolicy(int aPolicyID);

    /**
     * Выполнение метода ЕСБД
     *
     * @param aRequest
     * @return returns tech.lapsa.esbd.jaxws.wsimport.EsbdResponse
     */
    public EsbdResponse execute(EsbdRequest aRequest);

    /**
     * Возвращает идентификатор класса бонус-малус
     *
     * @param aTFId
     * @param aDate
     * @param aClientId
     * @return returns int
     */
    public int getClassId(int aClientId, String aDate, int aTFId);

    /**
     * Возвращает наименование класса бонус-малус
     *
     * @param aClassId
     * @return returns java.lang.String
     */
    public String getClassText(int aClassId);

    /**
     * Получение списка клиентов по идентификатору
     *
     * @param aID
     * @return returns tech.lapsa.esbd.jaxws.wsimport.Client
     */
    public Client getClientByID(int aID);

    /**
     * Получение списка реквизитов ИП/КХ по идентификатору клиента
     *
     * @param aClientID
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCLIENTPBDETAILS
     */
    public ArrayOfCLIENTPBDETAILS getClientPBDetailsListByID(int aClientID);

    /**
     * Получение списка клиентов по ключевым полям
     *
     * @param aClient
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfClient
     */
    public ArrayOfClient getClientsByKeyFields(Client aClient);

    /**
     * Получение списка клиентов по РНН
     *
     * @param aTPRN
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfClient
     */
    public ArrayOfClient getClientsByRNN(String aTPRN);

    /**
     * Получение списка договоров Обязательное страхование в растениеводстве по
     * дате заключения
     *
     * @param aContractDate
     * @return returns
     *         tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTAGRICULTURELIST
     */
    public ArrayOfCONTRACTAGRICULTURELIST getContractAgricultureByContractDate(String aContractDate);

    /**
     * Получение по идентификатору договора Обязательное страхование в
     * растениеводстве
     *
     * @param aCONTRACTID
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTAGRICULTURELIST
     */
    public CONTRACTAGRICULTURELIST getContractAgricultureById(int aCONTRACTID);

    /**
     * Получение списка договоров Обязательное страхование в растениеводстве по
     * номеру договора
     *
     * @param aContractNumber
     * @return returns
     *         tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTAGRICULTURELIST
     */
    public ArrayOfCONTRACTAGRICULTURELIST getContractAgricultureByNumber(String aContractNumber);

    /**
     * Получение списка договоров Обязательное страхование в растениеводстве по
     * дате ввода (изменения)
     *
     * @param aDateBeg
     * @param aDateEnd
     * @return returns
     *         tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTAGRICULTURELIST
     */
    public ArrayOfCONTRACTAGRICULTURELIST getContractAgricultureByPeriod(String aDateBeg, String aDateEnd);

    /**
     * Возвращает список идентификаторов договоров определенного класса по дате
     * утверждения и/или дате расторжения
     *
     * @param aApproveDate
     * @param aRescindingDate
     * @param aInsuranceTypeID
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfString
     */
    public ArrayOfString getContractByAppRescDate(int aInsuranceTypeID, String aApproveDate, String aRescindingDate);

    /**
     * Получение списка договоров Страхование от несчастных случаев по дате
     * заключения
     *
     * @param aContractDate
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSACCIDENT
     */
    public ArrayOfCONTRACTDSACCIDENT getContractDsAccidentByContractDate(String aContractDate);

    /**
     * Получение по идентификатору договора Страхование от несчастных случаев
     *
     * @param aCONTRACTID
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSACCIDENT
     */
    public CONTRACTDSACCIDENT getContractDsAccidentById(int aCONTRACTID);

    /**
     * Получение списка договоров Страхование от несчастных случаев по номеру
     * договора
     *
     * @param aContractNumber
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSACCIDENT
     */
    public ArrayOfCONTRACTDSACCIDENT getContractDsAccidentByNumber(String aContractNumber);

    /**
     * Получение списка договоров Страхование от несчастных случаев по дате
     * ввода (изменения)
     *
     * @param aDateBeg
     * @param aDateEnd
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSACCIDENT
     */
    public ArrayOfCONTRACTDSACCIDENT getContractDsAccidentByPeriod(String aDateBeg, String aDateEnd);

    /**
     * Получение списка договоров ДС воздушного транспорта по дате заключения
     *
     * @param aContractDate
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSAIR
     */
    public ArrayOfCONTRACTDSAIR getContractDsAirByContractDate(String aContractDate);

    /**
     * Получение по идентификатору договора ДС воздушного транспорта
     *
     * @param aCONTRACTID
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSAIR
     */
    public CONTRACTDSAIR getContractDsAirById(int aCONTRACTID);

    /**
     * Получение списка договоров ДС воздушного транспорта по номеру договора
     *
     * @param aContractNumber
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSAIR
     */
    public ArrayOfCONTRACTDSAIR getContractDsAirByNumber(String aContractNumber);

    /**
     * Получение списка договоров ДС воздушного транспорта по дате ввода
     * (изменения)
     *
     * @param aDateBeg
     * @param aDateEnd
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSAIR
     */
    public ArrayOfCONTRACTDSAIR getContractDsAirByPeriod(String aDateBeg, String aDateEnd);

    /**
     * Получение списка договоров Аннутитное страхование по дате заключения
     *
     * @param aContractDate
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSANNUITY
     */
    public ArrayOfCONTRACTDSANNUITY getContractDsAnnuityByContractDate(String aContractDate);

    /**
     * Получение по идентификатору договора Аннутитное страхование
     *
     * @param aCONTRACTID
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSANNUITY
     */
    public CONTRACTDSANNUITY getContractDsAnnuityById(int aCONTRACTID);

    /**
     * Получение списка договоров Аннутитное страхование по номеру договора
     *
     * @param aContractNumber
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSANNUITY
     */
    public ArrayOfCONTRACTDSANNUITY getContractDsAnnuityByNumber(String aContractNumber);

    /**
     * Получение списка договоров Аннутитное страхование по дате ввода
     * (изменения)
     *
     * @param aDateBeg
     * @param aDateEnd
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSANNUITY
     */
    public ArrayOfCONTRACTDSANNUITY getContractDsAnnuityByPeriod(String aDateBeg, String aDateEnd);

    /**
     * Получение списка договоров ДС автомобильного транспорта по дате
     * заключения
     *
     * @param aContractDate
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSAUTO
     */
    public ArrayOfCONTRACTDSAUTO getContractDsAutoByContractDate(String aContractDate);

    /**
     * Получение по идентификатору договора ДС автомобильного транспорта
     *
     * @param aCONTRACTID
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSAUTO
     */
    public CONTRACTDSAUTO getContractDsAutoById(int aCONTRACTID);

    /**
     * Получение списка договоров ДС автомобильного транспорта по номеру
     * договора
     *
     * @param aContractNumber
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSAUTO
     */
    public ArrayOfCONTRACTDSAUTO getContractDsAutoByNumber(String aContractNumber);

    /**
     * Получение списка договоров ДС автомобильного транспорта по дате ввода
     * (изменения)
     *
     * @param aDateBeg
     * @param aDateEnd
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSAUTO
     */
    public ArrayOfCONTRACTDSAUTO getContractDsAutoByPeriod(String aDateBeg, String aDateEnd);

    /**
     * Получение списка договоров ДС грузов по дате заключения
     *
     * @param aContractDate
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSCARGO
     */
    public ArrayOfCONTRACTDSCARGO getContractDsCargoByContractDate(String aContractDate);

    /**
     * Получение по идентификатору договора ДС грузов
     *
     * @param aCONTRACTID
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSCARGO
     */
    public CONTRACTDSCARGO getContractDsCargoById(int aCONTRACTID);

    /**
     * Получение списка договоров ДС грузов по номеру договора
     *
     * @param aContractNumber
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSCARGO
     */
    public ArrayOfCONTRACTDSCARGO getContractDsCargoByNumber(String aContractNumber);

    /**
     * Получение списка договоров ДС грузов по дате ввода (изменения)
     *
     * @param aDateBeg
     * @param aDateEnd
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSCARGO
     */
    public ArrayOfCONTRACTDSCARGO getContractDsCargoByPeriod(String aDateBeg, String aDateEnd);

    /**
     * Получение списка договоров ДС ГПО владельцев воздушного транспорта по
     * дате заключения
     *
     * @param aContractDate
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSGPOAIR
     */
    public ArrayOfCONTRACTDSGPOAIR getContractDsGpoAirByContractDate(String aContractDate);

    /**
     * Получение по идентификатору договора ДС ГПО владельцев воздушного
     * транспорта
     *
     * @param aCONTRACTID
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSGPOAIR
     */
    public CONTRACTDSGPOAIR getContractDsGpoAirById(int aCONTRACTID);

    /**
     * Получение списка договоров ДС ГПО владельцев воздушного транспорта по
     * номеру договора
     *
     * @param aContractNumber
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSGPOAIR
     */
    public ArrayOfCONTRACTDSGPOAIR getContractDsGpoAirByNumber(String aContractNumber);

    /**
     * Получение списка договоров ДС ГПО владельцев воздушного транспорта по
     * дате ввода (изменения)
     *
     * @param aDateBeg
     * @param aDateEnd
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSGPOAIR
     */
    public ArrayOfCONTRACTDSGPOAIR getContractDsGpoAirByPeriod(String aDateBeg, String aDateEnd);

    /**
     * Получение списка договоров ДС ГПО владельцев автомобильного транспорта по
     * дате заключения
     *
     * @param aContractDate
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSGPOAUTO
     */
    public ArrayOfCONTRACTDSGPOAUTO getContractDsGpoAutoByContractDate(String aContractDate);

    /**
     * Получение по идентификатору договора ДС ГПО владельцев автомобильного
     * транспорта
     *
     * @param aCONTRACTID
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSGPOAUTO
     */
    public CONTRACTDSGPOAUTO getContractDsGpoAutoById(int aCONTRACTID);

    /**
     * Получение списка договоров ДС ГПО владельцев автомобильного транспорта по
     * номеру договора
     *
     * @param aContractNumber
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSGPOAUTO
     */
    public ArrayOfCONTRACTDSGPOAUTO getContractDsGpoAutoByNumber(String aContractNumber);

    /**
     * Получение списка договоров ДС ГПО владельцев автомобильного транспорта по
     * дате ввода (изменения)
     *
     * @param aDateBeg
     * @param aDateEnd
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSGPOAUTO
     */
    public ArrayOfCONTRACTDSGPOAUTO getContractDsGpoAutoByPeriod(String aDateBeg, String aDateEnd);

    /**
     * Получение списка договоров ДС ГПО (другое) по дате заключения
     *
     * @param aContractDate
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSGPOOTHER
     */
    public ArrayOfCONTRACTDSGPOOTHER getContractDsGpoOtherByContractDate(String aContractDate);

    /**
     * Получение по идентификатору договора ДС ГПО (другое)
     *
     * @param aCONTRACTID
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSGPOOTHER
     */
    public CONTRACTDSGPOOTHER getContractDsGpoOtherById(int aCONTRACTID);

    /**
     * Получение списка договоров ДС ГПО (другое) по номеру договора
     *
     * @param aContractNumber
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSGPOOTHER
     */
    public ArrayOfCONTRACTDSGPOOTHER getContractDsGpoOtherByNumber(String aContractNumber);

    /**
     * Получение списка договоров ДС ГПО (другое) по дате ввода (изменения)
     *
     * @param aDateBeg
     * @param aDateEnd
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSGPOOTHER
     */
    public ArrayOfCONTRACTDSGPOOTHER getContractDsGpoOtherByPeriod(String aDateBeg, String aDateEnd);

    /**
     * Получение списка договоров ДС ГПО владельцев водного транспорта по дате
     * заключения
     *
     * @param aContractDate
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSGPOWATER
     */
    public ArrayOfCONTRACTDSGPOWATER getContractDsGpoWaterByContractDate(String aContractDate);

    /**
     * Получение по идентификатору договора ДС ГПО владельцев водного транспорта
     *
     * @param aCONTRACTID
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSGPOWATER
     */
    public CONTRACTDSGPOWATER getContractDsGpoWaterById(int aCONTRACTID);

    /**
     * Получение списка договоров ДС ГПО владельцев водного транспорта по номеру
     * договора
     *
     * @param aContractNumber
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSGPOWATER
     */
    public ArrayOfCONTRACTDSGPOWATER getContractDsGpoWaterByNumber(String aContractNumber);

    /**
     * Получение списка договоров ДС ГПО владельцев водного транспорта по дате
     * ввода (изменения)
     *
     * @param aDateBeg
     * @param aDateEnd
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSGPOWATER
     */
    public ArrayOfCONTRACTDSGPOWATER getContractDsGpoWaterByPeriod(String aDateBeg, String aDateEnd);

    /**
     * Получение списка договоров ДС гарантий и поручительств по дате заключения
     *
     * @param aContractDate
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSGUARANTEE
     */
    public ArrayOfCONTRACTDSGUARANTEE getContractDsGuaranteeByContractDate(String aContractDate);

    /**
     * Получение по идентификатору договора ДС гарантий и поручительств
     *
     * @param aCONTRACTID
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSGUARANTEE
     */
    public CONTRACTDSGUARANTEE getContractDsGuaranteeById(int aCONTRACTID);

    /**
     * Получение списка договоров ДС гарантий и поручительств по номеру договора
     *
     * @param aContractNumber
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSGUARANTEE
     */
    public ArrayOfCONTRACTDSGUARANTEE getContractDsGuaranteeByNumber(String aContractNumber);

    /**
     * Получение списка договоров ДС гарантий и поручительств по дате ввода
     * (изменения)
     *
     * @param aDateBeg
     * @param aDateEnd
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSGUARANTEE
     */
    public ArrayOfCONTRACTDSGUARANTEE getContractDsGuaranteeByPeriod(String aDateBeg, String aDateEnd);

    /**
     * Получение списка договоров Страхования на случай болезни по дате
     * заключения
     *
     * @param aContractDate
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSHEALTH
     */
    public ArrayOfCONTRACTDSHEALTH getContractDsHealthByContractDate(String aContractDate);

    /**
     * Получение по идентификатору договора Страхования на случай болезни
     *
     * @param aCONTRACTID
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSHEALTH
     */
    public CONTRACTDSHEALTH getContractDsHealthById(int aCONTRACTID);

    /**
     * Получение списка договоров Страхования на случай болезни по номеру
     * договора
     *
     * @param aContractNumber
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSHEALTH
     */
    public ArrayOfCONTRACTDSHEALTH getContractDsHealthByNumber(String aContractNumber);

    /**
     * Получение списка договоров Страхования на случай болезни по дате ввода
     * (изменения)
     *
     * @param aDateBeg
     * @param aDateEnd
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSHEALTH
     */
    public ArrayOfCONTRACTDSHEALTH getContractDsHealthByPeriod(String aDateBeg, String aDateEnd);

    /**
     * Получение списка договоров ДС судебных расходов по дате заключения
     *
     * @param aContractDate
     * @return returns
     *         tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSLEGALCOSTS
     */
    public ArrayOfCONTRACTDSLEGALCOSTS getContractDsLegalCostsByContractDate(String aContractDate);

    /**
     * Получение по идентификатору договора ДС судебных расходов
     *
     * @param aCONTRACTID
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSLEGALCOSTS
     */
    public CONTRACTDSLEGALCOSTS getContractDsLegalCostsById(int aCONTRACTID);

    /**
     * Получение списка договоров ДС судебных расходов по номеру договора
     *
     * @param aContractNumber
     * @return returns
     *         tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSLEGALCOSTS
     */
    public ArrayOfCONTRACTDSLEGALCOSTS getContractDsLegalCostsByNumber(String aContractNumber);

    /**
     * Получение списка договоров ДС судебных расходов по дате ввода (изменения)
     *
     * @param aDateBeg
     * @param aDateEnd
     * @return returns
     *         tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSLEGALCOSTS
     */
    public ArrayOfCONTRACTDSLEGALCOSTS getContractDsLegalCostsByPeriod(String aDateBeg, String aDateEnd);

    /**
     * Получение списка договоров Страхование жизни по дате заключения
     *
     * @param aContractDate
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSLIFE
     */
    public ArrayOfCONTRACTDSLIFE getContractDsLifeByContractDate(String aContractDate);

    /**
     * Получение по идентификатору договора Страхование жизни
     *
     * @param aCONTRACTID
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSLIFE
     */
    public CONTRACTDSLIFE getContractDsLifeById(int aCONTRACTID);

    /**
     * Получение списка договоров Страхование жизни по номеру договора
     *
     * @param aContractNumber
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSLIFE
     */
    public ArrayOfCONTRACTDSLIFE getContractDsLifeByNumber(String aContractNumber);

    /**
     * Получение списка договоров Ипотечное страхование по дате ввода
     * (изменения)
     *
     * @param aDateBeg
     * @param aDateEnd
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSLIFE
     */
    public ArrayOfCONTRACTDSLIFE getContractDsLifeByPeriod(String aDateBeg, String aDateEnd);

    /**
     * Получение списка договоров Страхование займов по дате заключения
     *
     * @param aContractDate
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSLOAN
     */
    public ArrayOfCONTRACTDSLOAN getContractDsLoanByContractDate(String aContractDate);

    /**
     * Получение по идентификатору договора Страхование займов
     *
     * @param aCONTRACTID
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSLOAN
     */
    public CONTRACTDSLOAN getContractDsLoanById(int aCONTRACTID);

    /**
     * Получение списка договоров Страхование займов по номеру договора
     *
     * @param aContractNumber
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSLOAN
     */
    public ArrayOfCONTRACTDSLOAN getContractDsLoanByNumber(String aContractNumber);

    /**
     * Получение списка договоров Страхование займов по дате ввода (изменения)
     *
     * @param aDateBeg
     * @param aDateEnd
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSLOAN
     */
    public ArrayOfCONTRACTDSLOAN getContractDsLoanByPeriod(String aDateBeg, String aDateEnd);

    /**
     * Получение списка договоров ДС убытков финансовых организаций по дате
     * заключения
     *
     * @param aContractDate
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSLOSSES
     */
    public ArrayOfCONTRACTDSLOSSES getContractDsLossesByContractDate(String aContractDate);

    /**
     * Получение по идентификатору договора ДС убытков финансовых организаций
     *
     * @param aCONTRACTID
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSLOSSES
     */
    public CONTRACTDSLOSSES getContractDsLossesById(int aCONTRACTID);

    /**
     * Получение списка договоров ДС убытков финансовых организаций по номеру
     * договора
     *
     * @param aContractNumber
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSLOSSES
     */
    public ArrayOfCONTRACTDSLOSSES getContractDsLossesByNumber(String aContractNumber);

    /**
     * Получение списка договоров ДС убытков финансовых организаций по дате
     * ввода (изменения)
     *
     * @param aDateBeg
     * @param aDateEnd
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSLOSSES
     */
    public ArrayOfCONTRACTDSLOSSES getContractDsLossesByPeriod(String aDateBeg, String aDateEnd);

    /**
     * Получение списка договоров Ипотечное страхование по дате заключения
     *
     * @param aContractDate
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSMORTGAGE
     */
    public ArrayOfCONTRACTDSMORTGAGE getContractDsMortgageByContractDate(String aContractDate);

    /**
     * Получение по идентификатору договора Ипотечное страхование
     *
     * @param aCONTRACTID
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSMORTGAGE
     */
    public CONTRACTDSMORTGAGE getContractDsMortgageById(int aCONTRACTID);

    /**
     * Получение списка договоров Ипотечное страхование по номеру договора
     *
     * @param aContractNumber
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSMORTGAGE
     */
    public ArrayOfCONTRACTDSMORTGAGE getContractDsMortgageByNumber(String aContractNumber);

    /**
     * Получение списка договоров Ипотечное страхование по дате ввода
     * (изменения)
     *
     * @param aDateBeg
     * @param aDateEnd
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSMORTGAGE
     */
    public ArrayOfCONTRACTDSMORTGAGE getContractDsMortgageByPeriod(String aDateBeg, String aDateEnd);

    /**
     * Получение списка договоров ДС от прочих финансовых убытков по дате
     * заключения
     *
     * @param aContractDate
     * @return returns
     *         tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSOTHERLOSSES
     */
    public ArrayOfCONTRACTDSOTHERLOSSES getContractDsOtherLossesByContractDate(String aContractDate);

    /**
     * Получение по идентификатору договора ДС от прочих финансовых убытков
     *
     * @param aCONTRACTID
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSOTHERLOSSES
     */
    public CONTRACTDSOTHERLOSSES getContractDsOtherLossesById(int aCONTRACTID);

    /**
     * Получение списка договоров ДС от прочих финансовых убытков по номеру
     * договора
     *
     * @param aContractNumber
     * @return returns
     *         tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSOTHERLOSSES
     */
    public ArrayOfCONTRACTDSOTHERLOSSES getContractDsOtherLossesByNumber(String aContractNumber);

    /**
     * Получение списка договоров ДС от прочих финансовых убытков по дате ввода
     * (изменения)
     *
     * @param aDateBeg
     * @param aDateEnd
     * @return returns
     *         tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSOTHERLOSSES
     */
    public ArrayOfCONTRACTDSOTHERLOSSES getContractDsOtherLossesByPeriod(String aDateBeg, String aDateEnd);

    /**
     * Получение списка договоров ДС имущества от ущерба по дате заключения
     *
     * @param aContractDate
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSPROPERTY
     */
    public ArrayOfCONTRACTDSPROPERTY getContractDsPropertyByContractDate(String aContractDate);

    /**
     * Получение по идентификатору договора ДС имущества от ущерба
     *
     * @param aCONTRACTID
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSPROPERTY
     */
    public CONTRACTDSPROPERTY getContractDsPropertyById(int aCONTRACTID);

    /**
     * Получение списка договоров ДС имущества от ущерба по номеру договора
     *
     * @param aContractNumber
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSPROPERTY
     */
    public ArrayOfCONTRACTDSPROPERTY getContractDsPropertyByNumber(String aContractNumber);

    /**
     * Получение списка договоров ДС имущества от ущерба по дате ввода
     * (изменения)
     *
     * @param aDateBeg
     * @param aDateEnd
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSPROPERTY
     */
    public ArrayOfCONTRACTDSPROPERTY getContractDsPropertyByPeriod(String aDateBeg, String aDateEnd);

    /**
     * Получение списка договоров ДС железнодорожного транспорта по дате
     * заключения
     *
     * @param aContractDate
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSRAILWAYS
     */
    public ArrayOfCONTRACTDSRAILWAYS getContractDsRailwaysByContractDate(String aContractDate);

    /**
     * Получение по идентификатору договора ДС железнодорожного транспорта
     *
     * @param aCONTRACTID
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSRAILWAYS
     */
    public CONTRACTDSRAILWAYS getContractDsRailwaysById(int aCONTRACTID);

    /**
     * Получение списка договоров ДС железнодорожного транспорта по номеру
     * договора
     *
     * @param aContractNumber
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSRAILWAYS
     */
    public ArrayOfCONTRACTDSRAILWAYS getContractDsRailwaysByNumber(String aContractNumber);

    /**
     * Получение списка договоров ДС железнодорожного транспорта по дате ввода
     * (изменения)
     *
     * @param aDateBeg
     * @param aDateEnd
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSRAILWAYS
     */
    public ArrayOfCONTRACTDSRAILWAYS getContractDsRailwaysByPeriod(String aDateBeg, String aDateEnd);

    /**
     * Получение списка договоров Титульного страхования по дате заключения
     *
     * @param aContractDate
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSTITLE
     */
    public ArrayOfCONTRACTDSTITLE getContractDsTitleByContractDate(String aContractDate);

    /**
     * Получение по идентификатору договора Титульного страхования
     *
     * @param aCONTRACTID
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSTITLE
     */
    public CONTRACTDSTITLE getContractDsTitleById(int aCONTRACTID);

    /**
     * Получение списка договоров Титульного страхования по номеру договора
     *
     * @param aContractNumber
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSTITLE
     */
    public ArrayOfCONTRACTDSTITLE getContractDsTitleByNumber(String aContractNumber);

    /**
     * Получение списка договоров Титульного страхования по дате ввода
     * (изменения)
     *
     * @param aDateBeg
     * @param aDateEnd
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSTITLE
     */
    public ArrayOfCONTRACTDSTITLE getContractDsTitleByPeriod(String aDateBeg, String aDateEnd);

    /**
     * Получение списка договоров ДС водного транспорта по дате заключения
     *
     * @param aContractDate
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSWATER
     */
    public ArrayOfCONTRACTDSWATER getContractDsWaterByContractDate(String aContractDate);

    /**
     * Получение по идентификатору договора ДС водного транспорта
     *
     * @param aCONTRACTID
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSWATER
     */
    public CONTRACTDSWATER getContractDsWaterById(int aCONTRACTID);

    /**
     * Получение списка договоров ДС водного транспорта по номеру договора
     *
     * @param aContractNumber
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSWATER
     */
    public ArrayOfCONTRACTDSWATER getContractDsWaterByNumber(String aContractNumber);

    /**
     * Получение списка договоров ДС водного транспорта по дате ввода
     * (изменения)
     *
     * @param aDateBeg
     * @param aDateEnd
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTDSWATER
     */
    public ArrayOfCONTRACTDSWATER getContractDsWaterByPeriod(String aDateBeg, String aDateEnd);

    /**
     * Получение списка договоров Обязательное экологическое страхование по дате
     * заключения
     *
     * @param aContractDate
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTOSECO
     */
    public ArrayOfCONTRACTOSECO getContractOsEcoByContractDate(String aContractDate);

    /**
     * Получение по идентификатору договора Обязательное экологическое
     * страхование
     *
     * @param aCONTRACTID
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTOSECO
     */
    public CONTRACTOSECO getContractOsEcoById(int aCONTRACTID);

    /**
     * Получение списка договоров Обязательное экологическое страхование по
     * номеру договора
     *
     * @param aContractNumber
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTOSECO
     */
    public ArrayOfCONTRACTOSECO getContractOsEcoByNumber(String aContractNumber);

    /**
     * Получение списка договоров Обязательное экологическое страхование по дате
     * ввода (изменения)
     *
     * @param aDateBeg
     * @param aDateEnd
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTOSECO
     */
    public ArrayOfCONTRACTOSECO getContractOsEcoByPeriod(String aDateBeg, String aDateEnd);

    /**
     * Получение списка договоров ОС ГПО аудиторских организаций по дате
     * заключения
     *
     * @param aContractDate
     * @return returns
     *         tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTOSGPOAUDITORS
     */
    public ArrayOfCONTRACTOSGPOAUDITORS getContractOsgpoAuditorsByContractDate(String aContractDate);

    /**
     * Получение по идентификатору договора ОС ГПО аудиторских организаций
     *
     * @param aCONTRACTID
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTOSGPOAUDITORS
     */
    public CONTRACTOSGPOAUDITORS getContractOsgpoAuditorsById(int aCONTRACTID);

    /**
     * Получение списка договоров ОС ГПО аудиторских организаций по номеру
     * договора
     *
     * @param aContractNumber
     * @return returns
     *         tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTOSGPOAUDITORS
     */
    public ArrayOfCONTRACTOSGPOAUDITORS getContractOsgpoAuditorsByNumber(String aContractNumber);

    /**
     * Получение списка договоров ОС ГПО аудиторских организаций по дате ввода
     * (изменения)
     *
     * @param aDateBeg
     * @param aDateEnd
     * @return returns
     *         tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTOSGPOAUDITORS
     */
    public ArrayOfCONTRACTOSGPOAUDITORS getContractOsgpoAuditorsByPeriod(String aDateBeg, String aDateEnd);

    /**
     * Получение списка договоров ОС ГПО владельцев опасных объектов по дате
     * заключения
     *
     * @param aContractDate
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTOSGPODO
     */
    public ArrayOfCONTRACTOSGPODO getContractOsgpoDoByContractDate(String aContractDate);

    /**
     * Получение по идентификатору договора ОС ГПО владельцев опасных объектов
     *
     * @param aCONTRACTID
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTOSGPODO
     */
    public CONTRACTOSGPODO getContractOsgpoDoById(int aCONTRACTID);

    /**
     * Получение списка договоров ОС ГПО владельцев опасных объектов по номеру
     * договора
     *
     * @param aContractNumber
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTOSGPODO
     */
    public ArrayOfCONTRACTOSGPODO getContractOsgpoDoByNumber(String aContractNumber);

    /**
     * Получение списка договоров ОС ГПО владельцев опасных объектов по дате
     * ввода (изменения)
     *
     * @param aDateBeg
     * @param aDateEnd
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTOSGPODO
     */
    public ArrayOfCONTRACTOSGPODO getContractOsgpoDoByPeriod(String aDateBeg, String aDateEnd);

    /**
     * Получение списка договоров ОС ГПО частных нотариусов по дате заключения
     *
     * @param aContractDate
     * @return returns
     *         tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTOSGPONOTARIUS
     */
    public ArrayOfCONTRACTOSGPONOTARIUS getContractOsgpoNotariusByContractDate(String aContractDate);

    /**
     * Получение по идентификатору договора ОС ГПО частных нотариусов
     *
     * @param aCONTRACTID
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTOSGPONOTARIUS
     */
    public CONTRACTOSGPONOTARIUS getContractOsgpoNotariusById(int aCONTRACTID);

    /**
     * Получение списка договоров ОС ГПО частных нотариусов по номеру договора
     *
     * @param aContractNumber
     * @return returns
     *         tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTOSGPONOTARIUS
     */
    public ArrayOfCONTRACTOSGPONOTARIUS getContractOsgpoNotariusByNumber(String aContractNumber);

    /**
     * Получение списка договоров ОС ГПО частных нотариусов по дате ввода
     * (изменения)
     *
     * @param aDateBeg
     * @param aDateEnd
     * @return returns
     *         tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTOSGPONOTARIUS
     */
    public ArrayOfCONTRACTOSGPONOTARIUS getContractOsgpoNotariusByPeriod(String aDateBeg, String aDateEnd);

    /**
     * Получение списка договоров ОС ГПО перевозчика по дате заключения
     *
     * @param aContractDate
     * @return returns
     *         tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTOSGPOPASSENGERS
     */
    public ArrayOfCONTRACTOSGPOPASSENGERS getContractOsgpoPassengersByContractDate(String aContractDate);

    /**
     * Получение по идентификатору договора ОС ГПО перевозчика перед пассажирами
     *
     * @param aCONTRACTID
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTOSGPOPASSENGERS
     */
    public CONTRACTOSGPOPASSENGERS getContractOsgpoPassengersById(int aCONTRACTID);

    /**
     * Получение списка договоров ОС ГПО перевозчика по номеру договора
     *
     * @param aContractNumber
     * @return returns
     *         tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTOSGPOPASSENGERS
     */
    public ArrayOfCONTRACTOSGPOPASSENGERS getContractOsgpoPassengersByNumber(String aContractNumber);

    /**
     * Получение списка договоров ОС ГПО перевозчика по дате ввода (изменения)
     *
     * @param aDateBeg
     * @param aDateEnd
     * @return returns
     *         tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTOSGPOPASSENGERS
     */
    public ArrayOfCONTRACTOSGPOPASSENGERS getContractOsgpoPassengersByPeriod(String aDateBeg, String aDateEnd);

    /**
     * Получение списка договоров ОС ГПО туроператора и турагента по дате
     * заключения
     *
     * @param aContractDate
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTOSGPOTOUR
     */
    public ArrayOfCONTRACTOSGPOTOUR getContractOsgpoTourByContractDate(String aContractDate);

    /**
     * Получение по идентификатору договора ОС ГПО туроператора и турагента
     *
     * @param aCONTRACTID
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTOSGPOTOUR
     */
    public CONTRACTOSGPOTOUR getContractOsgpoTourById(int aCONTRACTID);

    /**
     * Получение списка договоров ОС ГПО туроператора и турагента по номеру
     * договора
     *
     * @param aContractNumber
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTOSGPOTOUR
     */
    public ArrayOfCONTRACTOSGPOTOUR getContractOsgpoTourByNumber(String aContractNumber);

    /**
     * Получение списка договоров ОС ГПО туроператора и турагента по дате ввода
     * (изменения)
     *
     * @param aDateBeg
     * @param aDateEnd
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTOSGPOTOUR
     */
    public ArrayOfCONTRACTOSGPOTOUR getContractOsgpoTourByPeriod(String aDateBeg, String aDateEnd);

    /**
     * Получение списка договоров ОС РНС по дате заключения
     *
     * @param aContractDate
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTOSRNS
     */
    public ArrayOfCONTRACTOSRNS getContractOSRNSByContractDate(String aContractDate);

    /**
     * Получение по идентификатору договора ОС РНС
     *
     * @param aCONTRACTID
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTOSRNS
     */
    public CONTRACTOSRNS getContractOSRNSById(int aCONTRACTID);

    /**
     * Получение списка договоров ОС РНС по номеру договора
     *
     * @param aContractNumber
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTOSRNS
     */
    public ArrayOfCONTRACTOSRNS getContractOSRNSByNumber(String aContractNumber);

    /**
     * Получение списка договоров ОС РНС по дате ввода (изменения)
     *
     * @param aDateBeg
     * @param aDateEnd
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfCONTRACTOSRNS
     */
    public ArrayOfCONTRACTOSRNS getContractOSRNSByPeriod(String aDateBeg, String aDateEnd);

    /**
     * Возвращает СС (кроме ОСГПО ВТС) по идентификатору
     *
     * @param aIECOMMONID
     * @return returns tech.lapsa.esbd.jaxws.wsimport.IECOMMON
     */
    public IECOMMON getIECOMMONById(int aIECOMMONID);

    /**
     * Возвращает список идентификаторов Страховых случаев (кроме ОСГПО ВТС) по
     * заданным параметрам
     *
     * @param aIECOMMON
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfString
     */
    public ArrayOfString getIECOMMONBYPARAMS(IECOMMON aIECOMMON);

    /**
     * Возвращает Страховые случаи по заданным параметрам
     *
     * @param aInsuranceEvent
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfInsuranceEvent
     */
    public ArrayOfInsuranceEvent getInsuranceEvents(InsuranceEvent aInsuranceEvent);

    /**
     * Получение элементов справочника
     *
     * @param aTableName
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfItem
     */
    public ArrayOfItem getItems(String aTableName);

    /**
     * Возвращает идентификатор последней версии договора
     *
     * @param aContractID
     * @return returns java.lang.String
     */
    public String getLastContract(String aContractID);

    /**
     * Возвращает коэффициент надбавки для договоров ОСРНС
     *
     * @param idEmplType
     * @param idClient
     * @param idProfRisk
     * @return returns tech.lapsa.esbd.jaxws.wsimport.Item
     */
    public Item getMarkUpFactorXML(int idClient, int idEmplType, int idProfRisk);

    /**
     * Получение списка посредников по реквизитам
     *
     * @param aMiddleman
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfMIDDLEMAN
     */
    public ArrayOfMIDDLEMAN getMiddlemenByKeyFields(MIDDLEMAN aMiddleman);

    /**
     * Возвращает вознаграждения посредников введенные или измененные за период
     *
     * @param aDateTime1
     * @param aDateTime2
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfMiddlemenPayment
     */
    public ArrayOfMiddlemenPayment getMiddlemenPaymentsByCreatedOrChangedDateTime(String aDateTime1, String aDateTime2);

    /**
     * Расчет страховой премии для версии договора ОСРНС
     *
     * @param aXml
     * @return returns java.lang.String
     */
    public String getOSRNSPREMIUM(String aXml);

    /**
     * Возвращает список полисов ОС ГПО ВТС, созданных или измененных за период
     *
     * @param aDateTime1
     * @param aDateTime2
     * @param aBranchId
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfPolicy
     */
    public ArrayOfPolicy getPoliciesByCreatedOrChangedDateTime(int aBranchId, String aDateTime1, String aDateTime2);

    /**
     * Возвращает список полисов ОС ГПО ВТС по номеру полиса
     *
     * @param aPolicyNumber
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfPolicy
     */
    public ArrayOfPolicy getPoliciesByNumber(String aPolicyNumber);

    /**
     * Возвращает список полисов ОС ГПО ВТС по дате заключения полиса
     *
     * @param aPolicyDate2
     * @param aPolicyDate1
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfPolicy
     */
    public ArrayOfPolicy getPoliciesByPolicyDate(String aPolicyDate1, String aPolicyDate2);

    /**
     * Возвращает список идентификаторов полисов ОС ГПО ВТС по заданному условию
     *
     * @param aCondition
     * @param aPolicyDate2
     * @param aPolicyDate1
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfItem
     */
    public ArrayOfItem getPoliciesInfoByReason(String aCondition, String aPolicyDate1, String aPolicyDate2);

    /**
     * Возвращает полис ОСГПО ВТС по уникальному глобальному идентификатору
     *
     * @param aGlobalID
     * @return returns tech.lapsa.esbd.jaxws.wsimport.Policy
     */
    public Policy getPolicyByGlobalID(String aGlobalID);

    /**
     * Возвращает полис ОСГПО ВТС по идентификатору
     *
     * @param aPolicyID
     * @return returns tech.lapsa.esbd.jaxws.wsimport.Policy
     */
    public Policy getPolicyByID(int aPolicyID);

    /**
     * Метод устарел и больше не поддерживается
     *
     * @param reportName
     * @param parametrIn
     * @return returns java.lang.String
     */
    public String getReport(String reportName, ArrayOfSodParameter parametrIn);

    /**
     * Возвращает информацию о Заявке
     *
     * @param aRequestID
     * @return returns tech.lapsa.esbd.jaxws.wsimport.REQUEST
     */
    public REQUEST getREQUESTBYID(int aRequestID);

    /**
     * Возвращает дату и время сервера
     *
     * @return returns javax.xml.datatype.XMLGregorianCalendar
     */
    public XMLGregorianCalendar getServerDateTime();

    /**
     * Получение списка транспортных средств по номеру двигателя
     *
     * @param aEngineNumber
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfTF
     */
    public ArrayOfTF getTFByEngineNumber(String aEngineNumber);

    /**
     * Получение списка транспортных средств по ключевым полям
     *
     * @param aTF
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfTF
     */
    public ArrayOfTF getTFByKeyFields(TF aTF);

    /**
     * Получение списка транспортных средств по Гос. номеру
     *
     * @param aTFNUMBER
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfTF
     */
    public ArrayOfTF getTFByNumber(String aTFNUMBER);

    /**
     * Получение списка транспортных средств по VIN коду
     *
     * @param aVIN
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfTF
     */
    public ArrayOfTF getTFByVIN(String aVIN);

    /**
     * Возвращает отчет по классам бонус-малус для юр.лиц
     *
     * @param aClientId
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfTFClasses
     */
    public ArrayOfTFClasses getTFClasses(int aClientId);

    /**
     * Метод предназначен для получения списка объединенных записей
     *
     * @param aTableName
     * @param aDateFrom
     * @param aDateTo
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfUnionRecord
     */
    public ArrayOfUnionRecord getUnionRecords(String aTableName, String aDateFrom, String aDateTo);

    /**
     * Получить сертификаты пользователя
     *
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfUserCertificate
     */
    public ArrayOfUserCertificate getUserCertificates();

    /**
     * Возвращает список пользователей текущей организации
     *
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfUser
     */
    public ArrayOfUser getUsers();

    /**
     * Возвращает Пострадавшие объекты по заданным параметрам
     *
     * @param aVictimObject
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfVictimObject
     */
    public ArrayOfVictimObject getVictimObjects(VictimObject aVictimObject);

    /**
     * Возвращает список марок транспортных средств
     *
     * @param aSearchParams
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfVOITUREMARK
     */
    public ArrayOfVOITUREMARK getVoitureMarks(VOITUREMARK aSearchParams);

    /**
     * Возвращает идентификатор модели ТС по названию марки и модели. Если в
     * ЕСБД нет передаваемых марки и модели, то они добавляются
     *
     * @param aVoitureModelName
     * @param aVoitureMarkName
     * @return returns int
     */
    public int getVoitureModelIdByName(String aVoitureMarkName, String aVoitureModelName);

    /**
     * Возвращает список моделей автомобилей
     *
     * @param aSearchParams
     * @return returns tech.lapsa.esbd.jaxws.wsimport.ArrayOfVOITUREMODEL
     */
    public ArrayOfVOITUREMODEL getVoitureModels(VOITUREMODEL aSearchParams);

    /**
     * Проверяет состояние сессии (активна или нет)
     *
     * @param aUserName
     * @return returns boolean
     */
    public boolean sessionExists(String aUserName);

    /**
     * Сохранение клиента
     *
     * @param aClient
     * @return returns tech.lapsa.esbd.jaxws.wsimport.Client
     */
    public Client setClient(Client aClient);

    /**
     * Добавить рекизиты ИП/КХ для клиента
     *
     * @param aClientPBDetails
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CLIENTPBDETAILS
     */
    public CLIENTPBDETAILS setClientPBDetails(CLIENTPBDETAILS aClientPBDetails);

    /**
     * Сохранение договора Обязательное страхование в растениеводстве
     *
     * @param aCONTRACTAGRICULTURELIST
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTAGRICULTURELIST
     */
    public CONTRACTAGRICULTURELIST setContractAgriculture(CONTRACTAGRICULTURELIST aCONTRACTAGRICULTURELIST);

    /**
     * Сохранение договора Страхование от несчастных случаев
     *
     * @param aCONTRACTDSACCIDENT
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSACCIDENT
     */
    public CONTRACTDSACCIDENT setContractDsAccident(CONTRACTDSACCIDENT aCONTRACTDSACCIDENT);

    /**
     * Сохранение договора ДС воздушного транспорта
     *
     * @param aCONTRACTDSAIR
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSAIR
     */
    public CONTRACTDSAIR setContractDsAir(CONTRACTDSAIR aCONTRACTDSAIR);

    /**
     * Сохранение договора Аннутитное страхование
     *
     * @param aCONTRACTDSANNUITY
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSANNUITY
     */
    public CONTRACTDSANNUITY setContractDsAnnuity(CONTRACTDSANNUITY aCONTRACTDSANNUITY);

    /**
     * Сохранение договора ДС автомобильного транспорта
     *
     * @param aCONTRACTDSAUTO
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSAUTO
     */
    public CONTRACTDSAUTO setContractDsAuto(CONTRACTDSAUTO aCONTRACTDSAUTO);

    /**
     * Сохранение договора ДС грузов
     *
     * @param aCONTRACTDSCARGO
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSCARGO
     */
    public CONTRACTDSCARGO setContractDsCargo(CONTRACTDSCARGO aCONTRACTDSCARGO);

    /**
     * Сохранение договора ДС ГПО владельцев воздушного транспорта
     *
     * @param aCONTRACTDSGPOAIR
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSGPOAIR
     */
    public CONTRACTDSGPOAIR setContractDsGpoAir(CONTRACTDSGPOAIR aCONTRACTDSGPOAIR);

    /**
     * Сохранение договора ДС ГПО владельцев автомобильного транспорта
     *
     * @param aCONTRACTDSGPOAUTO
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSGPOAUTO
     */
    public CONTRACTDSGPOAUTO setContractDsGpoAuto(CONTRACTDSGPOAUTO aCONTRACTDSGPOAUTO);

    /**
     * Сохранение договора ДС ГПО (другое)
     *
     * @param aCONTRACTDSGPOOTHER
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSGPOOTHER
     */
    public CONTRACTDSGPOOTHER setContractDsGpoOther(CONTRACTDSGPOOTHER aCONTRACTDSGPOOTHER);

    /**
     * Сохранение договора ДС ГПО владельцев водного транспорта
     *
     * @param aCONTRACTDSGPOWATER
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSGPOWATER
     */
    public CONTRACTDSGPOWATER setContractDsGpoWater(CONTRACTDSGPOWATER aCONTRACTDSGPOWATER);

    /**
     * Сохранение договора ДС гарантий и поручительств
     *
     * @param aCONTRACTDSGUARANTEE
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSGUARANTEE
     */
    public CONTRACTDSGUARANTEE setContractDsGuarantee(CONTRACTDSGUARANTEE aCONTRACTDSGUARANTEE);

    /**
     * Сохранение договора Страхования на случай болезни
     *
     * @param aCONTRACTDSHEALTH
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSHEALTH
     */
    public CONTRACTDSHEALTH setContractDsHealth(CONTRACTDSHEALTH aCONTRACTDSHEALTH);

    /**
     * Сохранение договора ДС судебных расходов
     *
     * @param aCONTRACTDSLEGALCOSTS
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSLEGALCOSTS
     */
    public CONTRACTDSLEGALCOSTS setContractDsLegalCosts(CONTRACTDSLEGALCOSTS aCONTRACTDSLEGALCOSTS);

    /**
     * Сохранение договора Страхование жизни
     *
     * @param aCONTRACTDSLIFE
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSLIFE
     */
    public CONTRACTDSLIFE setContractDsLife(CONTRACTDSLIFE aCONTRACTDSLIFE);

    /**
     * Сохранение договора Страхование займов
     *
     * @param aCONTRACTDSLOAN
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSLOAN
     */
    public CONTRACTDSLOAN setContractDsLoan(CONTRACTDSLOAN aCONTRACTDSLOAN);

    /**
     * Сохранение договора ДС убытков финансовых организаций
     *
     * @param aCONTRACTDSLOSSES
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSLOSSES
     */
    public CONTRACTDSLOSSES setContractDsLosses(CONTRACTDSLOSSES aCONTRACTDSLOSSES);

    /**
     * Сохранение договора Ипотечное страхование
     *
     * @param aCONTRACTDSMORTGAGE
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSMORTGAGE
     */
    public CONTRACTDSMORTGAGE setContractDsMortgage(CONTRACTDSMORTGAGE aCONTRACTDSMORTGAGE);

    /**
     * Сохранение договора ДС от прочих финансовых убытков
     *
     * @param aCONTRACTDSOTHERLOSSES
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSOTHERLOSSES
     */
    public CONTRACTDSOTHERLOSSES setContractDsOtherLosses(CONTRACTDSOTHERLOSSES aCONTRACTDSOTHERLOSSES);

    /**
     * Сохранение договора ДС имущества от ущерба
     *
     * @param aCONTRACTDSPROPERTY
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSPROPERTY
     */
    public CONTRACTDSPROPERTY setContractDsProperty(CONTRACTDSPROPERTY aCONTRACTDSPROPERTY);

    /**
     * Сохранение договора ДС железнодорожного транспорта
     *
     * @param aCONTRACTDSRAILWAYS
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSRAILWAYS
     */
    public CONTRACTDSRAILWAYS setContractDsRailways(CONTRACTDSRAILWAYS aCONTRACTDSRAILWAYS);

    /**
     * Сохранение договора Титульного страхования
     *
     * @param aCONTRACTDSTITLE
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSTITLE
     */
    public CONTRACTDSTITLE setContractDsTitle(CONTRACTDSTITLE aCONTRACTDSTITLE);

    /**
     * Сохранение договора ДС водного транспорта
     *
     * @param aCONTRACTDSWATER
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTDSWATER
     */
    public CONTRACTDSWATER setContractDsWater(CONTRACTDSWATER aCONTRACTDSWATER);

    /**
     * Создание дубликата договора страхования
     *
     * @param aParamsXML
     * @return returns java.lang.String
     */
    public String setContractDuplicate(String aParamsXML);

    /**
     * Сохранение договора Обязательное экологическое страхование
     *
     * @param aCONTRACTOSECO
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTOSECO
     */
    public CONTRACTOSECO setContractOsEco(CONTRACTOSECO aCONTRACTOSECO);

    /**
     * Сохранение договора ОС ГПО аудиторских организаций
     *
     * @param aCONTRACTOSGPOAUDITORS
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTOSGPOAUDITORS
     */
    public CONTRACTOSGPOAUDITORS setContractOsgpoAuditors(CONTRACTOSGPOAUDITORS aCONTRACTOSGPOAUDITORS);

    /**
     * Сохранение договора ОС ГПО владельцев опасных объектов
     *
     * @param aCONTRACTOSGPODO
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTOSGPODO
     */
    public CONTRACTOSGPODO setContractOsgpoDo(CONTRACTOSGPODO aCONTRACTOSGPODO);

    /**
     * Сохранение договора ОС ГПО частных нотариусов
     *
     * @param aCONTRACTOSGPONOTARIUS
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTOSGPONOTARIUS
     */
    public CONTRACTOSGPONOTARIUS setContractOsgpoNotarius(CONTRACTOSGPONOTARIUS aCONTRACTOSGPONOTARIUS);

    /**
     * Сохранение договора ОС ГПО перевозчика перед пассажирами
     *
     * @param aCONTRACTOSGPOPASSENGERS
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTOSGPOPASSENGERS
     */
    public CONTRACTOSGPOPASSENGERS setContractOsgpoPassengers(CONTRACTOSGPOPASSENGERS aCONTRACTOSGPOPASSENGERS);

    /**
     * Сохранение договора ОС ГПО туроператора и турагента
     *
     * @param aCONTRACTOSGPOTOUR
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTOSGPOTOUR
     */
    public CONTRACTOSGPOTOUR setContractOsgpoTour(CONTRACTOSGPOTOUR aCONTRACTOSGPOTOUR);

    /**
     * Сохранение договора ОС РНС
     *
     * @param aCONTRACTOSRNS
     * @return returns tech.lapsa.esbd.jaxws.wsimport.CONTRACTOSRNS
     */
    public CONTRACTOSRNS setContractOSRNS(CONTRACTOSRNS aCONTRACTOSRNS);

    /**
     * Расторжение договора страхования
     *
     * @param aRESCINDINGREASONID
     * @param aRESCINDINGDATE
     * @param aCONTRACTID
     * @return returns java.lang.String
     */
    public String setContractRescinding(int aCONTRACTID, int aRESCINDINGREASONID, String aRESCINDINGDATE);

    /**
     * Передает информацию о Страховом случае в ЕСБД кроме ОС ГПО ВТС
     *
     * @param aIECOMMON
     * @return returns tech.lapsa.esbd.jaxws.wsimport.IECOMMON
     */
    public IECOMMON setIECOMMON(IECOMMON aIECOMMON);

    /**
     * Передает информацию о Страховом случае в ЕСБД
     *
     * @param aInsuranceEvent
     * @return returns tech.lapsa.esbd.jaxws.wsimport.InsuranceEvent
     */
    public InsuranceEvent setInsuranceEvent(InsuranceEvent aInsuranceEvent);

    /**
     * Пометка страхового случая как ошибочно введенного
     *
     * @param aDate
     * @param aInsEventID
     */
    public void setInsuranceEventMistake(int aInsEventID, String aDate);

    /**
     * Сохранение посредника в ЕСБД
     *
     * @param aMiddleman
     * @return returns tech.lapsa.esbd.jaxws.wsimport.MIDDLEMAN
     */
    public MIDDLEMAN setMiddleman(MIDDLEMAN aMiddleman);

    /**
     * Добавление или редактирование запроса на создание нового пользователя
     *
     * @param aNewUserRequest
     * @return returns tech.lapsa.esbd.jaxws.wsimport.NewUserRequest
     */
    public NewUserRequest setNewUserRequest(NewUserRequest aNewUserRequest);

    /**
     * Пометка виновного как ошибочно введенного
     *
     * @param aPerpetratorID
     * @param aDate
     */
    public void setPerpetratorMistake(int aPerpetratorID, String aDate);

    /**
     * Сохранение полиса ОС ГПО ВТС
     *
     * @param aPolicy
     * @return returns tech.lapsa.esbd.jaxws.wsimport.Policy
     */
    public Policy setPolicy(Policy aPolicy);

    /**
     * Создает дубликат полиса и расторгает оригинальный полис
     *
     * @param aOriginalPolicyId
     * @param aDescription
     * @param aDuplicateDate
     * @param aDuplicateNumber
     * @return returns tech.lapsa.esbd.jaxws.wsimport.Policy
     */
    public Policy setPolicyDuplicate(int aOriginalPolicyId, String aDuplicateNumber, String aDuplicateDate,
	    String aDescription);

    /**
     * Создает дубликат полиса и расторгает оригинальный полис
     *
     * @param aParamsXML
     * @return returns tech.lapsa.esbd.jaxws.wsimport.Policy
     */
    public Policy setPolicyDuplicateXML(String aParamsXML);

    /**
     * Устанавливает причину и дату расторжения полиса
     *
     * @param aRescindingReasonId
     * @param aRescindingDate
     * @param aPolicyId
     * @return returns int
     */
    public int setPolicyRescindingReason(int aPolicyId, int aRescindingReasonId, String aRescindingDate);

    /**
     * Передает информацию о Заявке в ЕСБД
     *
     * @param aREQUEST
     * @return returns tech.lapsa.esbd.jaxws.wsimport.REQUEST
     */
    public REQUEST setRequest(REQUEST aREQUEST);

    /**
     * Создание/редактирование ТС
     *
     * @param aTF
     * @return returns tech.lapsa.esbd.jaxws.wsimport.TF
     */
    public TF setTF(TF aTF);

    /**
     * Редактирование информации о Пострадавшем объекте в ЕСБД
     *
     * @param aVictimObject
     * @return returns tech.lapsa.esbd.jaxws.wsimport.InsuranceEvent
     */
    public InsuranceEvent setVictimObject(VictimObject aVictimObject);

    /**
     * Добавляет новую марку в справочник марок ТС
     *
     * @param aVoitureMark
     * @return returns tech.lapsa.esbd.jaxws.wsimport.VOITUREMARK
     */
    public VOITUREMARK setVoitureMark(VOITUREMARK aVoitureMark);

    /**
     * Добавляет новую модель в справочник Модели ТС
     *
     * @param aVoitureModel
     * @return returns tech.lapsa.esbd.jaxws.wsimport.VOITUREMODEL
     */
    public VOITUREMODEL setVoitureModel(VOITUREMODEL aVoitureModel);
}
