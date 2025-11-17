package com.flightapp.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "passenger", uniqueConstraints = @UniqueConstraint(name = "uq_flight_seat", columnNames = { "flight_id",
		"seat_number" }))
public class Passenger {



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "passenger_id")
	private Long id;
	
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "booking_id", nullable = false, foreignKey = @ForeignKey(name = "fk_passenger_booking"))
	private Booking booking;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "flight_id", nullable = false, foreignKey = @ForeignKey(name = "fk_passenger_flight"))
	private Flight flight;

	@Column(name = "name", nullable = false, length = 100)
	private String name;
	
	public enum Gender {MALE, FEMALE, OTHER}

	public enum MealType {VEG, NON_VEG}

	@Enumerated(EnumType.STRING)
	@Column(name = "gender", nullable = false, length = 10)
	private Gender gender;

	@Column(name = "age", nullable = false)
	private Integer age;

	@Column(name = "seat_number", nullable = false, length = 10)
	private String seatNumber;

	@Enumerated(EnumType.STRING)
	@Column(name = "meal_type", nullable = false, length = 10)
	private MealType mealType;
	

	// getters & setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public MealType getMealType() {
		return mealType;
	}

	public void setMealType(MealType mealType) {
		this.mealType = mealType;
	}
}
