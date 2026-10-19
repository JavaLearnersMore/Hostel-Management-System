package com.hostel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.hostel.entity.Admin;
import com.hostel.entity.Student;
import com.hostel.repository.AdminRepository;
import com.hostel.repository.StudentRepository;

@Service
public class MultiUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private StudentRepository studentRepo;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        // 🔐 ADMIN LOGIN
        Admin admin = adminRepo.findByUsername(username).orElse(null);

        if (admin != null) {
            return User.builder()
                    .username(admin.getUsername())
                    .password(admin.getPassword())
                    .roles("ADMIN")
                    .disabled(!admin.isEnabled())
                    .build();
        }

        // 🎓 STUDENT LOGIN
        Student student = studentRepo.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Invalid username or password"));

        return User.builder()
                .username(student.getUsername())
                .password(student.getPassword())
                .roles("STUDENT")
                .disabled(!student.isEnabled())
                .build();
    }
}