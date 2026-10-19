package com.hostel.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hostel.beans.StudentAttendance;
import com.hostel.entity.Attendance;

import com.hostel.entity.LeaveRequest;
import com.hostel.entity.Room;
import com.hostel.entity.Visitor;
import com.hostel.repository.LeaveRepository;
import com.hostel.repository.VisitorRepository;
import com.hostel.service.AttendanceService;
import com.hostel.service.DashboardService;
import com.hostel.service.LeaveService;
import com.hostel.service.PaymentService;
import com.hostel.service.RoomService;
import com.hostel.service.StudentService;
import com.hostel.service.VisitorService;


@Controller
@RequestMapping("/admin")
public class AdminDashboardController {

    @Autowired
    private DashboardService dashboardService;
    
    @Autowired
    private StudentService studentService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private VisitorService visitorService;

    @Autowired
    private AttendanceService attendanceService;
    
    @Autowired
    private LeaveService leaveService;  
    
    @Autowired
    private VisitorRepository visitorRepository;
    
    @Autowired
    private LeaveRepository leaveRepository;
    
    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        model.addAttribute("totalStudents", studentService.count());
        model.addAttribute("attendancePercentage", attendanceService.getTodayAttendancePercentage());
        model.addAttribute("totalRooms", roomService.count());
        model.addAttribute("totalFees", paymentService.getTotalCollectedAmount());
        model.addAttribute("totalVisitors", visitorService.count());

        model.addAttribute("page", "admin-dashboard"); // 🔥 IMPORTANT

        return "admin/admin-dashboard";
    }

    @GetMapping("/students")
    public String students(Model model) {

        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("page", "students");
        return "admin/students";
    }

    @GetMapping("/rooms")
    public String rooms(Model model) {
    	List<Room> list = roomService.getRoomDetails();
        model.addAttribute("rooms", roomService.getRoomDetails());
        model.addAttribute("page", "rooms");
        return "admin/rooms";
    }

    @GetMapping("/visitors")
    public String visitors(Model model) {
    	List<Visitor> list = visitorService.getVisitorDetails();
    	System.out.println("Visitors list : "+list.toString());
        model.addAttribute("visitors", visitorService.getVisitorDetails());
        model.addAttribute("page", "visitors");
        return "admin/visitors";
    }
    
    @GetMapping("/visitors/delete/{id}")
    public String deleteVisitors(@PathVariable Long id, Principal principal) {
    	System.out.println("Deleted ID : "+id);
    	visitorRepository.deleteById(id);
    	
        //return "admin/visitors";
        return "redirect:/admin/visitors";
    }

   @GetMapping("/leaves")
    public String leaves(Model model) {

       model.addAttribute("leaves", leaveService.getAllLeaves());
        model.addAttribute("page", "leaves");

        return "admin/leaves";
    } 
   
   @GetMapping("/leaves/reject/{id}")
   public String deleteleaves(@PathVariable Long id, Principal principal) {

	   LeaveRequest leave = leaveRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Leave request not found"));

	    leave.setStatus("REJECTED");

	    leaveRepository.save(leave);
	  
	    System.out.println("Leave rejected for id : "+id);
	    return "redirect:/admin/leaves";
   } 
   
   
   @GetMapping("/leaves/approve/{id}")
   public String approveLeaves(@PathVariable Long id, Principal principal) {

	   LeaveRequest leave = leaveRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Leave request not found"));

	    leave.setStatus("APPROVED");

	    leaveRepository.save(leave);
	  
	    System.out.println("Leave approved for id : "+id);
	    return "redirect:/admin/leaves";
   } 

}

	