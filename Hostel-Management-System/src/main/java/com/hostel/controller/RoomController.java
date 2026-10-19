package com.hostel.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hostel.entity.Room;
import com.hostel.entity.Student;
import com.hostel.repository.RoomRepository;
import com.hostel.repository.StudentRepository;
import com.hostel.service.StudentService;

@Controller
@RequestMapping("/student")
public class RoomController {

    @Autowired
    private RoomRepository roomRepo;

    @Autowired
    private StudentService studentRepo;

	private CrudRepository<Room, Long> roomService;

    @GetMapping("/select-room")
    public String selectRoom(Model model){
        model.addAttribute("rooms", roomRepo.findByStatus("AVAILABLE"));
        return "select-room";
    }

    @PostMapping("/select-room")
    public String assignRoom(@RequestParam Long roomId, Principal principal){

        Student student = studentRepo.findByUsername(principal.getName());
        Room room = roomRepo.findById(roomId).get();

        student.setRoom(room);
        room.setOccupied(room.getOccupied()+1);

        if(room.getOccupied() >= room.getCapacity()){
            room.setStatus("FULL");
        }

        studentRepo.save(student);
        roomRepo.save(room);

        return "redirect:/student/pay-fee";
    }
    
    
   
}