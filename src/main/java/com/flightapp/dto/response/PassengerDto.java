package com.flightapp.dto.response;
//response data transfer object , converts api returned data into response object 
import com.flightapp.entity.Passenger;

public class PassengerDto {

    private String name;
    private Passenger.Gender gender;
    private Integer age;
    private String seatNumber;
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
