package com.gv.midway.constant;

public class IResponse {

	public static final Integer SUCCESS_CODE=2000;
	public static final Integer NO_DATA_FOUND_CODE=1900;
	public static final Integer DB_ERROR_CODE=1901;
	public static final Integer CONNECTION_ERROR_CODE=1903;
	
	public static final String SUCCESS_MESSAGE="Success";
	public static final String SUCCESS_DESCRIPTION_INSERT_MIDWAYDB="Record succesfully inserted in Midway";
	public static final String SUCCESS_DESCRIPTION_UPDATE_MIDWAYDB="Record succesfully updated in Midway";
	public static final String SUCCESS_DESCRIPTION_DEVCIEINFO_MIDWAYDB="Data Succesfully Found from Midway";
	public static final String SUCCESS_DESCRIPTION_ACTIVATE_MIDWAY="Data Submitted Successfully";
	
	public static final String ERROR_MESSAGE="Error";
	public static final String ERROR_DESCRIPTION_INSERTDEVICE_MIDWAYDB="Failed to insert record in Midway";
	public static final String ERROR_DESCRIPTION_UPDATE_MIDWAYDB="Failed to update record in Midway";
	public static final String ERROR_DESCRIPTION_NODATA_DEVCIEINFO_MIDWAYDB="No data found in Midway";
	public static final String ERROR_DESCRIPTION_NODATA_UPDATEDEVCIE_MIDWAYDB="No record found to Update in Midway";
	public static final String ERROR_DESCRIPTION_EXCEPTION_DEVCIEINFO_MIDWAYDB="Error in fecthing the Device Data from Midway";
	public static final String ERROR_DESCRIPTION_CONNECTION_MIDWAYDB="Error in Connection with Carrier";
	
}
