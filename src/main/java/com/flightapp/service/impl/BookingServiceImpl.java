package com.flightapp.service.impl;

import com.flightapp.dto.request.BookFlightRequest;
import com.flightapp.dto.request.PassengerRequest;
import com.flightapp.dto.response.BookingHistoryItemDto;
import com.flightapp.dto.response.PassengerDto;
import com.flightapp.dto.response.TicketResponse;
import com.flightapp.entity.*;
import com.flightapp.exception.BookingCancellationException;
import com.flightapp.exception.NotFoundException;
import com.flightapp.exception.SeatNotAvailableException;
import com.flightapp.repository.BookingRepository;
import com.flightapp.repository.FlightRepository;
import com.flightapp.repository.PassengerRepository;
import com.flightapp.repository.UserAccountRepository;
import com.flightapp.service.BookingService;
import com.flightapp.util.PnrGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service  //annotation to mark service layer
public class BookingServiceImpl implements BookingService {

	//repository objects used here 
	private final FlightRepository flightRepository;
	private final BookingRepository bookingRepository;
	private final PassengerRepository passengerRepository;
	private final UserAccountRepository userAccountRepository;
	
	//constructor to initialize the objects

	public BookingServiceImpl(FlightRepository flightRepository, BookingRepository bookingRepository,
			PassengerRepository passengerRepository, UserAccountRepository userAccountRepository) 
	{
		this.flightRepository = flightRepository;
		this.bookingRepository = bookingRepository;
		this.passengerRepository = passengerRepository;
		this.userAccountRepository = userAccountRepository;
	}

	@Override //overriding the interface function
	@Transactional//either execute or rollback
	public TicketResponse bookFlight(Long flightId, BookFlightRequest request) 
	{
		//switching to functional programming for better readibility find by id or throw ex
		Flight flight = flightRepository.findById(flightId)
				.orElseThrow(() -> new NotFoundException("Flight not found with id" + flightId));

		// check logged-in: for assignment, we just attempt to find user by email
		UserAccount user = userAccountRepository.findByEmail(request.getUserEmail()).orElse(null);

		//if passenger number is not matching no of requested seats
		if (request.getPassengers().size() != request.getNumberOfSeats()) 
		{
			throw new SeatNotAvailableException("Number of seats must match passengers count");
		}

		//if seats is < number of requested seats
		if (flight.getAvailableSeats() < request.getNumberOfSeats()) 
		{
			throw new SeatNotAvailableException("Not enough seats available");
		}

		//if all ok . proceed with booking
		// Create booking
		Booking booking = new Booking();
		booking.setFlight(flight);
		booking.setUser(user);
		booking.setUserName(request.getUserName());
		booking.setUserEmail(request.getUserEmail());
		booking.setNumberOfSeats(request.getNumberOfSeats());
		booking.setBookingDateTime(LocalDateTime.now());
		booking.setJourneyDateTime(flight.getDepartureDateTime());
		booking.setStatus(Booking.Status.BOOKED);
		String pnr = PnrGenerator.generatePnr(flight.getFlightNumber());
		booking.setPnr(pnr);

		Booking savedBooking = bookingRepository.save(booking);

		// create passengers
		List<Passenger> passengerEntities = new ArrayList<>();
		for (PassengerRequest passengerRequest : request.getPassengers()) {
			Passenger passenger = new Passenger();
			passenger.setBooking(savedBooking);
			passenger.setFlight(flight);
			passenger.setName(passengerRequest.getName());
			passenger.setGender(passengerRequest.getGender());
			passenger.setAge(passengerRequest.getAge());
			passenger.setSeatNumber(passengerRequest.getSeatNumber());
			passenger.setMealType(passengerRequest.getMealType());
			passengerEntities.add(passenger);
		}

		passengerRepository.saveAll(passengerEntities);

		// decrement available seats
		flight.setAvailableSeats(flight.getAvailableSeats() - request.getNumberOfSeats());
		flightRepository.save(flight);

		savedBooking.setPassengers(passengerEntities);

		return mapToTicketResponse(savedBooking);
	}

	@Override
	@Transactional(readOnly = true)
	public TicketResponse getTicketByPnr(String pnr) 
	{
		//find by pnr defined in repository , or else throw exception
		Booking booking = bookingRepository.findByPnr(pnr)
				.orElseThrow(() -> new NotFoundException("Booking not found for PNR " + pnr));
		return mapToTicketResponse(booking);
	}

	@Override
	@Transactional(readOnly = true)
	public List<BookingHistoryItemDto> getBookingHistory(String emailId) 
	{
		List<Booking> bookings = bookingRepository.findByUserEmailOrderByBookingDateTimeDesc(emailId);
		return bookings.stream().map(b -> {
			BookingHistoryItemDto dto = new BookingHistoryItemDto();
			dto.setPnr(b.getPnr());
			dto.setAirlineName(b.getFlight().getAirline().getName());
			dto.setFlightNumber(b.getFlight().getFlightNumber());
			dto.setFromPlace(b.getFlight().getFromPlace());
			dto.setToPlace(b.getFlight().getToPlace());
			dto.setJourneyDateTime(b.getJourneyDateTime());
			dto.setStatus(b.getStatus().name());
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void cancelBooking(String pnr) {
		Booking booking = bookingRepository.findByPnr(pnr)
				.orElseThrow(() -> new NotFoundException("Booking not found for PNR " + pnr));

		if (booking.getStatus() == Booking.Status.CANCELLED) {
			throw new BookingCancellationException("Booking already cancelled");
		}

		LocalDateTime now = LocalDateTime.now();
		long hoursBeforeJourney = ChronoUnit.HOURS.between(now, booking.getJourneyDateTime());
		if (hoursBeforeJourney < 24) {
			throw new BookingCancellationException("Cannot cancel within 24 hours of journey");
		}

		booking.setStatus(Booking.Status.CANCELLED);
		bookingRepository.save(booking);

		// restore seats
		Flight flight = booking.getFlight();
		flight.setAvailableSeats(flight.getAvailableSeats() + booking.getNumberOfSeats());
		flightRepository.save(flight);
	}

	private TicketResponse mapToTicketResponse(Booking booking) {
		TicketResponse resp = new TicketResponse();
		resp.setPnr(booking.getPnr());
		resp.setUserName(booking.getUserName());
		resp.setUserEmail(booking.getUserEmail());

		Flight flight = booking.getFlight();
		Airline airline = flight.getAirline();

		resp.setAirlineName(airline.getName());
		resp.setAirlineCode(airline.getCode());
		resp.setFlightNumber(flight.getFlightNumber());
		resp.setFromPlace(flight.getFromPlace());
		resp.setToPlace(flight.getToPlace());
		resp.setDepartureDateTime(flight.getDepartureDateTime());
		resp.setArrivalDateTime(flight.getArrivalDateTime());
		resp.setNumberOfSeats(booking.getNumberOfSeats());
		resp.setStatus(booking.getStatus().name());

		List<PassengerDto> passengerDtos = booking.getPassengers().stream().map(p -> {
			PassengerDto pDto = new PassengerDto();
			pDto.setName(p.getName());
			pDto.setGender(p.getGender());
			pDto.setAge(p.getAge());
			pDto.setSeatNumber(p.getSeatNumber());
			pDto.setMealType(p.getMealType());
			return pDto;
		}).collect(Collectors.toList());

		resp.setPassengers(passengerDtos);

		return resp;
	}
}
