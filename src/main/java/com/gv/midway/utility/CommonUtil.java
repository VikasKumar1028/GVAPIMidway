package com.gv.midway.utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {


	
	public static String getCurrentTimeStamp()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();

		return dateFormat.format(date);
		
	}
	
	
}
