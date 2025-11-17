package com.flightapp.controller;

import com.flightapp.dto.request.AddInventoryRequest;
import com.flightapp.dto.request.BookFlightRequest;
import com.flightapp.dto.request.FlightSearchRequest;
import com.flightapp.dto.response.BookingHistoryItemDto;
import com.flightapp.dto.response.FlightSearchResultDto;
import com.flightapp.dto.response.TicketResponse;
import com.flightapp.service.BookingService;
import com.flightapp.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0/flight")
public class FlightController {

    private final FlightService flightService;
    private final BookingService bookingService;

    public FlightController(FlightService flightService, BookingService bookingService) 
    {
        this.flightService = flightService;
        this.bookingService = bookingService;
    }

    
    @PostMapping("/airline/inventory/add")
    public ResponseEntity<Long> addInventory(@Valid @RequestBody AddInventoryRequest request) 
    {
        Long flightId = flightService.addInventory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(flightId);
    }

    
    @PostMapping("/search")
    public ResponseEntity<List<FlightSearchResultDto>> searchFlights(@Valid @RequestBody FlightSearchRequest request) 
    {
        List<FlightSearchResultDto> results = flightService.searchFlights(request);
        return ResponseEntity.ok(results);
    }

    
    @PostMapping("/booking/{flightId}")
    public ResponseEntity<TicketResponse> bookFlight(
            @PathVariable Long flightId,
            @Valid @RequestBody BookFlightRequest request) 
    {
        TicketResponse response = bookingService.bookFlight(flightId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    
    @GetMapping("/ticket/{pnr}")
    public ResponseEntity<TicketResponse> getTicket(@PathVariable String pnr) 
    {
        TicketResponse response = bookingService.getTicketByPnr(pnr);
        return ResponseEntity.ok(response);
    }

    
    @GetMapping("/booking/history/{emailId}")
    public ResponseEntity<List<BookingHistoryItemDto>> getHistory(@PathVariable String emailId) {
        List<BookingHistoryItemDto> history = bookingService.getBookingHistory(emailId);
        return ResponseEntity.ok(history);
    }

    
    @DeleteMapping("/booking/cancel/{pnr}")
    public ResponseEntity<Void> cancelBooking(@PathVariable String pnr) {
        bookingService.cancelBooking(pnr);
        return ResponseEntity.noContent().build();
    }
}
