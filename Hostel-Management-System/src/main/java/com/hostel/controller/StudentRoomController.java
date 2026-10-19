package com.hostel.controller;

import java.io.File;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hostel.entity.LeaveRequest;
import com.hostel.entity.Room;
import com.hostel.entity.Student;
import com.hostel.repository.LeaveRepository;
import com.hostel.repository.RoomBookingRepository;
import com.hostel.repository.RoomRepository;
import com.hostel.service.RoomService;
import com.hostel.service.StudentService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/student")
public class StudentRoomController {

	@Autowired
	private RoomRepository roomRepo;

	@Autowired
	private RoomBookingRepository bookingRepo;

	@Autowired
	private StudentService studentService;

	@Autowired
	private RoomService roomService;

	@Autowired
	private LeaveRepository leaveRepo;


	@GetMapping("/rooms")
	public String showRooms(Model model){
		System.out.println(" rooms : "+roomRepo.findAvailableRooms());
		model.addAttribute("rooms", roomRepo.findAvailableRooms());
		return "student/available-rooms";
	}

	@PostMapping("/book/{roomId}")
	public String bookRoom(@PathVariable Long roomId, Principal principal){

		Student student = studentService.findByUsername(principal.getName());

		Room room = roomService.getRoomById(roomId);

		// check capacity
		if(room.getOccupied() >= room.getCapacity()){
			return "redirect:/student/rooms?full";
		}

		// assign room to student
		student.setRoom(room);
		studentService.save(student);

		// increase occupied count
		room.setOccupied(room.getOccupied() + 1);

		// update status if full
		if(room.getOccupied() == room.getCapacity()){
			room.setStatus("FULL");
		}

		roomService.save(room);

		return "redirect:/student/dashboard?booked";
	}

	@GetMapping("/my-room")
	public String myRoom(Model model, Principal principal) {

		Student student = studentService.findByUsername(principal.getName());

		model.addAttribute("student", student);
		model.addAttribute("room", student.getRoom());

		return "student/my-room";
	}

	    @GetMapping("/profile")
	    public String profile(Model model, Principal principal){
	        model.addAttribute("student",
	                studentService.findByUsername(principal.getName()));
	        return "student/profile";
	    }

//	@GetMapping("/profile")
//	public String profile(Model model, HttpSession session) {
//
//	    String username = (String) session.getAttribute("loggedInUser");
//
//	    if(username == null){
//	        return "redirect:/login";
//	    }
//
//	    Student student = studentService.findByUsername(username);
//
//	    model.addAttribute("student", student);
//
//	    return "student/profile";
//	}
	    
	    @PostMapping("/profile/upload")
	    public String uploadProfilePic(@RequestParam("profilePic") MultipartFile file,
	                                   HttpSession session,Principal principal) {

	        try {
	            // ✅ Get logged in username from session
	            String username = (String) session.getAttribute("loggedInUser");

	            if (username == null) {
	                return "redirect:/login";
	            }

	            // ✅ Fetch student
//	            Student student = studentService.findByUsername(username)
//	                    .orElseThrow(() -> new RuntimeException("Student not found"));
	            Student student = studentService.findByUsername(principal.getName());

	            // ✅ Check file not empty
	            if (!file.isEmpty()) {

	                // 📁 Folder path (inside project)
	                String uploadDir = "G:/Nisha More/Nisha Document/";

	                // ✅ Create folder if not exists
	                File dir = new File(uploadDir);
	                if (!dir.exists()) {
	                    dir.mkdirs();
	                }

	                // ✅ Generate unique file name
	               // String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
	                
	                String originalFileName = file.getOriginalFilename();
	                String cleanFileName = originalFileName.replaceAll("\\s+", "_");

	                String fileName = System.currentTimeMillis() + "_" + cleanFileName;

	                // ✅ Save file
	                File saveFile = new File(uploadDir + fileName);
	                file.transferTo(saveFile);

	                // ✅ Save path in DB
	                student.setProfilePic("/uploads/" + fileName);
	                studentService.save(student);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return "redirect:/student/profile";
	    }

	@GetMapping("/leave")
	public String leavePage(Model model){
		model.addAttribute("leave", new LeaveRequest());
		return "student/leave";
	}

	@PostMapping("/leave/apply")
	public String applyLeave(@ModelAttribute LeaveRequest leave, Principal principal){
		Student s = studentService.findByUsername(principal.getName());
		leave.setStudent(s);
		leave.setStatus("PENDING");
		leaveRepo.save(leave);
		return "redirect:/student/leave?applied";
	}
}