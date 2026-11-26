package com.hostel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hostel.entity.Room;
import com.hostel.exception.ResourceNotFoundException;
import com.hostel.repository.RoomRepository;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public long count() {
        return roomRepository.count();
    }
    
    public List<Room> getRoomDetails() {
    	return roomRepository.findAll();
    }

    public Room getRoomById(Long id){
        //return roomRepository.findById(id).orElseThrow();
    	
    	Optional<Room> room = roomRepository.findById(id);

        if (!room.isPresent()) {
            throw new ResourceNotFoundException(
                    "Room not found with ID: " + id);
        }

        return room.get();
    }
    

    public void save(Room room){
    	roomRepository.save(room);
    }

	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		 Optional<Room> room = roomRepository.findById(id);

	        if (!room.isPresent()) {
	            throw new ResourceNotFoundException(
	                    "Room not found with ID: " + id);
	        }
	        roomRepository.deleteById(id);
	        //roomRepository.deleteById(id);
	    }
	
    
    
   
}
