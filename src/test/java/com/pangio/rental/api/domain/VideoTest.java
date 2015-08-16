package com.pangio.rental.api.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for the {@link Video}.
 * 
 * @author pangio
 */
public class VideoTest {

	private Video video;

	@Before
	public void setUp() {
		video = new Video("Iron Man", 2, new OldFilm());
	}

	@Test
	public void canConstructAVideo() {
		assertEquals("Iron Man", video.getName());
		assertEquals(OldFilm.class, video.getFilmType().getClass());
		assertEquals(2, video.getTotalStock().intValue());
		assertEquals(2, video.getAvailableStock().intValue());
		assertTrue(video.isEnabled());
	}

	@Test
	public void testDecreaseStock() {
		video.decreaseAvailableStock();
		assertEquals(1, video.getAvailableStock().intValue());
		video.decreaseAvailableStock();
		assertEquals(0, video.getAvailableStock().intValue());
	}

	@Test
	public void testIncreaseStock() {
		video.decreaseAvailableStock();
		video.decreaseAvailableStock();
		assertEquals(0, video.getAvailableStock().intValue());
		video.increaseAvailableStock();
		assertEquals(1, video.getAvailableStock().intValue());
	}
}
