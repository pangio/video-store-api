package com.pangio.rental.api.exception;

import com.pangio.rental.api.domain.PriceType;

/**
 * This exception is thrown when an {@link PriceType}is not found.
 * 
 * @author pangio
 */
public class PriceTypeNotFoundException extends Exception {

	/**
	 * Unique ID for Serialized object
	 */
	private static final long serialVersionUID = -9123992039204402780L;

	/**
	 * Constructor of the exception
	 * 
	 * @param priceId
	 */
	public PriceTypeNotFoundException(Long priceId) {
		super("The Price Type with id " + priceId + " doesn't exist");
	}
}
