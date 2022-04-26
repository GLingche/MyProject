package com.example.demo.mapper;

import com.example.demo.entity.Driver;
import com.example.demo.entity.Passenger;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DriverMapper {

    void registerInfo(Map<String,Object> driverInfo);

    Driver login(Integer account);

    void updateLoginStatus(Integer account,Integer status);

    //导出司机信息
    List<Object> login();
}
