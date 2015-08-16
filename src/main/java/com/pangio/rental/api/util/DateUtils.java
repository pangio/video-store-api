package com.pangio.rental.api.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Utility class used for operations with Dates
 * 
 * @author pangio
 */
public class DateUtils {

	/**
	 * Calculates the difference in days between two dates
	 * 
	 * @param firstDate
	 * @param lastDate
	 * @return difference in days
	 */
	static public Long getDifferenceDays(Date firstDate, Date lastDate) {
		long diff = lastDate.getTime() - firstDate.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

}
