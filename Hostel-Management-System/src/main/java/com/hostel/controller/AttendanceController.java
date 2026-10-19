package com.hostel.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hostel.entity.Attendance;
import com.hostel.entity.Student;
import com.hostel.repository.AttendanceRepository;
import com.hostel.service.AttendanceService;
import com.hostel.service.StudentService;

@Controller
@RequestMapping("/admin")
public class AttendanceController {

    private final AttendanceService service;
    
    @Autowired
    private AttendanceService attendanceService;
    

    public AttendanceController(AttendanceService service) {
        this.service = service;
    }

    @PostMapping("/mark")
    public String markAttendance(
            @RequestParam Long studentId,
            @RequestParam String status) {

        service.markAttendance(studentId, status);
        return "redirect:/student/attendance";
    }
    
    @GetMapping("/attendance")
    public String attendance(Model model) {
    	List<Attendance> list = attendanceService.getAllStudentAtendace();
        model.addAttribute("attendance", attendanceService.getAllStudentAtendace());
        model.addAttribute("page", "attendance");
        return "admin/attendance";
    }
    
    @GetMapping("/attendance/delete/{id}")
    public String deleteAttendance(@PathVariable Long id) {

        attendanceService.deleteById(id);

        return "redirect:/admin/attendance?deleted";
    }
    
    @GetMapping("/attendance/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {

        Attendance attendance = attendanceService.getById(id);

        model.addAttribute("attendance", attendance);

        return "admin/edit-attendance"; // 👉 नवीन JSP page
    }
    
    @PostMapping("/attendance/update")
    public String updateAttendance(@ModelAttribute Attendance attendance) {

        attendanceService.save(attendance);

        return "redirect:/admin/attendance?updated=true";
    }

}
