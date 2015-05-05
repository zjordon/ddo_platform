/**
 * 
 */
package com.jason.ddoMsg.util;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * @author jasonzhang
 *
 */
public class DateUtil {

	public final static int getDateIntNum(Date date) {
		int dateNum = 0;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		dateNum = cal.get(Calendar.YEAR) * 10000 + (cal.get(Calendar.MONTH) + 1) * 100 + cal.get(Calendar.DATE);
		return dateNum;
	}
	
	public final static int getMonthIntNum(Date date) {
		int monthNum = 0;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		monthNum = cal.get(Calendar.YEAR) * 100 + (cal.get(Calendar.MONTH) + 1);
		return monthNum;
	}
	
	public final static Date parseDate(String dateString) {
		Date date = null;
		try {
			date = DateUtils.parseDate(dateString, "yyyy-MM-dd HH:mm:ss");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	public final static String formatDate(Date date) {
		return DateFormatUtils.format(date, "yyyyMMddHHmmss");
	}
}
