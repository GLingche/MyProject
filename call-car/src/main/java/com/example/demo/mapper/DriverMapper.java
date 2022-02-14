package com.example.demo.mapper;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public interface DriverMapper {
    void registerInfo(Map<String,Object> driverInfo);

    HashMap<String,Object> selectInfo();

}
