package com.hostel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
    	System.out.println("LoginController....");
        return "common/login"; // maps to /WEB-INF/jsp/login.jsp
    }
}
