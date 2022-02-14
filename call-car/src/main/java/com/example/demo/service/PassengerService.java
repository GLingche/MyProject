package com.example.demo.service;

import com.example.demo.entity.Driver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface PassengerService {


     String  selectColor(Integer id);

     HashMap<String,Object> selectInfo();

     void registerInfo(Map<String,Object> passengerInfo);
}
