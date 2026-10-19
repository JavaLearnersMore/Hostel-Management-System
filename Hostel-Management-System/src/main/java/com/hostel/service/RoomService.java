package com.hostel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hostel.entity.Room;
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
        return roomRepository.findById(id).orElseThrow();
    }

    public void save(Room room){
    	roomRepository.save(room);
    }

	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		 roomRepository.deleteById(id);
	}
    
    
   
}
