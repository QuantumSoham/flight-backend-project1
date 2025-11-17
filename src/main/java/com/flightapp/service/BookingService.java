package com.flightapp.service;

import com.flightapp.dto.request.BookFlightRequest;
import com.flightapp.dto.response.BookingHistoryItemDto;
import com.flightapp.dto.response.TicketResponse;

import java.util.List;

//interface to provide basic functionalities of flight booking
//implementations have been done in impl package
public interface BookingService {

    TicketResponse bookFlight(Long flightId, BookFlightRequest request);//book flight taking flight id and input json of user details
    TicketResponse getTicketByPnr(String pnr);//i am taking pnr from url param and getting ticket details
    List<BookingHistoryItemDto> getBookingHistory(String emailId);//take email id from url param and then return booking history
    void cancelBooking(String pnr);//take input of pnr from url param and then cancel it
}
