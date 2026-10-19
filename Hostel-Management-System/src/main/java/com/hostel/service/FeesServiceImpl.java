package com.hostel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hostel.entity.Fees;
import com.hostel.repository.FeesRepository;
import com.hostel.service.FeesService;

@Service
public class FeesServiceImpl implements FeesService {

    @Autowired
    private FeesRepository feesRepository;

    public List<Fees> getAllFees() {
        return feesRepository.findAll();
    }

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		feesRepository.deleteById(id);
	}
    
	@Override
	public Fees getById(Long id) {
	    return feesRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Fee not found"));
	}

	@Override
	public void save(Fees fee) {
	    feesRepository.save(fee);
	}
    

    
   
   
}