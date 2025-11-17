package com.flightapp.dto.response;
//response data transfer object , converts api returned data into response object 
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FlightSearchResultDto 
{

    private Long flightId;
    private String airlineName;
    private String airlineLogoUrl;
    private String flightNumber;
    private String fromPlace;
    private String toPlace;
    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;
    private BigDecimal priceOneWay;
    private BigDecimal priceRoundTrip;
    private Integer availableSeats;

    // getters & setters
    public Long getFlightId() { return flightId; }
    public void setFlightId(Long flightId) { this.flightId = flightId; }

    public String getAirlineName() { return airlineName; }
    public void setAirlineName(String airlineName) { this.airlineName = airlineName; }

    public String getAirlineLogoUrl() { return airlineLogoUrl; }
    public void setAirlineLogoUrl(String airlineLogoUrl) { this.airlineLogoUrl = airlineLogoUrl; }

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

    public BigDecimal getPriceOneWay() { return priceOneWay; }
    public void setPriceOneWay(BigDecimal priceOneWay) { this.priceOneWay = priceOneWay; }

    public BigDecimal getPriceRoundTrip() { return priceRoundTrip; }
    public void setPriceRoundTrip(BigDecimal priceRoundTrip) { this.priceRoundTrip = priceRoundTrip; }

    public Integer getAvailableSeats() { return availableSeats; }
    public void setAvailableSeats(Integer availableSeats) { this.availableSeats = availableSeats; }
}
