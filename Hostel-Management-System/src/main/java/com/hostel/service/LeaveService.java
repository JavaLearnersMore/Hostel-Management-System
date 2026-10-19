package com.hostel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hostel.entity.LeaveRequest;
import com.hostel.repository.LeaveRepository;

@Service
public class LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

    public int getApprovedLeaveCount(Long studentId) {
        return leaveRepository.countByStudentIdAndStatus(studentId, "APPROVED");
    }

	public List<LeaveRequest> getAllLeaves() {
		return leaveRepository.findAll();
	}
    
    

    

	
   }
