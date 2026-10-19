package com.hostel.service;

import java.util.List;

import com.hostel.entity.Fees;

	public interface FeesService {
		List<Fees> getAllFees();

		void deleteById(Long id);

		Fees getById(Long id);

		void save(Fees fee);

		

		
	}


