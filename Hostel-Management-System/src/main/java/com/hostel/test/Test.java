package com.hostel.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {

	public static void main(String[] args) {
		String password = new BCryptPasswordEncoder().encode("admin123");
		System.out.println("Password : "+password);
	}
}
