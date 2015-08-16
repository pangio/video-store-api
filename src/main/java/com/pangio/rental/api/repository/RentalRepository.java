package com.pangio.rental.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pangio.rental.api.domain.Associate;
import com.pangio.rental.api.domain.Rental;
import com.pangio.rental.api.domain.Video;

/**
 * {@link RentalRepository} is where all the {@link Rental}s will be stored.
 * Also see {@link JpaRepository}
 * 
 * @author pangio
 */
@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

	/**
	 * Finds a {@link Rental} by id.
	 * 
	 * @param id
	 *            of the requested Rental.
	 * @return the Rental.
	 */
	Rental findById(Long id);

	/**
	 * Finds the video to be returned to the Store and later calculate the extra
	 * fees if there any.
	 * 
	 * @param associate
	 * @param video
	 * @param returned
	 * @return the rental
	 */
	Rental findByAssociateAndVideoAndReturned(Associate associate, Video video,
			boolean returned);

	/**
	 * Returns the {@link Associate} pending rentals to be returned to the
	 * Store.
	 * 
	 * @return list of pending rentals to be returned
	 */
	List<Rental> findByAssociateAndReturned(Associate associate,
			boolean returned);

}
