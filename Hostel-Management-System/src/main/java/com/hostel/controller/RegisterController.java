package com.hostel.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hostel.entity.Student;
import com.hostel.entity.User;
import com.hostel.repository.StudentRepository;
import com.hostel.repository.UserRepository;

@Controller
public class RegisterController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private StudentRepository studentRepo;
    
    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("/register")
    public String registerPage(Model model){
        model.addAttribute("user", new User());
        return "common/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
    	
    	
    	String mobileNumber = user.getMobileNumber()==null?"":user.getMobileNumber();
    	String mobile = "";
    	
    	if(!mobileNumber.contains("+91")) {
    		mobile = "+91".concat(mobileNumber);
    	}else {
    		mobile = mobileNumber;
    	}
        user.setRole(user.getRole()==null?"":user.getRole());
        user.setPassword(encoder.encode(user.getPassword()));
        user.setMobileNumber(mobile);
        user.setEnabled(true);
        user.setLoginAttempts(1);
        user.setLoginDateTime(LocalDateTime.now());
        user.setR_cre_time(LocalDateTime.now());
        user.setR_mod_time(LocalDateTime.now());
        user.setR_cre_id(user.getRole()==null?"":user.getRole());
        user.setR_mod_id(user.getRole()==null?"":user.getRole());
        
        System.out.println("user : "+user.toString());
        userRepo.save(user);

        Student student = new Student();
        student.setName(user.getFullName());
        student.setUsername(user.getUsername());
        student.setEmail(user.getEmail());
        student.setMobileNumber(mobile);
        student.setPassword(encoder.encode(user.getPassword()));

        // link user ↔ student
        student.setUser(user);
        System.out.println("student : "+student.toString());
        studentRepo.save(student);

        return "redirect:/login";
    }
}
