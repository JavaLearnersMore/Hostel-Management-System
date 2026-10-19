package com.hostel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // ✅ correct import
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hostel.entity.Room;
import com.hostel.service.RoomService;

@Controller
@RequestMapping("/admin")
public class AdminRoomController {

    @Autowired
    private RoomService roomService;

    // 👉 Rooms list
   // @GetMapping("/rooms")
    //public String getRooms(Model model) {

      //  model.addAttribute("rooms", roomService.getRoomDetails());

      //  return "admin/rooms";
   // }

    // 👉 Delete
    @GetMapping("/rooms/delete/{id}")
    public String deleteRoom(@PathVariable Long id) {

        roomService.deleteById(id);

        return "redirect:/admin/rooms?deleted=true";
    }

    // 👉 Edit page
    @GetMapping("/rooms/edit/{id}")
    public String editRoom(@PathVariable Long id, Model model) {

        model.addAttribute("room", roomService.getRoomById(id));

        return "admin/edit-room";
    }

    @PostMapping("/rooms/update")
    public String updateRoom(@ModelAttribute Room room) {

        roomService.save(room);

        return "redirect:/admin/rooms?updated=true";
    }
}