package com.pangio.rental.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pangio.rental.api.domain.Associate;
import com.pangio.rental.api.domain.Video;
import com.pangio.rental.api.repository.VideoRepository;

/**
 * Service Layer.
 * 
 * @author pangio
 */
@Component
public class VideoService {

	@Autowired
	VideoRepository repository;

	/** Constructors */
	public VideoService() {
	}

	public VideoService(VideoRepository repository) {
		this.repository = repository;
	}

	/**
	 * Creates a new {@link Video}.
	 * 
	 * @param Video
	 *            - a JSON representation of a Video
	 */
	public void create(Video video) {
		this.repository.save(video);
	}

	/**
	 * Finds a {@link Video} by id
	 * 
	 * @param id
	 *            of the requested Video
	 * @return the Video
	 */
	public Video findById(Long id) {
		return this.repository.findById(id);
	}

	/**
	 * Updates a {@link Video}
	 * 
	 * @param videoId
	 *            to be updated
	 * @param type
	 *            - JSON representation of the new video
	 * @return the updated video
	 */
	public Video update(Long videoId, Video newVideo) {

		Video updatedVideo = null;

		Video storedVideo = repository.findById(videoId);
		if (storedVideo != null && newVideo != null) {
			storedVideo = newVideo;
			newVideo.setId(videoId);
			updatedVideo = repository.save(newVideo);
		}
		return updatedVideo;
	}

	/**
	 * Deletes a {@link Video}
	 * 
	 * @param id
	 *            of the Video to be deleted.
	 */
	public Video delete(Long id) {
		Video video = this.repository.findById(id);
		video.setEnabled(false);
		return this.repository.save(video);
	}

	/**
	 * Provides a list of all the {@link Video}s
	 * 
	 * @return the list of Video
	 */
	public List<Video> list() {
		return this.repository.findAll();
	}

	/**
	 * Returns if there is stock available for the requested video
	 * 
	 * @param video
	 * @return is the video in stock or not
	 */
	public boolean isStockAvailable(Video video) {
		return video.getAvailableStock() > 0;
	}

	/**
	 * The status is set to false when a video is DELETED.
	 * Returns the status of a video
	 * 
	 * @param video
	 * @return is the video available or not
	 */
	public boolean isEnabled(Video video) {
		return video.isEnabled();
	}

	/**
	 * When an {@link Associate} returns a {@link Video}, the service decreases
	 * the available stock of the video
	 * 
	 * @param video
	 */
	public void decreaseAvailableStock(Video video) {
		video.decreaseAvailableStock();
		repository.save(video);
	}

	/**
	 * When an {@link Associate} rents a {@link Video}, the service increase the
	 * available stock of the video
	 * 
	 * @param video
	 */
	public void increaseAvailableStock(Video video) {
		video.increaseAvailableStock();
		repository.save(video);
	}

}
