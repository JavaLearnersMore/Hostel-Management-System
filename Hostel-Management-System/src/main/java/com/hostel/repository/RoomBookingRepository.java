package com.hostel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hostel.entity.RoomBooking;

public interface RoomBookingRepository extends JpaRepository<RoomBooking, Long> {
}
