package com.vehicle.booking.controller;

import com.vehicle.booking.dto.BookingRequest;
import com.vehicle.booking.model.Booking;
import com.vehicle.booking.service.BookingService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public Booking createBooking(@RequestBody BookingRequest request) {
        return bookingService.createBooking(request);
    }

    @GetMapping("/{id}")
    public Booking getBooking(@PathVariable String id) {
        return bookingService.getBooking(id);
    }

    @GetMapping("/user/{userId}")
    public List<Booking> getUserBookings(@PathVariable String userId) {
        return bookingService.getUserBookings(userId);
    }

    @PutMapping("/{id}/cancel")
    public Booking cancelBooking(@PathVariable String id) {
        return bookingService.cancelBooking(id);
    }


}
