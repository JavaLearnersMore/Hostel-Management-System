package com.hostel.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hostel.repository.DashboardRepository;

@Service
public class DashboardService {

    @Autowired
    private DashboardRepository repo;

    public Map<String, Integer> getStats() {
        Map<String, Integer> map = new HashMap<>();
        map.put("students", repo.countStudents());
        map.put("rooms", repo.countRooms());
        map.put("fees", repo.countFeesPaid());
        map.put("visitors", repo.countVisitors());
        return map;
    }
}
