package com.pangio.rental.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pangio.rental.api.domain.FilmType;

/**
 * {@link FilmTypeRepository} is where all the {@link FilmType}s will be stored.
 * Also see {@link JpaRepository}
 * 
 * @author pangio
 */
@Repository
public interface FilmTypeRepository extends JpaRepository<FilmType, Long> {

	/**
	 * Finds a {@link FilmType} by id.
	 * 
	 * @param id
	 *            of the requested FilmType.
	 * @return the FilmType.
	 */
	FilmType findById(Long id);

	/**
	 * Finds a {@link FilmType} by description.
	 * 
	 * @param description
	 *            of the requested FilmType.
	 * @return the FilmType.
	 */
	FilmType findByDescription(String description);

}
