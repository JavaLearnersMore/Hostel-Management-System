package com.hostel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hostel.entity.Student;
import com.hostel.exception.ResourceNotFoundException;
import com.hostel.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // 🔹 find student by username (used for login)
    public Student findByUsername(String username) {
        return studentRepository.findByUsername(username)
        		.orElseThrow(() -> new ResourceNotFoundException(
	                    "Student not found with this username: " + username));    
        }

    // 🔹 get student id
    public Long getStudentIdByUsername(String username) {
        return findByUsername(username).getId();
    }

    // 🔹 admin usage
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public long count() {
        return studentRepository.count();
    }

	public void save(Student student) {
		studentRepository.save(student);
		
	}

	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		//studentRepository.deleteById(id);
		Optional<Student> student = studentRepository.findById(id);

        if (!student.isPresent()) {
            throw new ResourceNotFoundException(
                    "Student not found with ID: " + id);
        }

        studentRepository.deleteById(id);
    }
		

	

	public Student getById(Long id) {
		return studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
	                    "Student not found with this id: " + id)); 
	}
}