package com.flightapp.repository;

import com.flightapp.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirlineRepository extends JpaRepository<Airline, Long> {

	//optional class return type means if Airline object is present then return it else null
	//findByCode is parsed to sql syntax
	//SELECT * FROM AIRLINE WHERE CODE = :code;
    Optional<Airline> findByCode(String code);
}
