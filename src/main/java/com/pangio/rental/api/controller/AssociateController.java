package com.pangio.rental.api.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pangio.rental.api.domain.Associate;
import com.pangio.rental.api.domain.Rental;
import com.pangio.rental.api.domain.Video;
import com.pangio.rental.api.exception.AssociateNotFoundException;
import com.pangio.rental.api.exception.VideoNotFoundException;
import com.pangio.rental.api.service.AssociateService;
import com.pangio.rental.api.service.RentalService;
import com.pangio.rental.api.service.VideoService;

/**
 * Controller Layer. {@link AssociateTypeController} exposes endpoints under the
 * URI '/associates'. It allows to rent a video, return a video, and get the
 * pending rental for an associate See {@link HttpMethod}
 * 
 * @author pangio
 * @param <V>
 */
@Controller
@RequestMapping("/associates")
@ComponentScan(basePackages = "com.pangio.rental.api")
public class AssociateController {

	@Autowired
	private VideoService videoService;

	@Autowired
	private RentalService rentalService;

	@Autowired
	private AssociateService associateService;

    private static final Logger logger = Logger.getLogger(AssociateController.class);

    AssociateController() {
	}

	/**
	 * Creates a new {@link Rental}.
	 * 
	 * @param the JSON representation of the Rental.
	 * @return 
	 * @return the created Rental
	 * @throws AssociateNotFoundException
	 * @throws VideoNotFoundException
	 */
	@RequestMapping(value = "/{associateId}/rent", method = RequestMethod.POST)
	@ResponseBody HashMap<String, Double> rent(@PathVariable Long associateId, @RequestBody HashMap<String, Long> videoParams, HttpServletResponse response)
			throws AssociateNotFoundException, VideoNotFoundException {

		HashMap<String, Double> jsonResponse = new HashMap<String, Double>();
		Long videoId = videoParams.get("videoId");
		Long days = videoParams.get("days");

		Associate associate = validateAssociate(associateId);
		Video video = validateVideo(videoId);

		Double fees = this.rentalService.rent(associate, video, days.intValue());
		response.setStatus(HttpServletResponse.SC_OK);
		jsonResponse.put("fees", fees);
		return jsonResponse;
	}

	@RequestMapping(value = "/{associateId}/return/{videoId}", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Double> returnVideoToRental(@PathVariable Long associateId, @PathVariable Long videoId, HttpServletResponse response) 
			throws AssociateNotFoundException, VideoNotFoundException {

		HashMap<String, Double> jsonResponse = new HashMap<String, Double>();

		Associate associate = validateAssociate(associateId);
		Video video = validateVideo(videoId);

		Double extraFees = rentalService.returnVideoToRental(associate, video);
		response.setStatus(HttpServletResponse.SC_OK);
		jsonResponse.put("extraFees", extraFees);
		return jsonResponse;
	}

	/**
	 * Returns the {@link Associate} with his/her BonusPoints and Rental history
	 * 
	 * @return associate
	 * @throws AssociateNotFoundException
	 */
	@RequestMapping(value = "/{associateId}", method = RequestMethod.GET)
	@ResponseBody
	public Associate profile(@PathVariable Long associateId) throws AssociateNotFoundException {
		Associate associate = validateAssociate(associateId);
		logger.info("Associate id "+associate.getId()+" - profile...");
		return associate;
	}

	/**
	 * Returns the {@link Associate} pending rentals to be returned to the
	 * Store.
	 * 
	 * @return list of pending rentals to be returned
	 * @throws AssociateNotFoundException
	 */
	@RequestMapping(value = "/{associateId}/pending", method = RequestMethod.GET)
	@ResponseBody
	public List<Rental> pendingToReturn(@PathVariable Long associateId) throws AssociateNotFoundException {
		Associate associate = validateAssociate(associateId);
		logger.info("Associate id "+associate.getId()+" - rentals pending to return...");			
		return rentalService.pendingToReturn(associate);
	}

	/**
	 * Validates if the provided associate id is correct. If not a
	 * {@link AssociateNotFoundException} is thrown.
	 * 
	 * @param associateId to validate
	 * @throws AssociateNotFoundException
	 */
	private Associate validateAssociate(Long associateId)
			throws AssociateNotFoundException {
		Associate associate = this.associateService.findById(associateId);
		if (associate == null) 
			throw new AssociateNotFoundException(associateId);
		return associate;
	}

	/**
	 * Validates if the provided video id is correct. If not a
	 * {@link VideoNotFoundException} is thrown.
	 * 
	 * @param videoId to validate
	 * @throws VideoNotFoundException
	 */
	private Video validateVideo(Long videoId) throws VideoNotFoundException {
		Video video = this.videoService.findById(videoId);
		if (video == null)
			throw new VideoNotFoundException(videoId);
		return video;
	}
}
