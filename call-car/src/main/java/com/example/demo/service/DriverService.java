package com.example.demo.service;

import java.util.HashMap;
import java.util.Map;

public interface DriverService {
    void registerInfo(Map<String,Object> driverInfo);

    HashMap<String,Object> selectInfo();
}
