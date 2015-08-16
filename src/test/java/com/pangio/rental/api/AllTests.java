package com.pangio.rental.api;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.pangio.rental.api.domain.RentalTest;
import com.pangio.rental.api.domain.VideoTest;
import com.pangio.rental.api.service.RentalServiceTest;
import com.pangio.rental.api.service.VideoServiceTest;
import com.pangio.rental.api.utils.DateUtilsTest;

/**
 * Test Suite. Includes some unit tests for the Video Rental API. More should be
 * added.
 * 
 * @author pangio
 */
@RunWith(Suite.class)
@SuiteClasses({ 
	VideoServiceTest.class, 
	RentalServiceTest.class,
	VideoTest.class, 
	RentalTest.class, 
	DateUtilsTest.class })
public class AllTests {

}
