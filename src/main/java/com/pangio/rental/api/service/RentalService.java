package com.pangio.rental.api.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pangio.rental.api.domain.Associate;
import com.pangio.rental.api.domain.Rental;
import com.pangio.rental.api.domain.Video;
import com.pangio.rental.api.exception.AssociateNotFoundException;
import com.pangio.rental.api.exception.VideoNotFoundException;
import com.pangio.rental.api.repository.RentalRepository;

/**
 * Service Layer.
 * 
 * @author pangio
 */
@Component
public class RentalService {

	@Autowired
	VideoService videoService;

	@Autowired
	RentalRepository repository;

	@Autowired
	AssociateService associateService;

    private static final Logger logger = Logger.getLogger(RentalService.class);

	/** Constructors */
	public RentalService() {
	}

	public RentalService(RentalRepository repository,
			AssociateService associateService, VideoService videoService) {
		this.repository = repository;
		this.associateService = associateService;
		this.videoService = videoService;
	}

	/**
	 * Creates a new rental
	 * 
	 * @param associate
	 * @param video
	 * @param days
	 * @return the fees of the rental
	 * @throws AssociateNotFoundException
	 * @throws VideoNotFoundException
	 */
	public Double rent(Associate associate, Video video, int days) {

		// 1) check for stock
		// 2) update the available stock
		// 3) create and save Rental
		// 4) calculate BonusPoints
		// 5) calculate fees
		// 6) update Associate

		Double fees = new Double(0);

		if (videoService.isEnabled(video) && videoService.isStockAvailable(video) && this.canRent(associate, video)) {

			videoService.decreaseAvailableStock(video);
			Rental rental = new Rental(associate, video, days);
			repository.save(rental);

			associate.addBonusPoints(video.getFilmType().getBonusPoints());
			associate.addRental(rental);
			associateService.save(associate);
			fees = video.getFilmType().getFees(days);

			logger.info("Associate "+associate.getName()+" has rented "+ video.getName() + " for "+days +" days - Fees " +fees);			
		}
		return fees;
	}

	/** 
	 * One Associate can not rent two (or more) copies of same video at the same time.
	 * Check if the customer has already rented the requested video.
	 * 
	 * @param associate
	 * @param video
	 * @return can rent
	 */
	private boolean canRent(Associate associate, Video video) {
		Rental rental = this.repository.findByAssociateAndVideoAndReturned(associate, video, false);
		return (rental == null);
	}

	/**
	 * Returns a {@link Video} to the Store.
	 * 
	 * @param associateId
	 * @param videoId
	 */
	public Double returnVideoToRental(Associate associate, Video video) {

		Rental rental = repository.findByAssociateAndVideoAndReturned(
				associate, video, false);
		Double extraFees = new Double(0);

		if (rental != null) {
			videoService.increaseAvailableStock(video);
			rental.setReturned(true);
			repository.save(rental);
			extraFees = video.getFilmType().getExtrasFees(rental);
			logger.info("Associate "+associate.getName()+" has returned "+ video.getName()+" - Extra Fees " +extraFees);			
		}
		return extraFees;
	}

	/**
	 * Returns the {@link Associate} pending {@link Rental}s to be returned to
	 * the Store.
	 * 
	 * @return list of pending rentals to be returned
	 */
	public List<Rental> pendingToReturn(Associate associate) {
		return repository.findByAssociateAndReturned(associate, false);
	}

}
