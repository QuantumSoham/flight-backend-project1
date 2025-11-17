package com.flightapp.service;
import com.flightapp.dto.request.AddInventoryRequest;
import com.flightapp.dto.request.FlightSearchRequest;
import com.flightapp.dto.response.FlightSearchResultDto;
import java.util.List;

//interface to provide blueprint for flight service actions
//implementations have been done in impl package
public interface FlightService {

    Long addInventory(AddInventoryRequest request);// add flight to inventory
    List<FlightSearchResultDto> searchFlights(FlightSearchRequest request);//search flight
}
