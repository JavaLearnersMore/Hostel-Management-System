package com.hostel.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hostel.entity.Attendance;
import com.hostel.entity.Student;
import com.hostel.repository.AttendanceRepository;
import com.hostel.service.AttendanceService;
import com.hostel.service.LeaveService;
import com.hostel.service.PaymentService;
import com.hostel.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentDashboardController {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private LeaveService leaveService;

    @Autowired 
    private AttendanceRepository repo;
    @Autowired
    private PaymentService paymentService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {

        // 🔐 Get logged-in username
        String username = principal.getName();

        // 👤 Fetch student
        Student student = studentService.findByUsername(username);

        if (student == null) {
            return "redirect:/login"; // safety check
        }

        // 📊 Get data
        int attendancePercent = attendanceService.getAttendancePercentage(student.getId());
        int leaveCount = leaveService.getApprovedLeaveCount(student.getId());
        double pendingFees = paymentService.getPendingFees(student.getId());

        // 📦 Send to JSP
        model.addAttribute("student", student);
        model.addAttribute("attendancePercent", attendancePercent);
        model.addAttribute("leaveCount", leaveCount);
        model.addAttribute("pendingFees", pendingFees);
        return "student/student-dashboard";
    }

    @PostMapping("/mark")
    public String markAttendance(
    		@RequestParam Long studentId,
            @RequestParam String status) {
    	System.out.println("studentId = " + studentId);
    	System.out.println("status = " + status);
    	System.out.println("student markAttendance ........................................");
    	attendanceService.markAttendance(studentId, status);
        return "redirect:/student/attendance";
    }
    
    @GetMapping("/attendance")
    public String attendance(Model model, Principal principal){

        Student s = studentService.findByUsername(principal.getName());

        Optional<Attendance> optional =
                repo.findByStudentIdAndAttendDate(s.getId(), LocalDate.now());

        model.addAttribute("student", s);
        model.addAttribute("attendance", optional.orElse(null));
        model.addAttribute("today", LocalDate.now());

        return "student/attendance";
    }
}