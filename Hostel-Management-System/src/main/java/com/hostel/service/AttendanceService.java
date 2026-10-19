package com.hostel.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.hostel.entity.Attendance;
import com.hostel.entity.Student;
import com.hostel.repository.AttendanceRepository;
import com.hostel.repository.StudentRepository;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepo;

    @Autowired
    private StudentRepository studentRepo;

	private CrudRepository<Attendance, Long> attendanceRepository;

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public void markAttendance(Long studentId, String status) {

    	  Student student = studentRepo.findById(studentId)
    	            .orElseThrow(() -> new RuntimeException("Student not found"));

    	    LocalDate today = LocalDate.now();

    	    Optional<Attendance> optional =
    	            attendanceRepo.findByStudentIdAndAttendDate(studentId, today);

    	    Attendance attendance;

    	    if (optional.isPresent()) {
    	        attendance = optional.get();
    	    } else {
    	        attendance = new Attendance();
    	        attendance.setStudent(student);

    	        // ✅ SET BOTH DATES
    	        attendance.setAttendDate(today);
    	        attendance.setDate(today);   // 🔥 ADD THIS LINE
    	    }

    	    attendance.setStatus(Attendance.Status.valueOf(status));

    	    attendanceRepo.save(attendance);
    }
    
    public double getTodayAttendancePercentage() {

        long total = attendanceRepo.countTodayTotal();
        if (total == 0) return 0;

        long present = attendanceRepo.countTodayPresent();
        return (present * 100.0) / total;
    }
    public int getAttendancePercentage(Long studentId) {

        long totalDays = attendanceRepo.countByStudentId(studentId);
        long presentDays = attendanceRepo.countByStudentIdAndStatus(
                studentId, Attendance.Status.PRESENT);

        if (totalDays == 0) {
            return 0;
        }

        return (int) ((presentDays * 100.0) / totalDays);
    }
    
//    public List<StudentAttendance> getStudentAttendance(){
//    	return repo.getStudentAttendancee();
//    }
    
     public List<Attendance> getAllStudentAtendace(){
    	 return attendanceRepo.findAll();
     }

	public void deleteById(Long id) {
		// TODO Auto-generated method stub
        attendanceRepo.deleteById(id);

	}

	public Attendance getById(Long id) {
		return attendanceRepo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Attendance not found"));
	
	}

	public void save(Attendance attendance) {
		// TODO Auto-generated method stub
		attendanceRepo.save(attendance);
	}
    
    

}
