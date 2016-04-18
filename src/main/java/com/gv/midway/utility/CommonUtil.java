package com.gv.midway.utility;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {

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

	public static String getmidwayTransationId() {
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

		if (endPoint.contains("activate") || endPoint.contains("deactivate")) {

			return true;

		} else

			return false;
	}
}
