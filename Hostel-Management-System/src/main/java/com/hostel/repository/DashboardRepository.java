package com.hostel.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DashboardRepository {

    @Autowired
    private JdbcTemplate jdbc;

    public int countStudents() {
        return jdbc.queryForObject("SELECT COUNT(*) FROM students", Integer.class);
    }

    public int countRooms() {
        return jdbc.queryForObject("SELECT COUNT(*) FROM rooms", Integer.class);
    }

    public int countFeesPaid() {
        return jdbc.queryForObject(
            "SELECT COUNT(*) FROM payments WHERE status='SUCCESS'",
            Integer.class
        );
    }

    public int countVisitors() {
        return jdbc.queryForObject("SELECT COUNT(*) FROM visitors", Integer.class);
    }
}

