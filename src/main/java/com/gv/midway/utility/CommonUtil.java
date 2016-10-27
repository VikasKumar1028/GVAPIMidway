package com.gv.midway.utility;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Detail;
import javax.xml.soap.DetailEntry;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.cxf.binding.soap.SoapFault;
import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.headers.Header.Direction;
import org.apache.cxf.message.MessageContentsList;
import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IEndPoints;
import com.gv.midway.constant.IResponse;
import com.gv.midway.exception.InvalidParameterException;
import com.gv.midway.pojo.job.JobParameter;
import com.gv.midway.pojo.job.JobinitializedResponse;
import com.gv.midway.pojo.transaction.Transaction;
import com.gv.midway.pojo.verizon.DeviceId;

public class CommonUtil {
    //TODO The caught exceptions in this class should probably be caught, logged, and then re-thrown

    private CommonUtil() { }

    private static final Logger LOGGER = Logger.getLogger(CommonUtil.class);

    public static List<String> endPointList = new ArrayList<>();

    public static AtomicBoolean isTokenRequired = new AtomicBoolean();

    public static AtomicBoolean isAlreadyInTokenGeneration = new AtomicBoolean();

    static {
        endPointList.add(IEndPoints.ACTIVATION_ENDPOINT);
        endPointList.add(IEndPoints.DEACTIVATION_ENDPOINT);
        endPointList.add(IEndPoints.RESTORE_ENDPOINT);
        endPointList.add(IEndPoints.SUSPENSION_ENDPOINT);

        endPointList.add(IEndPoints.ACTIVATION_SEDA_KORE_ENDPOINT);
        endPointList.add(IEndPoints.DEACTIVATION_SEDA_KORE_ENDPOINT);
        endPointList.add(IEndPoints.RESTORE_SEDA_KORE_ENDPOINT);
        endPointList.add(IEndPoints.SUSPENSION_SEDA_KORE_ENDPOINT);
        endPointList.add(IEndPoints.REACTIVATION_SEDA_KORE_ENDPOINT);

        endPointList.add(IEndPoints.ACTIVATION_SEDA_ATTJASPER_ENDPOINT);
        endPointList.add(IEndPoints.DEACTIVATION_SEDA_ATTJASPER_ENDPOINT);
        endPointList.add(IEndPoints.CHANGE_SERVICEPLAN_SEDA_ATTJASPER_ENDPOINT);
        endPointList.add(IEndPoints.REACTIVATION_SEDA_ATTJASPER_ENDPOINT);
        endPointList.add(IEndPoints.CHANGE_CUSTOMFIELD_SEDA_ATTJASPER_ENDPOINT);
        endPointList.add(IEndPoints.SUSPENSION_SEDA_ATTJASPER_ENDPOINT);
        endPointList.add(IEndPoints.RESTORE_SEDA_ATTJASPER_ENDPOINT);

        endPointList.add(IEndPoints.CHANGE_CUSTOMFIELD_ENDPOINT);
        endPointList.add(IEndPoints.CHANGE_SERVICEPLAN_ENDPOINT);
        endPointList.add(IEndPoints.CHANGE_SERVICEPLAN_SEDA_KORE_ENDPOINT);
        endPointList.add(IEndPoints.CHANGE_CUSTOMFIELD_SEDA_KORE_ENDPOINT);
    }

    /**
     * Get Current date
     * 
     * @return the current date and time in 'yyyy/MM/dd HH:mm:ss' format
     */
    public static String getCurrentTimeStamp() {
        final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateFormat.format(new Date());
    }

    /**
     * Get the IP address of Machine
     * 
     * @return The current IP Address
     */
    public static String getIpAddress() {
        InetAddress ip;

        try {
            ip = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            LOGGER.error("Exception ex: " + e);
            return "Default_IP";
        }

        return ip.toString();
    }

    /**
     * Generate Midway Transaction Id
     * 
     * @return Current time in millis as a String
     */
    public static String getMidwayTransactionID() {
        long timestamp = System.currentTimeMillis();
        return Long.toString(timestamp);
    }

    /**
     * Method to get the carrier Name from the input
     * 
     * @param carrierName Carrier's name
     * @return Standardized carrier name
     */
    public static String getDerivedCarrierName(String carrierName) throws InvalidParameterException {

        if (carrierName.equalsIgnoreCase(IConstant.BSCARRIER_SERVICE_VERIZON)) {
            return IConstant.BSCARRIER_SERVICE_VERIZON;
        } else if (carrierName.equalsIgnoreCase(IConstant.BSCARRIER_SERVICE_KORE)) {
            return IConstant.BSCARRIER_SERVICE_KORE;
        } else if (carrierName.equalsIgnoreCase(IConstant.BSCARRIER_SERVICE_ATTJASPER)) {
            return IConstant.BSCARRIER_SERVICE_ATTJASPER;
        }
        throw new InvalidParameterException("402", "Unsupported bsCarrier field value.  Must pass [Verizon, Kore, or AttJasper].");
    }

