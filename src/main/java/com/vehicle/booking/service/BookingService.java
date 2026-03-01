package com.vehicle.booking.service;

import com.vehicle.booking.dto.BookingRequest;
import com.vehicle.booking.model.Booking;
import com.vehicle.booking.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking createBooking(BookingRequest request) {

        long days = ChronoUnit.DAYS.between(
                request.getStartDate(),
                request.getEndDate());

        double pricePerDay = 5000;
        double totalPrice = days * pricePerDay;

        Booking booking = new Booking();
        booking.setUserId(request.getUserId());
        booking.setVehicleId(request.getVehicleId());
        booking.setStartDate(request.getStartDate());
        booking.setEndDate(request.getEndDate());
        booking.setTotalPrice(totalPrice);
        booking.setStatus("CREATED");

        return bookingRepository.save(booking);
    }

    public Booking getBooking(String id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    public List<Booking> getUserBookings(String userId) {
        return bookingRepository.findByUserId(userId);
    }

    public Booking cancelBooking(String id) {
        Booking booking = getBooking(id);
        booking.setStatus("CANCELLED");
        return bookingRepository.save(booking);
    }
}
