package com.hostel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hostel.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    // ✅ total collected
    @Query("SELECT COALESCE(SUM(p.amount),0) FROM Payment p WHERE p.status = 'SUCCESS'")
    double getTotalCollectedAmount();

    // ✅ total paid by one student
    @Query("SELECT COALESCE(SUM(p.amount),0) FROM Payment p WHERE p.student.id = :studentId AND p.status='SUCCESS'")
    double getTotalPaidByStudent(Long studentId);

    // ✅ pending amount
    @Query("""
           SELECT (COALESCE(s.totalFee,0) -
                   COALESCE(
                       (SELECT SUM(p.amount)
                        FROM Payment p
                        WHERE p.student.id = s.id
                        AND p.status='SUCCESS'),0))
           FROM Student s
           WHERE s.id = :studentId
           """)
    Double getPendingAmount(Long studentId);
}