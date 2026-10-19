package com.hostel.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class AppErrorController {

	 @GetMapping("/error")
	    public String error() {
	        return "common/error";
	    }
}
