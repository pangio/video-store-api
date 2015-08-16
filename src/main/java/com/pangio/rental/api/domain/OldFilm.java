package com.pangio.rental.api.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.pangio.rental.api.util.DateUtils;

/**
 * Domain Layer.
 * 
 * @author pangio
 */
@Entity
public class OldFilm extends FilmType {

	@Override
	public Double getFees(Integer days) {

		Double price = getPriceType().getValue();
		if (days <= 5) {
			return price;
		} else
			return price + price * days / 5;
	}

	@Override
	public Double getExtrasFees(Rental rental) {

		Double extraFees = new Double(0);
		Date returnDate = new Date();
		Date dateRented = rental.getDateRented();
		Long extraDays = DateUtils.getDifferenceDays(dateRented, returnDate)
				- rental.getDays();

		if (extraDays > 0)
			extraFees = rental.getVideo().getFilmType().getPriceType()
					.getValue()
					* extraDays / 5;
		return extraFees;
	}
}
