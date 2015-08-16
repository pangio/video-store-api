package com.pangio.rental.api.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Domain Layer.
 * This class represents the Customer of the Video Store
 * 
 * @author pangio
 */
@Entity
public class Associate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private Integer bonusPoints = 0;
	@OneToMany(fetch = FetchType.EAGER)
	private List<Rental> rentals = new ArrayList<Rental>();

	/** Constructors */
	public Associate(String name) {
		this.name = name;
	}

	public Associate() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getBonusPoints() {
		return bonusPoints;
	}

	public void addBonusPoints(Integer amount) {
		this.bonusPoints += amount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Rental> getRentals() {
		return rentals;
	}

	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}

	public void addRental(Rental rental) {
		this.rentals.add(rental);
	}

}
