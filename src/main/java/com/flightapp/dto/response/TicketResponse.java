package com.flightapp.dto.response;
//response data transfer object , converts api returned data into response object 
import java.time.LocalDateTime;
import java.util.List;

public class TicketResponse {

    private String pnr;
    private String userName;
    private String userEmail;
    private String airlineName;
    private String airlineCode;
    private String flightNumber;
    private String fromPlace;
    private String toPlace;
    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;
    private Integer numberOfSeats;
    private String status;

    private List<PassengerDto> passengers;

    // getters & setters
    public String getPnr() { return pnr; }

    public void setPnr(String pnr) { this.pnr = pnr; }

    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

    public String getUserEmail() { return userEmail; }

    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }

    public String getAirlineName() { return airlineName; }

    public void setAirlineName(String airlineName) { this.airlineName = airlineName; }

    public String getAirlineCode() { return airlineCode; }

    public void setAirlineCode(String airlineCode) { this.airlineCode = airlineCode; }

    public String getFlightNumber() { return flightNumber; }

    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }

    public String getFromPlace() { return fromPlace; }

    public void setFromPlace(String fromPlace) { this.fromPlace = fromPlace; }

    public String getToPlace() { return toPlace; }

    public void setToPlace(String toPlace) { this.toPlace = toPlace; }

    public LocalDateTime getDepartureDateTime() { return departureDateTime; }

    public void setDepartureDateTime(LocalDateTime departureDateTime) { this.departureDateTime = departureDateTime; }

    public LocalDateTime getArrivalDateTime() { return arrivalDateTime; }

    public void setArrivalDateTime(LocalDateTime arrivalDateTime) { this.arrivalDateTime = arrivalDateTime; }

    public Integer getNumberOfSeats() { return numberOfSeats; }

    public void setNumberOfSeats(Integer numberOfSeats) { this.numberOfSeats = numberOfSeats; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public List<PassengerDto> getPassengers() { return passengers; }

    public void setPassengers(List<PassengerDto> passengers) { this.passengers = passengers; }
}
