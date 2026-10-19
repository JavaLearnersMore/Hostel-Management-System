package com.hostel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hostel.entity.Room;
import com.hostel.entity.Visitor;
import com.hostel.repository.VisitorRepository;

@Service
public class VisitorService {

    @Autowired
    private VisitorRepository visitorRepository;

    public long count() {
        return visitorRepository.count();
    }
    
    public List<Visitor> getVisitorDetails() {
    	return visitorRepository.findAll();
    }
}
