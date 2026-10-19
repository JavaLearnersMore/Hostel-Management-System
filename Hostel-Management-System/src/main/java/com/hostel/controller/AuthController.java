package com.hostel.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hostel.entity.User;
import com.hostel.service.EmailService;
import com.hostel.service.SmsService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

	   @Autowired
	    private AuthenticationManager authenticationManager;

	    @Autowired
	    private com.hostel.repository.UserRepository userRepository;

	    @Autowired
	    private SmsService smsService;
	    
	    @Autowired
	    private PasswordEncoder passwordEncoder;  // Inject PasswordEncoder
	    
	    @Autowired
	    private EmailService emailService;
	    
    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication) {

        var roles = AuthorityUtils.authorityListToSet(
                authentication.getAuthorities());

        if (roles.contains("ROLE_ADMIN")) {
            return "redirect:/admin/dashboard";
        } else if (roles.contains("ROLE_STUDENT")) {
            return "redirect:/student/dashboard";
        }
        return "redirect:/access-denied";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "common/error";
    }
    
 // ✅ STEP-1: username/password → send OTP
//    @PostMapping("/send-otp-login")
//    @ResponseBody
//    public String sendOtpLogin(@RequestParam String username,
//                               @RequestParam String password,
//                               HttpSession session) {
//
//        User user = userRepository.findByUsername(username);
//
//        if (user == null) {
//            return "INVALID";
//        }
//
//        // 🔐 verify password FIRST using Spring encoder
//        if (!passwordEncoder.matches(password, user.getPassword())) {
//            return "INVALID";
//        }
//        
//     // ✅ Store logged-in user in session
//        session.setAttribute("loggedInUser", user.getFullName());  // or user.getUsername()
//        session.setAttribute("loggedInUserId", user.getId());  // optional
//
//        // ✅ Optionally, store role
//        session.setAttribute("role", user.getRole());
//
//        // ✅ generate OTP
//        String otp = String.valueOf((int)(Math.random()*900000)+100000);
//
//        // ✅ store in session (CRITICAL)
//        session.setAttribute("OTP", otp);
//        session.setAttribute("OTP_USER", username);
//        session.setAttribute("OTP_EXPIRY",LocalDateTime.now().plusMinutes(5));
//
//        System.out.println("LOGIN OTP = " + otp);
//        
//
//        // ✅ send SMS
//        smsService.sendOtp(user.getMobileNumber(), otp);
//
//        return "OTP_SENT";
//    }
    
    
    @PostMapping("/send-otp-login")
    @ResponseBody
    public String sendOtpLogin(@RequestParam String username,
                              @RequestParam String password,
                              HttpSession session) {

        //User user = userRepository.findByUsername(username);
        
        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));
        
     // ✅ Store logged-in user in session
      session.setAttribute("loggedInUser", user.getFullName());  // or user.getUsername()
      session.setAttribute("loggedInUserId", user.getId());  // optional

        if (user == null) {
            return "INVALID";
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            return "INVALID";
        }

        // ✅ Generate OTP
        String otp = String.valueOf((int)(Math.random()*900000)+100000);
        System.out.println("LOGIN OTP = " + otp);

        session.setAttribute("OTP", otp);
        session.setAttribute("OTP_USER", username);
        session.setAttribute("OTP_EXPIRY", LocalDateTime.now().plusMinutes(5));
        
        System.out.println("LOGIN OTP = " + otp);

        try {
            // ✅ SEND EMAIL
            emailService.sendOtp(user.getEmail(), otp);
        } catch (Exception e) {
            e.printStackTrace();

            // fallback
            System.out.println("OTP (fallback) = " + otp);
            return "OTP_CONSOLE";
        }

        return "OTP_SENT";
    }
    
//    @PostMapping("/verify-otp")
//    @ResponseBody
//    public String verifyOtp(@RequestParam String otp, HttpSession session) {
//
//        String sessionOtp = (String) session.getAttribute("OTP");
//
//        if (sessionOtp != null && sessionOtp.equals(otp)) {
//            return "SUCCESS";
//        }
//
//        return "INVALID_OTP";
//    }
    
}
