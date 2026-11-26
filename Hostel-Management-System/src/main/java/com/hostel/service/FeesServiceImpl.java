package com.hostel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hostel.entity.Fees;
import com.hostel.exception.ResourceNotFoundException;
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
		
		 Fees fee = feesRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException(
	                        "Fee record not found with ID: " + id));
		
		feesRepository.delete(fee);
		//feesRepository.deleteById(id);
	}
    
	@Override
	public Fees getById(Long id) {
	    return feesRepository.findById(id)
	    		.orElseThrow(() -> new ResourceNotFoundException(
  	                    "fees not found with this id: " + id)); 
	}

	@Override
	public void save(Fees fee) {
	    feesRepository.save(fee);
	}
    

    
   
   
}