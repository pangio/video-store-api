package com.pangio.rental.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pangio.rental.api.domain.Video;

/**
 * {@link VideoRepository} is where all the {@link Video}s will be stored. Also
 * see {@link JpaRepository}
 * 
 * @author pangio
 */
@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

	/**
	 * Finds a {@link Video} by id.
	 * 
	 * @param id
	 *            of the requested magazine.
	 * @return the magazine.
	 */
	Video findById(Long id);

	/**
	 * Finds a {@link Video} by name.
	 * 
	 * @param name
	 *            of the requested magazine.
	 * @return the magazine.
	 */
	Video findByName(String name);

}
