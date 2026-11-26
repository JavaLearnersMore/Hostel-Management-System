package com.hostel.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.hostel.entity.RoomBooking;

@Service
public class RentService {

    public double calculateRent(RoomBooking booking){

    	
    	if (booking == null) {
            throw new IllegalArgumentException(
                    "Room booking cannot be null");
        }

        if (booking.getStartDate() == null) {
            throw new IllegalArgumentException(
                    "Booking start date cannot be null");
        }

        if (booking.getRoom() == null) {
            throw new IllegalArgumentException(
                    "Room is not assigned to this booking");
        }

    	
        long days = ChronoUnit.DAYS.between(
                booking.getStartDate(),
                LocalDate.now()
        );

        if(days == 0) days = 1;

        return days * booking.getRoom().getFee();
    }
}
