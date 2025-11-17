package com.flightapp.dto.request;
//request dto -> incoming object into entity
import com.flightapp.entity.Passenger;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PassengerRequest {

    @NotBlank  // shouldn't be "" or " ", not null for strings
    private String name;
    @NotNull
    private Passenger.Gender gender;
    @NotNull
    @Min(0)
    private Integer age;
    @NotBlank
    private String seatNumber;
    @NotNull
    private Passenger.MealType mealType;

    // getters & setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Passenger.Gender getGender() { return gender; }
    public void setGender(Passenger.Gender gender) { this.gender = gender; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }
    public Passenger.MealType getMealType() { return mealType; }
    public void setMealType(Passenger.MealType mealType) { this.mealType = mealType; }
}
