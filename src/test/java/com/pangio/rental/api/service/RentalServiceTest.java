package com.pangio.rental.api.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.pangio.rental.api.domain.Associate;
import com.pangio.rental.api.domain.FilmType;
import com.pangio.rental.api.domain.PriceType;
import com.pangio.rental.api.domain.Rental;
import com.pangio.rental.api.domain.Video;
import com.pangio.rental.api.repository.RentalRepository;

/**
 * {@link RentalServiceTest} guarantees the proper behavior of the
 * {@link RentalService} and its methods.
 * 
 * @author pangio
 */
public class RentalServiceTest {

	private RentalRepository rentalRepositoryMock;
	private AssociateService associateServiceMock;
	private VideoService videoServiceMock;
	private RentalService rentalService;
	private Associate associateMock;
	private Video videoMock;
	private Video videoNoStock;
	private Video unavailableVideoMock;
	private FilmType filmMock;
	private PriceType priceMock;

	@Before
	public void setUp() {
		videoMock = mock(Video.class);
		unavailableVideoMock = mock(Video.class);
		filmMock = mock(FilmType.class);
		priceMock = mock(PriceType.class);
		associateMock= mock(Associate.class);

		rentalRepositoryMock = mock(RentalRepository.class);
		associateServiceMock = mock(AssociateService.class);
		videoServiceMock = mock(VideoService.class);

		when(associateServiceMock.findById(any(Long.class))).thenReturn(
				associateMock);
		when(videoServiceMock.findById(4L)).thenReturn(videoMock);
		when(videoServiceMock.isStockAvailable(videoMock)).thenReturn(true);
		when(videoServiceMock.findById(76L)).thenReturn(videoNoStock);
		when(videoServiceMock.isEnabled(videoMock)).thenReturn(true);
		when(videoServiceMock.isEnabled(unavailableVideoMock))
				.thenReturn(false);

		when(videoServiceMock.findById(23L)).thenReturn(unavailableVideoMock);
		when(unavailableVideoMock.isEnabled()).thenReturn(false);

		when(filmMock.getBonusPoints()).thenReturn(2);
		when(filmMock.getPriceType()).thenReturn(priceMock);
		when(videoMock.getFilmType()).thenReturn(filmMock);

		rentalService = new RentalService(rentalRepositoryMock,
				associateServiceMock, videoServiceMock);
	}

	@Test
	public void testRentVideo() {
		// check the rental was saved
		rentalService.rent(associateMock, videoMock, 2);
		verify(rentalRepositoryMock, times(1)).save(any(Rental.class));
		verify(rentalRepositoryMock).findByAssociateAndVideoAndReturned(associateMock, videoMock, false);
		verifyNoMoreInteractions(rentalRepositoryMock);
	}

	@Test
	public void testTryToRentVideoButNoStock() {
		// check the rental was not saved
		rentalService.rent(associateMock, videoNoStock, 2);
		verifyNoMoreInteractions(rentalRepositoryMock);
	}

	@Test
	public void testTryToRentUnavailableVideo() {
		// check the rental was not saved
		rentalService.rent(associateMock, unavailableVideoMock, 2);
		verifyNoMoreInteractions(rentalRepositoryMock);
	}

	@Test
	public void testReturnVideoToRental() {
		when(
				rentalRepositoryMock.findByAssociateAndVideoAndReturned(
						associateMock, videoMock, false)).thenReturn(new Rental());
		// rent and return
		rentalService.rent(associateMock, videoMock, 2);
		rentalService.returnVideoToRental(associateMock, videoMock);
		verify(rentalRepositoryMock).save(any(Rental.class));
	}

}