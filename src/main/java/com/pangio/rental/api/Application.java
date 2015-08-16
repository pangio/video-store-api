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

	private static FilmType old;
	private static FilmType regular;
	private static FilmType newRelease;
	private static FilmTypeRepository filmTypes;
	private static VideoRepository videos;
	private static PriceTypeRepository priceTypes;
	private static AssociateRepository associates;
	private static RentalService rentalService;
	private static PriceType basic;
	private static PriceType premium;
	private static Video terminator;
	private static Video casablanca;
	private static Video madMax;
	private static Video harryPotter;
	private static Video sanAndreas;
	private static Associate peter;
	private static Associate rick;
	private static Associate karen;

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

		logBootstrapData();
	}

	private static void getBeans(ConfigurableApplicationContext context) {
		videos = context
				.getBean(com.pangio.rental.api.repository.VideoRepository.class);
		filmTypes = context
				.getBean(com.pangio.rental.api.repository.FilmTypeRepository.class);
		priceTypes = context
				.getBean(com.pangio.rental.api.repository.PriceTypeRepository.class);
		associates = context
				.getBean(com.pangio.rental.api.repository.AssociateRepository.class);
		rentalService = context
				.getBean(com.pangio.rental.api.service.RentalService.class);
	}

	private static void createFilmTypes() {

		old = new OldFilm();
		old.setDescription("Old Film");
		old.setPriceType(basic);
		old.setBonusPoints(1);
		filmTypes.save(old);

		regular = new RegularFilm();
		regular.setDescription("Regular Film");
		regular.setPriceType(basic);
		regular.setBonusPoints(1);
		filmTypes.save(regular);

		newRelease = new NewRelease();
		newRelease.setDescription("New Release");
		newRelease.setPriceType(premium);
		newRelease.setBonusPoints(2);
		filmTypes.save(newRelease);
	}

	private static void createPriceTypes() {
		basic = new PriceType("Basic", new Double(30));
		priceTypes.save(basic);

		premium = new PriceType("Premium", new Double(40));
		priceTypes.save(premium);
	}

	private static void createVideos() {
		terminator = new Video("Terminator", 4, regular);
		videos.save(terminator);

		casablanca = new Video("Casablanca", 2, old);
		videos.save(casablanca);

		madMax = new Video("Mad Max", 5, newRelease);
		videos.save(madMax);

		harryPotter = new Video("Harry Potter", 6, regular);
		videos.save(harryPotter);

		sanAndreas = new Video("San Andreas", 7, newRelease);
		videos.save(sanAndreas);
	}

	private static void createAssociates() {
		karen = new Associate("Karen");
		associates.save(karen);

		rick = new Associate("Rick");
		associates.save(rick);

		peter = new Associate("Peter");
		associates.save(peter);
	}

	private static void rentFilms() {

		rentalService.rent(karen, sanAndreas, 1);
		rentalService.rent(karen, madMax, 2);
		rentalService.rent(karen, madMax, 1);
		rentalService.rent(karen, harryPotter, 3);

		rentalService.rent(rick, casablanca, 2);
		rentalService.rent(rick, terminator, 3);

		rentalService.rent(peter, casablanca, 5);
		rentalService.rent(peter, madMax, 3);
	}

	private static void logBootstrapData() {
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
