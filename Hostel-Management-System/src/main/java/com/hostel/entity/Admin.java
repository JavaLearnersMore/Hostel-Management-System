package com.hostel.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false)
    private boolean enabled = true;

    // 🔐 Role is fixed for admin
    @Column(nullable = false)
    private String role = "ROLE_ADMIN";

    /* =========================
       Constructors
       ========================= */

    public Admin() {
    }

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
        this.enabled = true;
        this.role = "ROLE_ADMIN";
    }

    /* =========================
       Getters & Setters
       ========================= */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    // ⚠️ Always store BCrypt encoded password
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRole() {
        return role;
    }

    // Role is fixed; setter optional
    public void setRole(String role) {
        this.role = role;
    }
}
