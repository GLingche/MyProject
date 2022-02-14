package com.example.demo.service;

import com.example.demo.entity.Admin;

import java.util.List;
import java.util.Map;

public interface AdminService {
    List<Admin> login(Map<String,Object> map);
}
