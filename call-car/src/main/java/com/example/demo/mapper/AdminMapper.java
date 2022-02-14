package com.example.demo.mapper;

import com.example.demo.entity.Admin;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AdminMapper {
    List<Admin> login();

}
