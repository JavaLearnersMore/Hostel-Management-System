package com.hostel.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.hostel.entity.RoomBooking;

@Service
public class RentService {

    public double calculateRent(RoomBooking booking){

        long days = ChronoUnit.DAYS.between(
                booking.getStartDate(),
                LocalDate.now()
        );

        if(days == 0) days = 1;

        return days * booking.getRoom().getFee();
    }
}
