package com.pangio.rental.api.utils;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import com.pangio.rental.api.util.DateUtils;

public class DateUtilsTest {

	@SuppressWarnings("deprecation")
	@Test
	public void differenceDaysTest() {

		Date d1 = new Date(2015, 02, 01);
		Date d2 = new Date(2015, 02, 05);

		long differenceDays = DateUtils.getDifferenceDays(d1, d2);
		assertEquals(4, differenceDays);
	}

}
