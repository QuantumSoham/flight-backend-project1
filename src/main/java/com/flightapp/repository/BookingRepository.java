package com.flightapp.repository;

import com.flightapp.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    //findByPnr is parsed by jpa to 
	//SELECT * FROM BOOKING WHERE pnr=:pnr;
    Optional<Booking> findByPnr(String pnr);

    //findByUserEmailOrderByBookingDateTimeDesc is parsed to 
    //SELECT * FROM BOOKING WHERE USER_EMAIL=:userEmail ORDER BY BOOKINGDATETIME DESC;
    List<Booking> findByUserEmailOrderByBookingDateTimeDesc(String userEmail);
}
