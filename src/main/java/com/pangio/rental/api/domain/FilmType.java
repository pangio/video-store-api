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
public abstract class FilmType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String description;
	@ManyToOne
	private PriceType price;
	private Integer bonusPoints = 0;

	/** Constructors */
	public FilmType() {
	}

	public FilmType(String description) {
		this.description = description;
	}

	public abstract Double getFees(Integer days);

	public abstract Double getExtrasFees(Rental rental);

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PriceType getPriceType() {
		return price;
	}

	public void setPriceType(PriceType price) {
		this.price = price;
	}

	public Integer getBonusPoints() {
		return bonusPoints;
	}

	public void setBonusPoints(Integer bonusPoints) {
		this.bonusPoints = bonusPoints;
	}

}
