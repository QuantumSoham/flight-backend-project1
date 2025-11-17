package com.flightapp.dto.request;
//request dto -> incoming object into entity
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class FlightSearchRequest {

	public enum TripType {//stores info of if flight is round trip or one way 
		ONE_WAY, ROUND_TRIP
	}

	@NotBlank
	private String fromPlace;
	@NotBlank
	private String toPlace;
	@NotNull
	private LocalDate departureDate;
	private LocalDate returnDate; // used if ROUND_TRIP
	@NotNull
	private TripType tripType;

	// getters & setters
	public String getFromPlace() {
		return fromPlace;
	}

	public void setFromPlace(String fromPlace) {
		this.fromPlace = fromPlace;
	}

	public String getToPlace() {
		return toPlace;
	}

	public void setToPlace(String toPlace) {
		this.toPlace = toPlace;
	}

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public TripType getTripType() {
		return tripType;
	}

	public void setTripType(TripType tripType) {
		this.tripType = tripType;
	}
}
