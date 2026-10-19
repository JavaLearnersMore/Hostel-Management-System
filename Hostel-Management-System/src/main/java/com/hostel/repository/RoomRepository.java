package com.hostel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hostel.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

	List<Room> findByStatus(String status);

	@Query("SELECT r FROM Room r WHERE r.occupied < r.capacity AND r.status = 'Available'")
	List<Room> findAvailableRooms();
}
