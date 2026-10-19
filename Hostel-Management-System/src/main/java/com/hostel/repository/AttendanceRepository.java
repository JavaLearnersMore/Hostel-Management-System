package com.hostel.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hostel.beans.StudentAttendance;
import com.hostel.entity.Attendance;
import com.hostel.entity.Student;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
	
	
	Optional<Attendance> findByStudentAndAttendDate(Student student, LocalDate attendDate);

    // ✅ Find attendance for a student on a specific date
    Optional<Attendance> findByStudentIdAndAttendDate(
            Long studentId, LocalDate attendDate);

    // ✅ Get all attendance records of a student
    List<Attendance> findByStudentId(Long studentId);

    // ✅ Count total attendance days for a student
    long countByStudentId(Long studentId);

    // ✅ Count present days for attendance percentage
    long countByStudentIdAndStatus(
            Long studentId, Attendance.Status status);

    // (Optional) Check existence — extra safety
    boolean existsByStudentIdAndAttendDate(
            Long studentId, LocalDate attendDate);
    
       @Query("""
    	    SELECT COUNT(a) FROM Attendance a
    	    WHERE a.attendDate = CURRENT_DATE
    	      AND a.status = 'PRESENT'
    	""")
    	long countTodayPresent();

    	@Query("""
    	    SELECT COUNT(a) FROM Attendance a
    	    WHERE a.attendDate = CURRENT_DATE
    	""")
    	long countTodayTotal();
    	
//    	@Query(""" 
//    	select s.id,s.name,s.mobile_number, a.date, a.status, a.attend_date from students s inner join attendance a on s.id = a.student_id order by a.attend_date asc
//    	""")
  //  	List<StudentAttendance> getStudentAttendancee();
}
