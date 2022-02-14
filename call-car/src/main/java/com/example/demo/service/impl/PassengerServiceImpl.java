package com.example.demo.service.impl;

import com.example.demo.mapper.PassengerMapper;
import com.example.demo.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("passengerService")
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    PassengerMapper passengerMapper;

    @Override
    public String selectColor(Integer id) {
        return passengerMapper.selectColor((id));
    }

    @Override
    public HashMap<String,Object> selectInfo() {
        return passengerMapper.selectInfo();
    }

    @Override
    public void registerInfo(Map<String,Object> passengerInfo) {
        passengerMapper.registerInfo(passengerInfo);
    }


}
