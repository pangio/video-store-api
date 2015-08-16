package com.pangio.rental.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pangio.rental.api.domain.FilmType;
import com.pangio.rental.api.repository.FilmTypeRepository;

/**
 * Service Layer.
 * 
 * @author pangio
 */
@Component
public class FilmTypeService {

	@Autowired
	FilmTypeRepository repository;

	/** Constructors */
	public FilmTypeService() {
	}

	public FilmTypeService(FilmTypeRepository PriceType) {
		this.repository = PriceType;
	}

	/**
	 * Finds a {@link FilmType} by id
	 * 
	 * @param id
	 *            of the requested FilmType
	 * @return the FilmType
	 */
	public FilmType findById(Long id) {
		return this.repository.findById(id);
	}

	/**
	 * Provides a list of all the {@link FilmType}s
	 * 
	 * @return the list of FilmType
	 */
	public List<FilmType> list() {
		return this.repository.findAll();
	}

}
