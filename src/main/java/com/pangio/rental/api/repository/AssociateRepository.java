package com.pangio.rental.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pangio.rental.api.domain.Associate;
import com.pangio.rental.api.domain.Rental;

/**
 * {@link AssociateRepository} is where all the {@link Associate}s will be
 * stored. Also see {@link JpaRepository}
 * 
 * @author pangio
 */
@Repository
public interface AssociateRepository extends JpaRepository<Associate, Long> {

	/**
	 * Finds a {@link Associate} by id.
	 * 
	 * @param id
	 *            of the requested Associate.
	 * @return the Associate.
	 */
	Associate findById(Long id);

	/**
	 * Finds a {@link Rental} by name.
	 * 
	 * @param name
	 *            of the requested Associate.
	 * @return the Associate.
	 */
	Associate findByName(String name);

}
