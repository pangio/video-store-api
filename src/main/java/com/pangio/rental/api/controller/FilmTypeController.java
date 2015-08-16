package com.pangio.rental.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pangio.rental.api.domain.FilmType;
import com.pangio.rental.api.exception.FilmTypeNotFoundException;
import com.pangio.rental.api.service.FilmTypeService;

/**
 * Controller Layer. {@link FilmTypeController} exposes endpoints under the URI
 * '/types'. See {@link HttpMethod}
 * 
 * @author pangio
 */
@Controller
@RequestMapping("/types")
@ComponentScan(basePackages = "com.pangio.rental.api")
public class FilmTypeController {

	@Autowired
	private FilmTypeService service;

	FilmTypeController() {
	}

	/**
	 * Retrieves a {@link FilmType}.
	 * 
	 * @param filmTypeId of the requested {@link FilmType}.
	 * @return the filmType.
	 * @throws FilmTypeNotFoundException
	 */
	@RequestMapping(value = "/{filmTypeId}", method = RequestMethod.GET)
	@ResponseBody
	public FilmType get(@PathVariable Long filmTypeId)
			throws FilmTypeNotFoundException {
		validate(filmTypeId);
		return service.findById(filmTypeId);
	}

	/**
	 * Provides a list of all the {@link FilmType}s.
	 * @return the list of all filmTypes.
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<FilmType> list() {
		return service.list();
	}

	/**
	 * Validates if the provided filmType id is correct. If not a
	 * {@link FilmTypeNotFoundException} is thrown.
	 * 
	 * @param filmTypeId
	 * @throws FilmTypeNotFoundException
	 */
	private void validate(Long filmTypeId) throws FilmTypeNotFoundException {
		FilmType filmType = this.service.findById(filmTypeId);
		if (filmType == null)
			throw new FilmTypeNotFoundException(filmTypeId);
	}

}
