package com.pangio.rental.api.domain;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for the {@link Rental}.
 * 
 * @author pangio
 */
public class RentalTest {

	private Video videoMock;
	private Associate associateMock;
	private Rental rental;

	@Before
	public void setUp() {
		videoMock = mock(Video.class);
		associateMock = mock(Associate.class);
		rental = new Rental(associateMock, videoMock, 2);

	}

	@Test
	public void canConstructARental() {
		assertEquals(false, rental.isReturned());
		assertEquals(2, rental.getDays().intValue());
	}
}
