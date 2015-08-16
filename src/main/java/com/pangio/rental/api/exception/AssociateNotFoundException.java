package com.pangio.rental.api.exception;

import com.pangio.rental.api.domain.Associate;

/**
 * This exception is thrown when an {@link Associate} is not found.
 * 
 * @author pangio
 */
public class AssociateNotFoundException extends Exception {

	/**
	 * Unique ID for Serialized object
	 */
	private static final long serialVersionUID = -1550375199092092927L;

	/**
	 * Constructor of the exception
	 * 
	 * @param videoId
	 */
	public AssociateNotFoundException(Long associateId) {
		super("The Associate with id " + associateId + " doesn't exist");
	}
}
