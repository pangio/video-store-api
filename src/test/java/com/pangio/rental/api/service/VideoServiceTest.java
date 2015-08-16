package com.pangio.rental.api.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.pangio.rental.api.domain.OldFilm;
import com.pangio.rental.api.domain.Video;
import com.pangio.rental.api.repository.VideoRepository;

/**
 * {@link VideoServiceTest} guarantees the proper behavior of the
 * {@link VideoService} and its methods.
 * 
 * @author pangio
 */
public class VideoServiceTest {

	private VideoRepository videoRepositoryMock;
	private VideoService videoService;
	private Video video;

	@Before
	public void setUp() {
		video = new Video("Terminator", 2, new OldFilm());
		videoRepositoryMock = mock(VideoRepository.class);
		videoService = new VideoService(videoRepositoryMock);
	}

	@Test
	public void testCreateNewVideo() {
		when(videoRepositoryMock.save(any(Video.class))).thenReturn(video);
		videoService.create(new Video());
		verify(videoRepositoryMock).save(any(Video.class));
		verifyNoMoreInteractions(videoRepositoryMock);
	}

	@Test
	public void testIncreaseStock() {
		videoService.increaseAvailableStock(video);
		assertEquals(new Integer(3), video.getAvailableStock());
	}

	@Test
	public void testDecreaseStock() {
		videoService.decreaseAvailableStock(video);
		assertEquals(new Integer(1), video.getAvailableStock());
	}

	@Test
	public void testAvailableStock() {
		videoService.isStockAvailable(video);
		assertTrue(videoService.isStockAvailable(video));
	}

	@Test
	public void testFindById() {
		when(videoRepositoryMock.findById(any(Long.class))).thenReturn(video);
		Video savedVideo = videoService.findById(1L);
		assertEquals(video.getId(), savedVideo.getId());
	}

}