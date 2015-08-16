package com.pangio.rental.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pangio.rental.api.domain.PriceType;
import com.pangio.rental.api.repository.PriceTypeRepository;

/**
 * Service Layer.
 * 
 * @author pangio
 */
@Component
public class PriceTypeService {

	@Autowired
	PriceTypeRepository repository;

	/** Constructors */
	public PriceTypeService() {
	}

	public PriceTypeService(PriceTypeRepository repository) {
		this.repository = repository;
	}

	/**
	 * Finds a {@link PriceType} by id
	 * 
	 * @param id
	 *            of the requested PriceType
	 * @return the PriceType
	 */
	public PriceType findById(Long id) {
		return this.repository.findById(id);
	}

	/**
	 * Provides a list of all the {@link PriceType}s
	 * 
	 * @return the list of PriceType
	 */
	public List<PriceType> list() {
		return this.repository.findAll();
	}

}
