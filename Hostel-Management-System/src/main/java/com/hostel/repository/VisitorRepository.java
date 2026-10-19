package com.hostel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hostel.entity.Visitor;

public interface VisitorRepository extends JpaRepository<Visitor, Long> {
}
