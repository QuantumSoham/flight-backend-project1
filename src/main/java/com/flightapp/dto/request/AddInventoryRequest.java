package com.flightapp.dto.request;
//request dto -> incoming object into entity
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AddInventoryRequest {

    @NotNull
    private Long airlineId;
    @NotBlank
    private String flightNumber;
    @NotBlank
    private String fromPlace;
    @NotBlank
    private String toPlace;
    @NotNull
    @Future
    private LocalDateTime departureDateTime;

    @NotNull
    @Future
    private LocalDateTime arrivalDateTime;
    @NotNull
    @Min(0)
    private BigDecimal priceOneWay;
    private BigDecimal priceRoundTrip;
    @NotNull
    @Min(1)
    private Integer totalSeats;

    // getters & setters
    public Long getAirlineId() { return airlineId; }
    public void setAirlineId(Long airlineId) { this.airlineId = airlineId; }

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

    public Integer getTotalSeats() { return totalSeats; }
    public void setTotalSeats(Integer totalSeats) { this.totalSeats = totalSeats; }
}
