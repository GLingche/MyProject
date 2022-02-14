package com.example.demo.service.impl;

import com.example.demo.mapper.DriverMapper;
import com.example.demo.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service("driverService")
public class DriverServiceImpl implements DriverService {

    @Autowired
    DriverMapper driverMapper;

    @Override
    public void registerInfo(Map<String,Object> driverInfo) {
        driverMapper.registerInfo(driverInfo);
    }

    @Override
    public HashMap<String, Object> selectInfo() {
        return driverMapper.selectInfo();
    }


}
