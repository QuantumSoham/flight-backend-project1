package com.flightapp.service.impl;

import com.flightapp.dto.request.AddInventoryRequest;
import com.flightapp.dto.request.FlightSearchRequest;
import com.flightapp.dto.response.FlightSearchResultDto;
import com.flightapp.entity.Airline;
import com.flightapp.entity.Flight;
import com.flightapp.exception.BadRequestException;
import com.flightapp.exception.NotFoundException;
import com.flightapp.repository.AirlineRepository;
import com.flightapp.repository.FlightRepository;
import com.flightapp.service.FlightService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {

	private final AirlineRepository airlineRepository;
	private final FlightRepository flightRepository;

	public FlightServiceImpl(AirlineRepository airlineRepository, FlightRepository flightRepository) 
	{
		this.airlineRepository = airlineRepository;
		this.flightRepository = flightRepository;
	}

	@Override
	@Transactional
	public Long addInventory(AddInventoryRequest request)//add a flight to inventory 
	{
		//get airline id or throw ex
		Airline airline = airlineRepository.findById(request.getAirlineId())
				.orElseThrow(() -> new NotFoundException("Airline not found with id " + request.getAirlineId()));

		//check if departure is after arrival or else chaos, throw ex
		if (!request.getDepartureDateTime().isBefore(request.getArrivalDateTime())) {
			throw new BadRequestException("Departure time must be before arrival time");
		}

		//create a entry 
		Flight flight = new Flight();
		flight.setAirline(airline);
		flight.setFlightNumber(request.getFlightNumber());
		flight.setFromPlace(request.getFromPlace());
		flight.setToPlace(request.getToPlace());
		flight.setDepartureDateTime(request.getDepartureDateTime());
		flight.setArrivalDateTime(request.getArrivalDateTime());
		flight.setPriceOneWay(request.getPriceOneWay());
		flight.setPriceRoundTrip(request.getPriceRoundTrip());
		flight.setTotalSeats(request.getTotalSeats());
		flight.setAvailableSeats(request.getTotalSeats());

		Flight saved = flightRepository.save(flight); //save in flight database
		return saved.getId();
	}

	@Override
	@Transactional(readOnly = true)
	//search flights from to to location,
	public List<FlightSearchResultDto> searchFlights(FlightSearchRequest request) 
	{
		LocalDate date = request.getDepartureDate();
		LocalDateTime start = date.atStartOfDay();
		LocalDateTime end = date.plusDays(1).atStartOfDay().minusNanos(1);
		//load all flights from to location of start to end
		List<Flight> flights = flightRepository
				.findByFromPlaceIgnoreCaseAndToPlaceIgnoreCaseAndDepartureDateTimeBetween(request.getFromPlace(),
						request.getToPlace(), start, end);

		//convert flight to stream and then use functional mapping to set and send to response dto
		return flights.stream().map(f -> {
			FlightSearchResultDto dto = new FlightSearchResultDto();
			dto.setFlightId(f.getId());
			dto.setAirlineName(f.getAirline().getName());
			dto.setAirlineLogoUrl(f.getAirline().getLogoUrl());
			dto.setFlightNumber(f.getFlightNumber());
			dto.setFromPlace(f.getFromPlace());
			dto.setToPlace(f.getToPlace());
			dto.setDepartureDateTime(f.getDepartureDateTime());
			dto.setArrivalDateTime(f.getArrivalDateTime());
			dto.setPriceOneWay(f.getPriceOneWay());
			dto.setPriceRoundTrip(f.getPriceRoundTrip());
			dto.setAvailableSeats(f.getAvailableSeats());
			return dto;
		}).collect(Collectors.toList());
	}
}
