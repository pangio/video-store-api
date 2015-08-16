package com.pangio.rental.api.exception;

import com.pangio.rental.api.domain.Video;

/**
 * This exception is thrown when an {@link Video}is not found.
 * 
 * @author pangio
 */
public class VideoNotFoundException extends Exception {

	/**
	 * Unique ID for Serialized object
	 */
	private static final long serialVersionUID = -4835354659991269703L;

	/**
	 * Constructor of the exception
	 * 
	 * @param videoId
	 */
	public VideoNotFoundException(Long videoId) {
		super("The Video with id " + videoId + " doesn't exist");
	}
}
