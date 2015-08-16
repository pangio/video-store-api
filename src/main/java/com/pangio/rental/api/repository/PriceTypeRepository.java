package com.pangio.rental.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pangio.rental.api.domain.PriceType;

/**
 * {@link PriceTypeRepository} is where all the {@link PriceType}s will be
 * stored. Also see {@link JpaRepository}
 * 
 * @author pangio
 */
@Repository
public interface PriceTypeRepository extends JpaRepository<PriceType, Long> {

	/**
	 * Finds a {@link PriceType} by id.
	 * 
	 * @param id
	 *            of the requested PriceType.
	 * @return the PriceType.
	 */
	PriceType findById(Long id);

	/**
	 * Finds a {@link PriceType} by description.
	 * 
	 * @param description
	 *            of the requested PriceType.
	 * @return the PriceType.
	 */
	PriceType findByDescription(String description);

}
