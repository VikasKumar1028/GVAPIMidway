package com.gv.midway.utility;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.camel.Exchange;
import org.apache.camel.Message;

import org.apache.log4j.Logger;

import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IEndPoints;
import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.job.JobParameter;
import com.gv.midway.pojo.job.JobinitializedResponse;
import com.gv.midway.pojo.verizon.DeviceId;

public class CommonUtil {

    private static final Logger LOGGER = Logger.getLogger(CommonUtil.class);

    public static List<String> endPointList = new ArrayList<String>();

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

}
