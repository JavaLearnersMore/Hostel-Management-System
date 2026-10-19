package com.hostel.controller;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hostel.entity.Payment;
import com.hostel.entity.Room;
import com.hostel.entity.Student;
import com.hostel.repository.PaymentRepository;
import com.hostel.service.PaymentService;
import com.hostel.service.StudentService;


@Controller
@RequestMapping("/student")
public class PaymentController {

	@Autowired
	private PaymentRepository paymentRepo;

	@Autowired
	private StudentService studentservice;

	@Autowired private PaymentService paymentService;
	// @Autowired private StudentService studentService;

	@GetMapping("/pay-fee")
	public String payFeePage(Model model, Principal principal){
		Student student = studentservice.findByUsername(principal.getName());
		model.addAttribute("fee", student.getRoom().getFee());
		return "pay-fee";
	}

	@PostMapping("/pay-fee")
	public String payFee(Principal principal){

		Student student = studentservice.findByUsername(principal.getName());

		Payment p = new Payment();
		p.setStudent(student);
		p.setRoom(student.getRoom());
		p.setAmount(student.getRoom().getFee());
		p.setPayDate(LocalDateTime.now());
		p.setStatus("SUCCESS");

		paymentRepo.save(p);

		return "redirect:/student/dashboard";
	}

	@GetMapping("/payments")
	public String payments(Model model, Principal principal){
		//Student student = studentservice.findByUsername(principal.getName());
		
		Student student = Optional.ofNullable(studentservice.findByUsername(principal.getName()))
		        .orElseThrow(() -> new RuntimeException("Student not found"));
		
		Double fee = Optional.ofNullable(student)
		        .map(Student::getRoom)
		        .map(Room::getFee)
		        .orElse(null);

		if (fee == null) {
			return "redirect:/student/payments/fail";
		}
		model.addAttribute("fee", student.getRoom().getFee());
		return "student/payments";
	}
	
	@GetMapping("/payments/fail")
	public String paymentsFail(Model model, Principal principal){
		//Student student = studentservice.findByUsername(principal.getName());
		
		return "student/FailPayment";
	}

	@PostMapping("/pay")
	public String payFees(@RequestParam Double amount, Principal principal){
		//Student student = studentservice.findByUsername(principal.getName());
		
		Student student = Optional.ofNullable(studentservice.findByUsername(principal.getName()))
		        .orElseThrow(() -> new RuntimeException("Student not found"));
		
		Double fee = Optional.ofNullable(student)
		        .map(Student::getRoom)
		        .map(Room::getFee)
		        .orElse(null);

		if (fee == null) {
			return "student/payments?success=fail";
		}

		System.out.println("Student id : "+student.getId());
		System.out.println("Student room fee : "+student.getRoom().getFee());
		
		Double roomFee = student.getRoom().getFee();
		Double paidFee = amount;
		
		if(amount > roomFee) {
			return "redirect:/student/payments?success=false";	
		}else {
		
		Payment p = new Payment();
		p.setStudent(student);
		p.setRoom(student.getRoom());
		//p.setAmount(student.getRoom().getFee());
		p.setAmount(amount);
		p.setPayDate(LocalDateTime.now());
		p.setStatus("SUCCESS");

		paymentRepo.save(p);
		}
		return "redirect:/student/payments?success=true";
	}
	
	
	@PostMapping("/payNew")
	public String payFeesNew(@RequestParam Double amount, Principal principal){
		Student student = studentservice.findByUsername(principal.getName());

		System.out.println("Student id : "+student.getId());
		System.out.println("Student room fee : "+student.getRoom().getFee());
		Payment p = new Payment();
		p.setStudent(student);
		p.setRoom(student.getRoom());
		p.setAmount(amount);
		p.setPayDate(LocalDateTime.now());
		p.setStatus("SUCCESS");

		paymentRepo.save(p);
		return "redirect:/student/payments?success=true";
	}
}
