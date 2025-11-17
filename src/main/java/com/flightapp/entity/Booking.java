package com.flightapp.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "booking")
public class Booking {

	public enum Status {
		BOOKED, CANCELLED
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_id")
	private Long id;

	@Column(name = "pnr", nullable = false, length = 20, unique = true)
	private String pnr;

	@ManyToOne(fetch = FetchType.LAZY) //dont load all the columns simultaneously
	//One booking -> can have many flights
	@JoinColumn(name = "flight_id", nullable = false, foreignKey = @ForeignKey(name = "fk_booking_flight"))
	private Flight flight;

	@ManyToOne(fetch = FetchType.LAZY)
	//one booking can have many passenger user
	@JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_booking_user"))
	private UserAccount user;

	@Column(name = "user_name", nullable = false, length = 100)
	private String userName;

	@Column(name = "user_email", nullable = false, length = 100)
	private String userEmail;

	@Column(name = "number_of_seats", nullable = false)
	private Integer numberOfSeats;

	@Column(name = "booking_datetime", nullable = false)
	private LocalDateTime bookingDateTime;

	@Column(name = "journey_datetime", nullable = false)
	private LocalDateTime journeyDateTime;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false, length = 20)
	private Status status;

	@OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Passenger> passengers;

	// getters & setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPnr() {
		return pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public UserAccount getUser() {
		return user;
	}

	public void setUser(UserAccount user) {
		this.user = user;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Integer getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(Integer numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public LocalDateTime getBookingDateTime() {
		return bookingDateTime;
	}

	public void setBookingDateTime(LocalDateTime bookingDateTime) {
		this.bookingDateTime = bookingDateTime;
	}

	public LocalDateTime getJourneyDateTime() {
		return journeyDateTime;
	}

	public void setJourneyDateTime(LocalDateTime journeyDateTime) {
		this.journeyDateTime = journeyDateTime;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}
}
