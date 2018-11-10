package com.stock.analysis.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import com.stock.anaysis.common.CommonConstants;

public class TimeUtils {

	public static String convertToIndianTime(LocalDateTime time){
		Date date = Date.from(time.atZone(ZoneId.systemDefault())
	      .toInstant());
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.add(Calendar.HOUR, CommonConstants.HOUR_BUFFER);
	    calendar.add(Calendar.MINUTE, CommonConstants.MINUTE_BUFFER);
	    return calendar.getTime().toString();
	}
}
