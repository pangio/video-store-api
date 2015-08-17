package com.pangio.rental.api;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.pangio.rental.api.domain.Associate;
import com.pangio.rental.api.domain.FilmType;
import com.pangio.rental.api.domain.NewRelease;
import com.pangio.rental.api.domain.OldFilm;
import com.pangio.rental.api.domain.PriceType;
import com.pangio.rental.api.domain.RegularFilm;
import com.pangio.rental.api.domain.Video;
import com.pangio.rental.api.repository.AssociateRepository;
import com.pangio.rental.api.repository.FilmTypeRepository;
import com.pangio.rental.api.repository.PriceTypeRepository;
import com.pangio.rental.api.repository.VideoRepository;
import com.pangio.rental.api.service.RentalService;

/**
 * Main class. Performs the setup of the Spring Boot application. Also creates
 * sample data.
 * 
 * @author pangio
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
@EntityScan(basePackages = "com.pangio.rental")
public class Application {

	private static FilmTypeRepository filmTypes;
	private static VideoRepository videos;
	private static PriceTypeRepository priceTypes;
	private static AssociateRepository associates;
	private static RentalService rentalService;

    private static final Logger logger = Logger.getLogger(Application.class);

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication
				.run(Application.class);

		getBeans(context);
		createPriceTypes();
		createFilmTypes();
		createVideos();
		createAssociates();
		rentFilms();
		logInitData();
	}

	private static void getBeans(ConfigurableApplicationContext context) {
		videos = context.getBean(com.pangio.rental.api.repository.VideoRepository.class);
		filmTypes = context.getBean(com.pangio.rental.api.repository.FilmTypeRepository.class);
		priceTypes = context.getBean(com.pangio.rental.api.repository.PriceTypeRepository.class);
		associates = context.getBean(com.pangio.rental.api.repository.AssociateRepository.class);
		rentalService = context.getBean(com.pangio.rental.api.service.RentalService.class);
	}

	private static void createPriceTypes() {
		PriceType basic = new PriceType("Basic", new Double(30));
		priceTypes.save(basic);

		PriceType premium = new PriceType("Premium", new Double(40));
		priceTypes.save(premium);
	}
	
	private static void createFilmTypes() {
		PriceType basic = priceTypes.findByDescription("Basic");

		FilmType old = new OldFilm();
		old.setDescription("Old Film");
		old.setPriceType(basic);
		old.setBonusPoints(1);
		filmTypes.save(old);

		FilmType regular = new RegularFilm();
		regular.setDescription("Regular Film");
		regular.setPriceType(basic);
		regular.setBonusPoints(1);
		filmTypes.save(regular);

		FilmType newRelease = new NewRelease();
		newRelease.setDescription("New Release");
		newRelease.setPriceType(priceTypes.findByDescription("Premium"));
		newRelease.setBonusPoints(2);
		filmTypes.save(newRelease);
	}

	private static void createVideos() {
		FilmType old = filmTypes.findByDescription("Old Film");
		FilmType regular = filmTypes.findByDescription("Regular Film");
		FilmType newRelease = filmTypes.findByDescription("New Release");
		
		videos.save(new Video("Terminator", 4, regular));
		videos.save(new Video("Casablanca", 2, old));
		videos.save(new Video("Mad Max", 5, newRelease));
		videos.save(new Video("Harry Potter", 6, regular));
		videos.save(new Video("San Andreas", 7, newRelease));
	}

	private static void createAssociates() {
		associates.save(new Associate("Karen"));
		associates.save(new Associate("Rick"));
		associates.save(new Associate("Peter"));
	}

	private static void rentFilms() {
		Video sanAndreas = videos.findByName("San Andreas");
		Video madMax = videos.findByName("Mad Max");
		Video harryPotter = videos.findByName("Harry Potter");
		Video casablanca = videos.findByName("Casablanca");
		Video terminator = videos.findByName("Terminator");

		rentalService.rent(associates.findByName("Karen"), sanAndreas, 1);
		rentalService.rent(associates.findByName("Karen"), madMax, 2);
		rentalService.rent(associates.findByName("Karen"), madMax, 1); // Can't rent it, she has already rented Mad Max
		rentalService.rent(associates.findByName("Karen"), harryPotter, 3);

		rentalService.rent(associates.findByName("Rick"), casablanca, 2);
		rentalService.rent(associates.findByName("Rick"), terminator, 3);

		rentalService.rent(associates.findByName("Peter"), casablanca, 5);
		rentalService.rent(associates.findByName("Peter"), madMax, 3);
	}

	private static void logInitData() {
		for (PriceType p : priceTypes.findAll()) {
			logger.info(p.getDescription()+" price is "+ p.getValue());			
		}
		for (FilmType f : filmTypes.findAll()) {
			logger.info("Renting a " + f.getDescription()
					+ " costs you " + f.getPriceType().getValue()
					+ " and gives you " + f.getBonusPoints()
					+ " bonus point(s)!");
		}
		for (Associate a : associates.findAll()) {
			logger.info("Associate "+a.getName()+" has "+ a.getBonusPoints() + " bonus points and counting...");			
		}
	}

}