    /**
     * Method to check if the end point is Provisioning Request
     * 
     * @param endPoint Endpoint to check
     * @return true if provisioning method, false otherwise
     */
    public static boolean isProvisioningMethod(String endPoint) {

        LOGGER.info("endpoint is......." + endPoint);

        for (String element : endPointList) {
            if (endPoint.contains(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returning the recommended device Identifier such as ESN/MEID/ICCID for
     * Device Connection and Device Usage Batch Jobs
     */
    public static DeviceId getRecommendedDeviceIdentifier(DeviceId[] devices) {

        LOGGER.info("getRecommendedDeviceIdentifier........deviceId..is.......");

        for (DeviceId device : devices) {
            if (("ESN".equalsIgnoreCase(device.getKind()))
                    || ("MEID".equalsIgnoreCase(device.getKind()))
                    || ("ICCID".equalsIgnoreCase(device.getKind()))) {

                return device;
            }
        }

        return devices[0];
    }

    /**
     * Returning the Sim Number from the DeviceId Array Device Usage Batch Jobs
     * 
     * @param devices devices to check
     * @return the first DeviceId that has a kind of 'SIM'
     */
    public static DeviceId getSimNumber(DeviceId[] devices) {

        LOGGER.info("getSimNumber........deviceId..is.......");

        for (DeviceId device : devices) {
            if ("SIM".equalsIgnoreCase(device.getKind())) {
                return device;
            }
        }

        return null;
    }

    /**
     * Validate date
     * 
     * @param date date to check
     * @return boolean indicating whether or not 'date' matches the specified format:
     */
    public static boolean isValidDateFormat(String date) {

        if (date == null) {
            LOGGER.info("date is null.....");
            return false;
        }

        final String trimDate = date.trim();

        final String exp = "^([\\d]{4})[-]?(0?[1-9]|1[012])[-]?(0?[1-9]|[12][0-9]|3[01])";

        final Pattern pattern = Pattern.compile(exp, Pattern.CASE_INSENSITIVE);

        final Matcher matcher = pattern.matcher(trimDate);

        if (matcher.matches()) {
            LOGGER.info("valid date format for....." + trimDate);
            return true;
        } else {
            LOGGER.info("invalid date format for....." + trimDate);
            return false;
        }
    }

    /**
     * Validate Job Parameter for Device Usage
     */
    public static JobinitializedResponse validateJobParameterForDeviceUsage(JobParameter jobParameter) {

        final JobinitializedResponse jobinitializedResponse = new JobinitializedResponse();

        if (jobParameter == null) {
            jobinitializedResponse.setMessage("Please provide job parameters");
            return jobinitializedResponse;
        }

        final String carrierName = jobParameter.getCarrierName();

        if (carrierName == null) {
            jobinitializedResponse.setMessage("Please provide Carrier Name. Allowable Values are KORE and VERIZON");
            return jobinitializedResponse;
        }

		if (!(IConstant.BSCARRIER_SERVICE_KORE.equals(carrierName)
				|| IConstant.BSCARRIER_SERVICE_VERIZON.equals(carrierName) )) {
            jobinitializedResponse.setMessage("Please provide valid Carrier Name. Allowable Values are KORE and VERIZON");
            return jobinitializedResponse;
        }

        final String date = jobParameter.getDate();

        if (!isValidDateFormat(date)) {
            jobinitializedResponse.setMessage(IResponse.ERROR_DESCRIPTION_DATE_VALIDATE_JOB_MIDWAYDB);
            return jobinitializedResponse;
        }
        return null;
    }

    /**
     * Validate Job Parameter for Device Connection
     */
    public static JobinitializedResponse validateJobParameterForDeviceConnection(JobParameter jobParameter) {

        final JobinitializedResponse jobinitializedResponse = new JobinitializedResponse();

        if (jobParameter == null) {
            jobinitializedResponse.setMessage("Please provide job parameters");
            return jobinitializedResponse;
        }

        final String carrierName = jobParameter.getCarrierName();

        if (carrierName == null) {
            jobinitializedResponse.setMessage("Please provide Carrier Name. Allowable Value is VERIZON");
            return jobinitializedResponse;
        }

        if (!(IConstant.BSCARRIER_SERVICE_VERIZON.equals(carrierName))) {
            jobinitializedResponse.setMessage("Please provide valid Carrier Name. Allowable Value is VERIZON");
            return jobinitializedResponse;
        }

        final String date = jobParameter.getDate();

        if (!isValidDateFormat(date)) {
            jobinitializedResponse.setMessage(IResponse.ERROR_DESCRIPTION_DATE_VALIDATE_JOB_MIDWAYDB);
            return jobinitializedResponse;
        }
        return null;
    }

    /**
     * Method takes the Billing Day(eg 10 and Creates the billing Start date yyyy-MM-dd)
     * 
     * @param billingDay The day of the month the customer is billed
     * @param jobDate The date of the job
     * @return The date which the customer will be billed for
     */
    public static String getDeviceBillingStartDate(String billingDay, String jobDate) {
        if (billingDay == null || jobDate == null || !billingDay.matches("\\d{1,2}") || !jobDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return null;
        } else {
            return getDeviceBillingStartDate(
                    Integer.parseInt(billingDay)
                    , LocalDate.parse(jobDate, DateTimeFormat.forPattern("yyyy-MM-dd"))
            ).toString("yyyy-MM-dd");
        }
    }

    public static org.joda.time.LocalDate getDeviceBillingStartDate(int billingDay, org.joda.time.LocalDate jobDate) {

        if (billingDay > jobDate.getDayOfMonth()) {
            final org.joda.time.LocalDate previousMonth = jobDate.minusMonths(1);
            final int lastDayOfPreviousMonth = previousMonth.dayOfMonth().withMaximumValue().getDayOfMonth();

            final int calculatedDay = previousMonth.getDayOfMonth();

            if ((calculatedDay < lastDayOfPreviousMonth) && (billingDay < lastDayOfPreviousMonth)) {
                return previousMonth.withDayOfMonth(billingDay);
            } else {
                return previousMonth;
            }
        } else {
            return jobDate.withDayOfMonth(billingDay);
        }
    }

    /**
     * Setting Message Header Values in exchange
     */
    public static Message setMessageHeader(Exchange exchange) {
        final Message message = exchange.getIn();
        String sessionToken = "";
        String authorizationToken = "";

        if (exchange.getProperty(IConstant.VZ_SEESION_TOKEN) != null && exchange.getProperty(IConstant.VZ_AUTHORIZATION_TOKEN) != null) {
            sessionToken = exchange.getProperty(IConstant.VZ_SEESION_TOKEN).toString();
            authorizationToken = exchange.getProperty(IConstant.VZ_AUTHORIZATION_TOKEN).toString();
        }

        message.setHeader("VZ-M2M-Token", sessionToken);
        message.setHeader("Authorization", "Bearer " + authorizationToken);
        message.setHeader(Exchange.CONTENT_TYPE, "application/json");
        message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
        message.setHeader(Exchange.HTTP_METHOD, "POST");
        return message;
    }

    public static List<SoapHeader> getSOAPHeaders(String username, String password) {

        final String soapHeader = "<wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" "
                + "soap:mustUnderstand=\"true\" xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\">"
                + "<wsse:UsernameToken xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\" wsu:Id=\"UsernameToken-16847597\">"
                + "<wsse:Username>"
                + username
                + "</wsse:Username>"
                + "<wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText\">"
                + password
                + "</wsse:Password>"
                + "</wsse:UsernameToken></wsse:Security>";

        final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        Element element = null;
        try {
            db = dbf.newDocumentBuilder();
            final InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(soapHeader));
            try {
                Document doc = db.parse(is);
                element = doc.getDocumentElement();

            } catch (SAXException | IOException e) {
                LOGGER.error(e);
            }
        } catch (ParserConfigurationException e1) {
            LOGGER.error(e1);
        }

        final List<SoapHeader> soapHeaders = new ArrayList<>();

        try {
            final SoapHeader newHeader = new SoapHeader(new QName("soapHeader"), element);
            newHeader.setDirection(Direction.DIRECTION_OUT);
            soapHeaders.add(newHeader);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return soapHeaders;
    }

    public static String getSOAPResponseFromExchange(Exchange exchange) {

        final MessageContentsList result = (MessageContentsList) exchange.getIn().getBody();
        Object object = result.get(0);
        LOGGER.info("Received output text: " + result.get(0));

        JAXBContext context;
        String xmlString = null;
        try {
            context = JAXBContext.newInstance(object.getClass());
            StringWriter sw = new StringWriter();
            Marshaller jaxbMarshaller = context.createMarshaller();
            jaxbMarshaller.marshal(object, sw);
            xmlString = sw.toString();
            LOGGER.info("response body is........" + xmlString);

            exchange.setProperty(IConstant.ATTJASPER_SOAP_RESPONSE_PAYLOAD, xmlString);
        } catch (JAXBException e) {
            LOGGER.error(e);
        }

        return xmlString;
    }

    public static String getSOAPErrorResponseFromExchange(Exchange exchange) {

        String payload = null;

        SoapFault soapFault = (SoapFault) exchange.getProperty(Exchange.EXCEPTION_CAUGHT);

        String message = soapFault.getMessage();

        LOGGER.info("soap fault code    ------------------" + message);

        try {
            SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
            SOAPEnvelope envelope = soapMessage.getSOAPPart().getEnvelope();

            SOAPBody soapBody = soapMessage.getSOAPBody();

            SOAPFault fault = soapBody.addFault();

            fault.setFaultCode(soapFault.getFaultCode());
            fault.setFaultString(soapFault.getMessage());
            Detail detail = fault.addDetail();

            NodeList nodeList = soapFault.getDetail().getChildNodes();

            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);

                String nodeName = node.getNodeName();

                String nodeTextContent = node.getTextContent();

                LOGGER.info("-----*************--Node----Name------" + nodeName);
                LOGGER.info("-----*************--Node-------Text-----" + nodeTextContent);

                Name entryName = envelope.createName(nodeName);

                DetailEntry entry = detail.addDetailEntry(entryName);
                entry.addTextNode(nodeTextContent);

                if (nodeName.contains(":error")) {
                    exchange.setProperty(IConstant.ATTJASPER_SOAP_FAULT_ERRORMESSAGE, nodeTextContent);
                }
            }

            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            soapMessage.writeTo(outStream);
            payload = new String(outStream.toByteArray(), StandardCharsets.UTF_8);

            exchange.setProperty(IConstant.ATTJASPER_SOAP_FAULT_PAYLOAD, payload);

        } catch (SOAPException | IOException e) {
            LOGGER.error(e);
        }

        return payload;
    }

    public static String convertSOAPFaulttoString(Node node) {
        try {
            if (node == null) {
                return null;
            }

            TransformerFactory trf = TransformerFactory.newInstance();
            Transformer tf = trf.newTransformer();

            StringWriter sw = new StringWriter();
            StreamResult result = new StreamResult(sw);

            // transform
            tf.transform(new DOMSource(node), result);

            return sw.getBuffer().toString();
        } catch (Exception e) {
            throw new RuntimeException("Could not convert Node to string", e);
        }
    }

    /**
     * Get the Exception stack trace in log file
     */
    public static String getStackTrace(Exception e) {
        final StringWriter stack = new StringWriter();
        e.printStackTrace(new PrintWriter(stack));

        return stack.toString();
    }

    public static void setTokenGenerationRequired() {
        if (!isTokenRequired.get()) {
            isTokenRequired.set(true);
            isAlreadyInTokenGeneration.set(true);
        }
    }

    public static AtomicBoolean getTokenRequired() {
        return isTokenRequired;
    }

    public static AtomicBoolean isAlreadyinTokenGeneration() {
        return isAlreadyInTokenGeneration;
    }

    public static void setAlreadyInTokenGeneration(boolean isAlreadyInTokenGeneration) {
        CommonUtil.isAlreadyInTokenGeneration.set(isAlreadyInTokenGeneration);
    }

    public static void setTokenRequired(boolean isTokenRequired) {
        CommonUtil.isTokenRequired.set(isTokenRequired);
    }

    /*
     * For Kore and ATTJasper We Need Wire Tap and SEDA component So the body
     * should be set with array list of transaction for Verizon we simply add
     * into database and do not change the exchange body
     */
    public static void setListInWireTap(Exchange exchange, List<Transaction> list) {

        final String carrierName = exchange.getProperty(IConstant.MIDWAY_DERIVED_CARRIER_NAME).toString();

        if (!IConstant.BSCARRIER_SERVICE_VERIZON.equals(carrierName)) {
            exchange.getIn().setBody(list);
        }
    }

    public static int getAttJasperCustomField(String customeField) {

        switch (customeField) {
            case "CustomField1":
                return 17;
            case "CustomField2":
                return 18;
            case "CustomField3":
                return 19;
            case "CustomField4":
                return 73;
            case "CustomField5":
                return 74;
            case "CustomField6":
                return 75;
            case "CustomField7":
                return 76;
            case "CustomField8":
                return 77;
            case "CustomField9":
                return 78;
            case "CustomField10":
                return 79;
            default:
                return -1;
        }
    }

    public static String toJsonString(Object objectWithJsonSerializeAnnotation) throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(objectWithJsonSerializeAnnotation);
    }
    
    public static boolean checkKoreJobScheduling(int jobDuration)
    { 
		Calendar cal = Calendar.getInstance();
		int date = cal.getTime().getDate();

		switch (date) {
		
		 case 24:
            
			return false;
			
		case 25:

			if(jobDuration==IConstant.DURATION_24)
			return true;
			
			else
			 return false;
			
		case 26:

			if(jobDuration!=IConstant.DURATION_72)
				return true;
				
			else
				 return false;

		default:
			break;
		}

		return true;
    }
}