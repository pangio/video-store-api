package com.pangio.rental.api.controller.advice;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.pangio.rental.api.exception.AssociateNotFoundException;
import com.pangio.rental.api.exception.FilmTypeNotFoundException;
import com.pangio.rental.api.exception.PriceTypeNotFoundException;
import com.pangio.rental.api.exception.VideoNotFoundException;

/**
 * Exception handlers
 * 
 * @author pangio
 */
@ControllerAdvice
public class AllControllerAdvice {

	/**
	 * Exception handler of {@link FilmTypeNotFoundException}
	 */
	@ResponseBody
	@ExceptionHandler(FilmTypeNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	VndErrors filmTypeNotFoundExceptionHandler(FilmTypeNotFoundException ex) {
		return new VndErrors("error", ex.getMessage());
	}

	/**
	 * Exception handler of {@link PriceTypeNotFoundException}
	 */
	@ResponseBody
	@ExceptionHandler(PriceTypeNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	VndErrors priceTypeNotFoundExceptionHandler(PriceTypeNotFoundException ex) {
		return new VndErrors("error", ex.getMessage());
	}

	/**
	 * Exception handler of {@link VideoNotFoundException}
	 */
	@ResponseBody
	@ExceptionHandler(VideoNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	VndErrors videoNotFoundExceptionHandler(VideoNotFoundException ex) {
		return new VndErrors("error", ex.getMessage());
	}

	/**
	 * Exception handler of {@link AssociateNotFoundException}
	 */
	@ResponseBody
	@ExceptionHandler(AssociateNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	VndErrors associateNotFoundExceptionHandler(AssociateNotFoundException ex) {
		return new VndErrors("error", ex.getMessage());
	}

}
