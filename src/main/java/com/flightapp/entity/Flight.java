package com.flightapp.entity;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "flight",
       uniqueConstraints = @UniqueConstraint(name = "uq_flight_number_departure",
               columnNames = {"flight_number", "departure_datetime"}))
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    //many flights can have one airlien 
    @JoinColumn(name = "airline_id", nullable = false,
                foreignKey = @ForeignKey(name = "fk_flight_airline"))
    //here we are doing foreign key join
    private Airline airline;

    @Column(name = "flight_number", nullable = false, length = 20)
    private String flightNumber;

    @Column(name = "from_place", nullable = false, length = 100)
    private String fromPlace;

    @Column(name = "to_place", nullable = false, length = 100)
    private String toPlace;

    @Column(name = "departure_datetime", nullable = false)
    private LocalDateTime departureDateTime;

    @Column(name = "arrival_datetime", nullable = false)
    private LocalDateTime arrivalDateTime;

    @Column(name = "price_one_way", nullable = false, precision = 10, scale = 2)
    private BigDecimal priceOneWay;

    @Column(name = "price_round_trip", precision = 10, scale = 2)
    private BigDecimal priceRoundTrip;

    @Column(name = "total_seats", nullable = false)
    private Integer totalSeats;

    @Column(name = "available_seats", nullable = false)
    private Integer availableSeats;

    // getters & setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Airline getAirline() { return airline; }

    public void setAirline(Airline airline) { this.airline = airline; }

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

    public Integer getAvailableSeats() { return availableSeats; }

    public void setAvailableSeats(Integer availableSeats) { this.availableSeats = availableSeats; }
}
