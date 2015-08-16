package com.pangio.rental.api.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Domain Layer.
 * 
 * @author pangio
 */
@Entity
public class Video {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private Integer totalStock = 0;
	private Integer availableStock = 0;
	@ManyToOne
	private FilmType filmType;
	// The status is a SOFT DELETE. Is only set to false when a video is DELETED
	private boolean enabled = true;

	/** Constructors */
	public Video() {
	}

	public Video(String name, Integer totalStock, FilmType filmType) {
		this.name = name;
		this.filmType = filmType;
		this.availableStock = totalStock;
		this.totalStock = totalStock;
		this.enabled = true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAvailableStock() {
		return availableStock;
	}

	public void setAvailableStock(Integer availableStock) {
		this.availableStock = availableStock;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTotalStock() {
		return totalStock;
	}

	public void setTotalStock(Integer totalStock) {
		this.totalStock = totalStock;
	}

	public FilmType getFilmType() {
		return filmType;
	}

	public void setFilmType(FilmType filmType) {
		this.filmType = filmType;
	}

	public void decreaseAvailableStock() {
		this.availableStock--;
	}

	public void increaseAvailableStock() {
		this.availableStock++;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
