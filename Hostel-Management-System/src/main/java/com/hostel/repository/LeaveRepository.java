package com.hostel.repository;


import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.hostel.entity.LeaveRequest;

@Repository
public interface LeaveRepository extends JpaRepository<LeaveRequest, Long> {

    // 🔹 Get all leaves of a student
    List<LeaveRequest> findByStudentId(Long studentId);

    // 🔹 Count approved leaves (used in student dashboard)
    int countByStudentIdAndStatus(Long studentId, String status);

    // 🔹 Get all pending leave requests (admin approval panel)
    List<LeaveRequest> findByStatus(String status);

    // 🔹 Get leaves between dates (optional analytics)
    List<LeaveRequest> findByFromDateBetween(
            java.time.LocalDate start,
            java.time.LocalDate end);

}