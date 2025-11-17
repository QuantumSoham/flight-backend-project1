package com.flightapp.repository;

import com.flightapp.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

//used jpa reposity here because crud repo allows primitive syntax save() saveAll() etc
//jpa repository allows the writing of complex queries as function name which is parsed and converted to sql queries
public interface FlightRepository extends JpaRepository<Flight, Long> {

	//findByFromPlaceIgnoreCaseAndToPlaceIgnoreCaseAndDepartureDateTimeBetween is parsed to
	//SELECT * FROM Flight WHERE LOWER(fromPlace) = LOWER(:fromPlace) AND LOWER(toPlace) = LOWER(:toPlace)
	// AND departureDateTime BETWEEN :start AND :end;

    List<Flight> findByFromPlaceIgnoreCaseAndToPlaceIgnoreCaseAndDepartureDateTimeBetween(
            String fromPlace,
            String toPlace,
            LocalDateTime start,
            LocalDateTime end
    );
}
