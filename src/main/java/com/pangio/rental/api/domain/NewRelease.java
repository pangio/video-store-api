package com.pangio.rental.api.domain;

import javax.persistence.Entity;

/**
 * Domain Layer.
 * 
 * @author pangio
 */
@Entity
public class NewRelease extends FilmType {

	@Override
	public Double getFees(Integer days) {
		return getPriceType().getValue() * days;
	}

	@Override
	public Double getExtrasFees(Rental rental) {
		return getFees(rental.getDays());
	}

}
