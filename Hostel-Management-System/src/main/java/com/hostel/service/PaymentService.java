package com.hostel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hostel.entity.Payment;
import com.hostel.entity.Student;
import com.hostel.repository.PaymentRepository;
import com.hostel.repository.StudentRepository;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private PaymentRepository paymentRepo;

    // ✅ TOTAL COLLECTION (Admin dashboard)
    public double getTotalCollectedAmount() {
        return paymentRepository.getTotalCollectedAmount();
    }

    // ✅ SAVE PAYMENT (when student pays fee)
    public void savePayment(Student student, Double amount) {

        Payment payment = new Payment();
        payment.setStudent(student);
        payment.setAmount(amount);
        payment.setStatus("SUCCESS");

        paymentRepository.save(payment);
    }

    // ✅ TOTAL PAID BY STUDENT
    public double getTotalPaidByStudent(Long studentId) {
        Double paid = paymentRepository.getTotalPaidByStudent(studentId);
        return paid == null ? 0 : paid;
    }

    // ✅ PENDING FEES
    public double getPendingFees(Long studentId) {
        Double pending = paymentRepository.getPendingAmount(studentId);
        return pending == null ? 0 : pending;
    }
    
    public double calculateDue(Student student){
    	double rent = student.getRoom().getFee();
    	 Double paid = paymentRepo.getTotalPaidByStudent(student.getId());

        if(paid == null) paid = 0.0;

        return rent - paid;
    }
}