//Documentation I am adding for my reference and ease of understanding of various methods

package com.flightapp.controller;
// Here I am importing all the DTOs (Data Transfer Objects)
// These are like containers that carry data in a clean shape
import com.flightapp.dto.request.AddInventoryRequest;
import com.flightapp.dto.request.BookFlightRequest;
import com.flightapp.dto.request.FlightSearchRequest;
import com.flightapp.dto.response.BookingHistoryItemDto;
import com.flightapp.dto.response.FlightSearchResultDto;
import com.flightapp.dto.response.TicketResponse;

// Now i am importing service layers, These are the service layers
// I am not writing logic here, implemented later
import com.flightapp.service.BookingService;
import com.flightapp.service.FlightService;

// For validating input and handling REST responses
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// This marks the class as a REST controller
// why? so that every method returns JSON by default
@RestController

// Base URL for all methods inside this class
@RequestMapping("/api/v1.0/flight")
public class FlightController {

    // Dependency injection using constructor
    // Here I declare the services that I need
    private final FlightService flightService;
    private final BookingService bookingService;

    // Constructor injection — more testable, 
    //or I can also used @Autowired 
    public FlightController(FlightService flightService, BookingService bookingService) 
    {
        this.flightService = flightService;  
        this.bookingService = bookingService;
    }
    
    // API to add new flight inventory
    @PostMapping("/airline/inventory/add")
    public ResponseEntity<Long> addInventory(@Valid @RequestBody AddInventoryRequest request) 
    {
        // calling service layer, passing request object
        Long flightId = flightService.addInventory(request);

        // Returning CREATED(201) because a new resource (flight) is added
        return ResponseEntity.status(HttpStatus.CREATED).body(flightId);
    }

    
    // API to search flights based on from, to, date etc.
    @PostMapping("/search")
    public ResponseEntity<List<FlightSearchResultDto>> searchFlights(@Valid @RequestBody FlightSearchRequest request) 
    {
        // sending logic to service and getting results list
        List<FlightSearchResultDto> results = flightService.searchFlights(request);

        // ResponseEntity=sReturning 200 OK along with list of available flights
        return ResponseEntity.ok(results);
    }

    
    // API to book a flight, here flightId is passed in path
    @PostMapping("/booking/{flightId}")
    public ResponseEntity<TicketResponse> bookFlight(
            @PathVariable Long flightId,  // extracting flightId from URL
            @Valid @RequestBody BookFlightRequest request) // request body contains passenger data
  {
        // again delegating i am extending booking logic to service layer
        TicketResponse response = bookingService.bookFlight(flightId, request);

        // when booking is done, I return 201 CREATED with ticket details
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    
    // API to fetch ticket details using PNR
    @GetMapping("/ticket/{pnr}")
    public ResponseEntity<TicketResponse> getTicket(@PathVariable String pnr) 
    {
        // finding ticket with this pnr using bookingService
        TicketResponse response = bookingService.getTicketByPnr(pnr);

        // Simply returning 200 OK with ticket info
        return ResponseEntity.ok(response);
    }

    
    // API to get booking history based on email
    @GetMapping("/booking/history/{emailId}")
    public ResponseEntity<List<BookingHistoryItemDto>> getHistory(@PathVariable String emailId) {
        
        // service returns list of past bookings
        List<BookingHistoryItemDto> history = bookingService.getBookingHistory(emailId);
        return ResponseEntity.ok(history);
    }

    
    // API to cancel booking using PNR
    @DeleteMapping("/booking/cancel/{pnr}")
    public ResponseEntity<Void> cancelBooking(@PathVariable String pnr) {
        
        // No return data, just calling service cancel method
        bookingService.cancelBooking(pnr);
        // returning 204 No Content because nothing to return
        return ResponseEntity.noContent().build();
    }
}
