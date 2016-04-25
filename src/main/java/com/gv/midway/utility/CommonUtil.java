package com.gv.midway.utility;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.esotericsoftware.minlog.Log;
import com.gv.midway.constant.IEndPoints;
import com.gv.midway.dao.impl.TransactionalDaoImpl;

public class CommonUtil {
	
	private static Logger log = Logger.getLogger(CommonUtil.class);
	
	public static List<String> endPointList=new ArrayList<String>();
	
	static{
		
		endPointList.add(IEndPoints.ACTIVATION_ENDPOINT);
		endPointList.add(IEndPoints.DEACTIVATION_ENDPOINT);
		endPointList.add(IEndPoints.ACTIVATION_SEDA_KORE_ENDPOINT);
	}

	public static String getCurrentTimeStamp() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();

		return dateFormat.format(date);

	}

	public static String getIpAddress()

	{
		InetAddress ip;

		try {

			ip = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			return "Defulat_IP";
		}

		return ip.toString();

	}

	public static String getMidwayTransactionID() {
		long timestamp = System.currentTimeMillis();
		return Long.toString(timestamp);

	}

	public static String getDerivedCarrierName(String carrierName) {

		String sourceDirived = null;

		if (carrierName.startsWith("V")) {

			return sourceDirived = "VERIZON";

		} else if (carrierName.startsWith("K")) {

			return sourceDirived = "KORE";
		}

		return sourceDirived;
	}

	public static boolean isProvisioningMethod(String endPoint) {
		
		log.info("endpoint is......."+endPoint);
		
		/*if(endPointList.contains(endPoint)){
			
			return true;
		}

		return false;*/
		if (endPoint.contains(IEndPoints.ACTIVATION_ENDPOINT) || endPoint.contains(IEndPoints.ACTIVATION_ENDPOINT)|| 
				endPoint.contains(IEndPoints.ACTIVATION_SEDA_KORE_ENDPOINT) || endPoint.contains(IEndPoints.DEACTIVATION_SEDA_KORE_ENDPOINT) || endPoint.contains(IEndPoints.REACTIVATION_SEDA_KORE_ENDPOINT)) {

			return true;

		} else

			return false;
	}
}
