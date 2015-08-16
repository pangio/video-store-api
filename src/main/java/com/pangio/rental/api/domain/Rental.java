package com.pangio.rental.api.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Domain Layer.
 * 
 * @author pangio
 */
@Entity
public class Rental {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Date dateRented;
	@ManyToOne
	private Video video;
	@ManyToOne
	@JsonBackReference
	private Associate associate;
	private Integer days;
	private boolean returned = false;

	/** Constructors */
	public Rental() {
		this.setDateRented(new Date());
	}

	public Rental(Associate associate, Video video, Integer days) {
		this.associate = associate;
		this.video = video;
		this.days = days;
		this.setDateRented(new Date());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public boolean isReturned() {
		return returned;
	}

	public void setReturned(boolean returned) {
		this.returned = returned;
	}

	public Date getDateRented() {
		return dateRented;
	}

	public void setDateRented(Date dateRented) {
		this.dateRented = dateRented;
	}

	public Associate getAssociate() {
		return associate;
	}

	public void setAssociate(Associate associate) {
		this.associate = associate;
	}
}
