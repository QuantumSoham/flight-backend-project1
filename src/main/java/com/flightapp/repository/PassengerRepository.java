package com.flightapp.repository;

import com.flightapp.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
	
	//Empty because No custom queries needed here 
	//JpaRepository already provides full CRUD support.
	// Passengers are only saved and loaded via Booking logic, so this stays empty by design

}
