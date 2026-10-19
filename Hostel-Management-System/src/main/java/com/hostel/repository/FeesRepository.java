package com.hostel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hostel.entity.Fees;

public interface FeesRepository extends JpaRepository<Fees, Long> {
}