package com.pangio.rental.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pangio.rental.api.domain.Associate;
import com.pangio.rental.api.repository.AssociateRepository;

/**
 * Service Layer.
 * 
 * @author pangio
 */
@Component
public class AssociateService {

	@Autowired
	AssociateRepository repository;

	/** Constructors */
	public AssociateService() {
	}

	/**
	 * Finds an associate by id
	 * 
	 * @param associateId
	 * @return the associate
	 */
	public Associate findById(Long associateId) {
		return this.repository.findById(associateId);
	}

	/**
	 * Save the associate
	 * 
	 * @param associate
	 * @return the associate
	 */
	public Associate save(Associate associate) {
		return this.repository.save(associate);
	}

}
