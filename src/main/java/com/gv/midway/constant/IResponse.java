package com.gv.midway.constant;

public class IResponse {

	public static final Integer SUCCESS_CODE=2000;
	public static final Integer NO_DATA_FOUND_CODE=1900;
	public static final Integer DB_ERROR_CODE=1901;
	public static final Integer CONNECTION_ERROR_CODE=1903;
	public static final Integer DEVICEINFO_ERROR_CODE_CARRIER=1904;
	public static final Integer INVALID_PAYLOAD=1905;
	
	public static final Integer NETSUITE_CALLBACK_ERRORCODE=1906;
	
	
	
	public static final String SUCCESS_MESSAGE="Success";
	
	public static final String SUCCESS_DESCRIPTION_UPDATE_MIDWAYDB="Record succesfully uploaded in Midway";
	public static final String SUCCESS_DESCRIPTION_DEVCIEINFO_MIDWAYDB="Data Succesfully Found from Midway";

	public static final String SUCCESS_DESCRIPTION_ACTIVATE_MIDWAY="Data Submitted Successfully";
	
	public static final String SUCCESS_DESCRIPTION_DEVICE_USAGE_MIDWAY="Retrieved Device Usage Successfully";

	public static final String SUCCESS_DESCRIPTION_SUSPEND_MIDWAY="Data Submitted Successfully";
	public static final String SUCCESS_DESCRIPTION_CONNECTION_STATUS = "Data Processed Successfully";
	
	public static final String SUCCESS_DESCRIPTION_DEVCIEINFO_CARRIER="Data Succesfully Found from Carrier";
	

	
	public static final String ERROR_MESSAGE="Error";
	
	public static final String ERROR_DESCRIPTION_UPDATE_MIDWAYDB="Failed to upload record in Midway";
	public static final String ERROR_DESCRIPTION_UPDATE_NETSUITE_MIDWAYDB="No NetsuiteId for the record";
	public static final String ERROR_DESCRIPTION_NODATA_DEVCIEINFO_MIDWAYDB="No data found in Midway";
	public static final String ERROR_DESCRIPTION_NODATA_UPDATEDEVCIE_MIDWAYDB="No record found to Update in Midway";
	public static final String ERROR_DESCRIPTION_EXCEPTION_DEVCIEINFO_MIDWAYDB="Error in fecthing the Device Data from Midway";
	public static final String ERROR_DESCRIPTION_CONNECTION_MIDWAYDB="Error in Connection with Carrier";
	
	
}
