package com.hostel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hostel.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    // 🔐 Used by Spring Security login
    Optional<Admin> findByUsername(String username);

    // ⚡ Faster existence check during login
    boolean existsByUsername(String username);
}
