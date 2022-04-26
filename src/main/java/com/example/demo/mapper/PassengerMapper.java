package com.example.demo.mapper;

import com.example.demo.entity.Passenger;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PassengerMapper {
    Passenger login(Integer account);

    void registerInfo(Map<String,Object> passengerInfo);

    void updateLoginStatus(Integer account,Integer status);

    //导出乘客信息
    List<Object> login();
}
