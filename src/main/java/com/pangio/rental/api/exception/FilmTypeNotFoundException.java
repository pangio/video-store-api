package com.pangio.rental.api.exception;

import com.pangio.rental.api.domain.FilmType;

/**
 * This exception is thrown when an {@link FilmType}is not found.
 * 
 * @author pangio
 */
public class FilmTypeNotFoundException extends Exception {

	/**
	 * Unique ID for Serialized object
	 */
	private static final long serialVersionUID = 7908872301663406742L;

	/**
	 * Constructor of the exception
	 * 
	 * @param typeId
	 */
	public FilmTypeNotFoundException(Long typeId) {
		super("The Film Type with id " + typeId + " doesn't exist");
	}
}
