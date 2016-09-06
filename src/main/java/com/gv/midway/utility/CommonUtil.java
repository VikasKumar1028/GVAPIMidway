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
import java.util.Iterator;
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

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.cxf.binding.soap.SoapFault;
import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.headers.Header.Direction;
import org.apache.cxf.message.MessageContentsList;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IEndPoints;
import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.job.JobParameter;
import com.gv.midway.pojo.job.JobinitializedResponse;
import com.gv.midway.pojo.verizon.DeviceId;

public class CommonUtil {

    private static final Logger LOGGER = Logger.getLogger(CommonUtil.class);

    public static List<String> endPointList = new ArrayList<String>();
    
    public static AtomicBoolean isTokenRequired = new AtomicBoolean();
    
    public static AtomicBoolean isAlreadyinTokenGeneration=new AtomicBoolean();

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

        endPointList.add(IEndPoints.CHANGE_CUSTOMFIELD_ENDPOINT);
        endPointList.add(IEndPoints.CHANGE_SERVICEPLAN_ENDPOINT);
        endPointList.add(IEndPoints.CHANGE_SERVICEPLAN_SEDA_KORE_ENDPOINT);
        endPointList.add(IEndPoints.CHANGE_CUSTOMFIELD_SEDA_KORE_ENDPOINT);
    }

    /**
     * Get Current date
     * 
     * @return
     */
    public static String getCurrentTimeStamp() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        return dateFormat.format(date);

    }

    /**
     * Get the IP address of Machine
     * 
     * @return
     */
    public static String getIpAddress()

    {
        InetAddress ip;

        try {

            ip = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            LOGGER.error("Exception ex: " + e);
            return "Defulat_IP";
        }

        return ip.toString();

    }

    /**
     * Generate Midway Transaction Id
     * 
     * @return
     */
    public static String getMidwayTransactionID() {
        long timestamp = System.currentTimeMillis();
        return Long.toString(timestamp);

    }

    /**
     * Method to get the carrier Name from the input
     * 
     * @param carrierName
     * @return
     */
    public static String getDerivedCarrierName(String carrierName) {

        if (carrierName.equalsIgnoreCase("VERIZON")) {

            return "VERIZON";

        } else if (carrierName.equalsIgnoreCase("KORE")) {

            return "KORE";
        }
        
        else if (carrierName.equalsIgnoreCase("ATTJASPER")) {

            return "ATTJASPER";
        }

        return null;
    }

    /**
     * Method to check if the end point is Provisioning Request
     * 
     * @param endPoint
     * @return
     */

    public static boolean isProvisioningMethod(String endPoint) {

        LOGGER.info("endpoint is......." + endPoint);

        for (Iterator<String> iterator = endPointList.iterator(); iterator
                .hasNext();) {
            String element = iterator.next();

            if (endPoint.contains(element)) {

                return true;
            }

        }

        return false;

    }

    /**
     * Returning the recommended device Identifier such as ESN/MEID/ICCID for
     * Device Connection and Device Usage Batch Jobs
     * 
     * @param devices
     * @return
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
     * @param devices
     * @return
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
     * @param date
     * @return
     */
    public static boolean isValidDateFormat(String date) {

        if (date == null) {
            LOGGER.info("date is null.....");
            return false;
        }

        String trimDate = date.trim();

        String exp = "^([\\d]{4})[-]?(0?[1-9]|1[012])[-]?(0?[1-9]|[12][0-9]|3[01])";

        Pattern pattern = Pattern.compile(exp, Pattern.CASE_INSENSITIVE);

        Matcher matcher = pattern.matcher(trimDate);

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
     * 
     * @param jobParameter
     * @return
     */
    public static JobinitializedResponse validateJobParameterForDeviceUsage(
            JobParameter jobParameter) {

        JobinitializedResponse jobinitializedResponse = new JobinitializedResponse();

        if (jobParameter == null) {

            jobinitializedResponse.setMessage("Please provide job parameters");

            return jobinitializedResponse;
        }

        String carrierName = jobParameter.getCarrierName();

        if (carrierName == null) {
            jobinitializedResponse
                    .setMessage("Please provide Carrier Name. Allowable Values are KORE and VERIZON");
            return jobinitializedResponse;
        }

        if (!(IConstant.BSCARRIER_SERVICE_KORE.equals(carrierName) || IConstant.BSCARRIER_SERVICE_VERIZON
                .equals(carrierName))) {

            jobinitializedResponse
                    .setMessage("Please provide valid Carrier Name. Allowable Values are KORE and VERIZON");
            return jobinitializedResponse;
        }

        String date = jobParameter.getDate();

        if (!isValidDateFormat(date)) {

            jobinitializedResponse
                    .setMessage(IResponse.ERROR_DESCRIPTION_DATE_VALIDATE_JOB_MIDWAYDB);
            return jobinitializedResponse;
        }
        return null;

    }

    /**
     * Validate Job Parameter for Device Connection
     * 
     * @param jobParameter
     * @return
     */
    public static JobinitializedResponse validateJobParameterForDeviceConnection(
            JobParameter jobParameter) {

        JobinitializedResponse jobinitializedResponse = new JobinitializedResponse();

        if (jobParameter == null) {

            jobinitializedResponse.setMessage("Please provide job parameters");

            return jobinitializedResponse;
        }

        String carrierName = jobParameter.getCarrierName();

        if (carrierName == null) {
            jobinitializedResponse
                    .setMessage("Please provide Carrier Name. Allowable Value is VERIZON");
            return jobinitializedResponse;
        }

        if (!(IConstant.BSCARRIER_SERVICE_VERIZON.equals(carrierName))) {

            jobinitializedResponse
                    .setMessage("Please provide valid Carrier Name. Allowable Value is VERIZON");
            return jobinitializedResponse;
        }

        String date = jobParameter.getDate();

        if (!isValidDateFormat(date)) {

            jobinitializedResponse
                    .setMessage(IResponse.ERROR_DESCRIPTION_DATE_VALIDATE_JOB_MIDWAYDB);
            return jobinitializedResponse;
        }
        return null;

    }

    /**
     * Method takes the Billing Day(eg 10 and Creates the billing Start date
     * yyyy-MM-dd)
     * 
     * @param billDay
     * @param jobDate
     * @return
     */
    public static String getDeviceBillingStartDate(String billingDay,
            String jobDate) {

        String billingStartDate = null;
        String jobDay = jobDate.substring(8);

        // Creating The bill start date
        // billingday One oR two character
        if (billingDay != null && billingDay.length() == 1) {
            String billingDayChanged = "0" + billingDay;
            billingDay = billingDayChanged;
        }

        try {
            if (billingDay != null) {

                // billing day>JobDetail Day
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Calendar cal = Calendar.getInstance();
                cal.setTime(dateFormat.parse(jobDate));

                LOGGER.info(billingDay + "::::::::::::::::::::" + jobDay);
                if (Integer.parseInt(billingDay) > Integer.parseInt(jobDay)) {
                    // previous month data

                    cal.add(Calendar.MONTH, -1);

                    int noOfLastDay = cal
                            .getActualMaximum(Calendar.DAY_OF_MONTH);

                    billingStartDate = dateFormat.format(cal.getTime());

                    LOGGER.info(noOfLastDay + ":::::::::::::::::::::::"
                            + dateFormat.format(cal.getTime()));

                    int calculatedDay = Integer.parseInt(billingStartDate
                            .substring(8));

                    LOGGER.info("CAlculated Day " + calculatedDay);

                    if ((calculatedDay < noOfLastDay)
                            && (Integer.parseInt(billingDay) < noOfLastDay)) {

                        cal.set(Calendar.DAY_OF_MONTH,
                                Integer.parseInt(billingDay));
                        billingStartDate = dateFormat.format(cal.getTime());

                        LOGGER.info("::::::::::::::::" + billingStartDate);
                    }

                } else

                // billing day is <= JobDetail Day
                if (Integer.parseInt(billingDay) <= Integer.parseInt(jobDay)) {

                    // current month of data
                    cal.set(Calendar.DATE, Integer.parseInt(billingDay));
                    billingStartDate = dateFormat.format(cal.getTime());

                }
                LOGGER.info("::::::::::::::::" + billingStartDate);

            }

        } catch (Exception ex) {
            LOGGER.error("................Error in Setting Job Dates" + ex);
        }

        return billingStartDate;
    }

    /**
     * Setting Message Header Values in exchange
     * 
     */

    public static Message setMessageHeader(Exchange exchange) {
        Message message = exchange.getIn();
        String sessionToken = "";
        String authorizationToken = "";

        if (exchange.getProperty(IConstant.VZ_SEESION_TOKEN) != null
                && exchange.getProperty(IConstant.VZ_AUTHORIZATION_TOKEN) != null) {
            sessionToken = exchange.getProperty(IConstant.VZ_SEESION_TOKEN)
                    .toString();
            authorizationToken = exchange.getProperty(
                    IConstant.VZ_AUTHORIZATION_TOKEN).toString();
        }

        message.setHeader("VZ-M2M-Token", sessionToken);
        message.setHeader("Authorization", "Bearer " + authorizationToken);
        message.setHeader(Exchange.CONTENT_TYPE, "application/json");
        message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
        message.setHeader(Exchange.HTTP_METHOD, "POST");
        return message;
    }
    
    /**
     * 
     * @param exchange
     * @return
     */
    
    public static List<SoapHeader> getSOAPHeaders(String username,String password) 
    {
    	final String soapHeader = "<wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" "
				+ "soap:mustUnderstand=\"true\" xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\">"
				+ "<wsse:UsernameToken xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\" wsu:Id=\"UsernameToken-16847597\">"
				+ "<wsse:Username>"+username+"</wsse:Username>"
				+ "<wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText\">"+password+"</wsse:Password>"
				+ "</wsse:UsernameToken></wsse:Security>";

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		Element element = null;
		try {
			db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(soapHeader));
			try {
				Document doc = db.parse(is);
				element = doc.getDocumentElement();

			} catch (SAXException e) {
				// handle SAXException
			} catch (IOException e) {
				// handle IOException
			}
		} catch (ParserConfigurationException e1) {
			// handle ParserConfigurationException
		}

		List<SoapHeader> soapHeaders = new ArrayList<SoapHeader>();

		try {

			SoapHeader newHeader = new SoapHeader(new QName("soapHeader"),
					element);

			newHeader.setDirection(Direction.DIRECTION_OUT);

			soapHeaders.add(newHeader);


		} catch (Exception e) {

			// log error

		}
        return soapHeaders;
    }
    
    public static String getSOAPResposneFromExchange(Exchange exchange)
    {
    	
    	MessageContentsList result = (MessageContentsList)exchange.getIn().getBody();
    	Object object=result.get(0);
        LOGGER.info("Received output text: " + result.get(0));
        
        JAXBContext context;
        String xmlString=null;
		try {
			context = JAXBContext.newInstance(object.getClass());
			 StringWriter sw = new StringWriter();
		     Marshaller jaxbMarshaller = context.createMarshaller();
		     jaxbMarshaller.marshal(object, sw);
		     xmlString = sw.toString();
		     LOGGER.info("resposne body is........"+xmlString);
		     
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return xmlString;
    }
    
    
	public static String getSOAPErrorResposneFromExchange(Exchange exchange) {

		String payload = null;

		SoapFault soapFault = (SoapFault) exchange
				.getProperty(Exchange.EXCEPTION_CAUGHT);

		String message = soapFault.getMessage();

		LOGGER.info("soap fault code    ------------------" + message);

		try {
			SOAPMessage soapMessage = MessageFactory.newInstance()
					.createMessage();
			SOAPEnvelope envelope = soapMessage.getSOAPPart().getEnvelope();

			SOAPBody soapBody = soapMessage.getSOAPBody();

			SOAPFault fault = soapBody.addFault();

			fault.setFaultCode(soapFault.getFaultCode());
			fault.setFaultString(soapFault.getMessage());
			Detail detail = fault.addDetail();

			NodeList nodeList = soapFault.getDetail().getChildNodes();

			String errorDetails = "";
			for (int i = 0; i < nodeList.getLength(); i++) {

				Node node = nodeList.item(i);

				String nodeName = node.getNodeName();

				String nodeTextContent = node.getTextContent();

				LOGGER.info("-----*************--Node----Name------" + nodeName);
				LOGGER.info("-----*************--Node-------Text-----"
						+ nodeTextContent);

				Name entryName = envelope.createName(nodeName);

				DetailEntry entry = detail.addDetailEntry(entryName);
				entry.addTextNode(nodeTextContent);

				if (nodeName.contains(":error")) {

					errorDetails = nodeTextContent;

					exchange.setProperty(
							IConstant.ATTJASPER_SOAP_FAULT_ERRORMESSAGE,
							nodeTextContent);
				}

			}

			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			soapMessage.writeTo(outStream);
			payload = new String(outStream.toByteArray(),
					StandardCharsets.UTF_8);

		} catch (SOAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return payload;
	}
 
    public static final String convertSOAPFaulttoString(Node node) {
        try {
            if (node == null) {
                return null;
            }

            TransformerFactory trf=TransformerFactory.newInstance();
            Transformer tf=trf.newTransformer();
          
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

    public static String getStackTrace(Exception e){
    	
    	StringWriter stack = new StringWriter();
    	e.printStackTrace(new PrintWriter(stack));
    	
    	return stack.toString();
    	
    }
    
    public static void setTokenGenerationRequired()
    {
     
    	if(isTokenRequired.get()==false){
    		
    		isTokenRequired.set(true);
    		isAlreadyinTokenGeneration.set(true);
    	}
    	
    	
    }
    
    public static AtomicBoolean getTokenRequired()
    {
     

      return isTokenRequired;
    	
    	
    }
    
    public static AtomicBoolean isAlreadyinTokenGeneration()
    {
     

      return isAlreadyinTokenGeneration;
    	
    	
    }
    
    public static void setAlreadyInTokenGeneration(boolean isAlreadyInTokenGeneration)
    {
     

    	isAlreadyinTokenGeneration.set(isAlreadyInTokenGeneration);
    	
    	
    }
    
    public static void setTokenRequired(boolean isTokenRequired)
    {
     
    	CommonUtil.isTokenRequired.set(isTokenRequired);
    	
    	
    }
}
