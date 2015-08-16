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

import com.pangio.rental.api.domain.PriceType;
import com.pangio.rental.api.exception.PriceTypeNotFoundException;
import com.pangio.rental.api.service.PriceTypeService;

/**
 * Controller Layer. {@link PriceTypeController} exposes endpoints under the URI
 * '/prices'. See {@link HttpMethod}
 * 
 * @author pangio
 */
@Controller
@RequestMapping("/prices")
@ComponentScan(basePackages = "com.pangio.rental.api")
public class PriceTypeController {

	@Autowired
	private PriceTypeService service;

	PriceTypeController() {
	}

	/**
	 * Retrieves a {@link PriceType}.
	 * 
	 * @param typeId of the requested {@link PriceType}.
	 * @return the PriceType.
	 * @throws PriceTypeNotFoundException
	 */
	@RequestMapping(value = "/{typeId}", method = RequestMethod.GET)
	@ResponseBody
	public PriceType get(@PathVariable Long typeId)
			throws PriceTypeNotFoundException {
		validate(typeId);
		return service.findById(typeId);
	}

	/**
	 * Provides a list of all the {@link PriceType}s.
	 * 
	 * @return the list of all VideoTypes.
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<PriceType> list() {
		return service.list();
	}

	/**
	 * Validates if the provided type id is correct. If not a
	 * {@link PriceTypeNotFoundException} is thrown.
	 * 
	 * @param type
	 * @throws PriceTypeNotFoundException
	 */
	private void validate(Long typeId) throws PriceTypeNotFoundException {
		PriceType priceType = this.service.findById(typeId);
		if (priceType == null)
			throw new PriceTypeNotFoundException(typeId);
	}
}
