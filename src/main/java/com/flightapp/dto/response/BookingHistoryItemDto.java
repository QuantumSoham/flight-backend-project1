package com.flightapp.dto.response;
//response dto converts entity into response object
import java.time.LocalDateTime;

public class BookingHistoryItemDto 
{

	//no strict checking needed here
	private String pnr;
	private String airlineName;
	private String flightNumber;
	private String fromPlace;
	private String toPlace;
	private LocalDateTime journeyDateTime;
	private String status;

	// getters & setters
	public String getPnr() {
		return pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

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

	public LocalDateTime getJourneyDateTime() {
		return journeyDateTime;
	}

	public void setJourneyDateTime(LocalDateTime journeyDateTime) {
		this.journeyDateTime = journeyDateTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
