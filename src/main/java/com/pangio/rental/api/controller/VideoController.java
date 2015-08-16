package com.pangio.rental.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pangio.rental.api.domain.Video;
import com.pangio.rental.api.exception.VideoNotFoundException;
import com.pangio.rental.api.service.VideoService;

/**
 * Controller Layer. {@link VideoController} exposes endpoints under the URI
 * '/videos'. CRUD operations for {@link Video}.
 * Note the DELETE method change video status as unavailable for further rentals.
 * See {@link HttpMethod}
 * 
 * @author pangio
 */
@RestController
@RequestMapping("/videos")
@ComponentScan(basePackages = "com.pangio.rental.api")
public class VideoController {

	@Autowired
	private VideoService service;

	VideoController() {
	}

	/**
	 * Creates a new {@link Video}.
	 * 
	 * @param the JSON representation of the videos.
	 * @return the created video
	 */
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<?> create(@RequestBody Video video) {
		this.service.create(video);
		return new ResponseEntity<Video>(video, HttpStatus.CREATED);
	}

	/**
	 * Retrieves a {@link Video}.
	 * 
	 * @param videoId of the requested {@link Video}.
	 * @return the video.
	 * @throws VideoNotFoundException
	 */
	@RequestMapping(value = "/{videoId}", method = RequestMethod.GET)
	@ResponseBody
	public Video get(@PathVariable Long videoId) throws VideoNotFoundException {
		validate(videoId);
		return service.findById(videoId);
	}

	/**
	 * Updates a {@link Video}.
	 * 
	 * @param newVideo the JSON representation of the video.
	 * @param videoId to be updated.
	 * @return the updated video.
	 * @throws VideoNotFoundException
	 */
	@RequestMapping(value = "/{videoId}", method = RequestMethod.PUT)
	@ResponseBody
	public Video update(@PathVariable long videoId, @RequestBody Video newVideo)
			throws VideoNotFoundException {
		validate(videoId);
		Video video = service.update(videoId, newVideo);
		return video;
	}

	/**
	 * Deletes a {@link Video} Soft delete. Only change the status of the video
	 * to be unavailable for further rentals
	 * 
	 * @param videoId to be deleted.
	 * @throws VideoNotFoundException
	 */
	@RequestMapping(value = "/{videoId}", method = RequestMethod.DELETE)
	@ResponseBody
	public Video delete(@PathVariable long videoId, HttpServletResponse response)
			throws VideoNotFoundException {
		validate(videoId);
		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		return service.delete(videoId);
	}

	/**
	 * Provides a list of all the {@link Video}s.
	 * @return the list of all videos.
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Video> list() {
		return service.list();
	}

	/**
	 * Validates if the provided video id is correct. If not a
	 * {@link VideoNotFoundException} is thrown.

	 * @param videoId
	 * @throws VideoNotFoundException
	 */
	private Video validate(Long videoId) throws VideoNotFoundException {
		Video video = this.service.findById(videoId);
		if (video == null)
			throw new VideoNotFoundException(videoId);
		return video;
	}

}
