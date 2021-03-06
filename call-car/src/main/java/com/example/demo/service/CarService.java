package com.example.demo.service;

import com.example.demo.entity.Car;
import com.example.demo.entity.DriverIntroduce;

import java.util.List;
import java.util.Map;
/**
 * 车辆接口
 */
public interface CarService {

    void registerInfo(Map<String,Object> carInfo);

    List<Car> searchInfo();

    void updateServiceStatus(Object carID,Object isService);

    List<DriverIntroduce> searchIntroduce(Integer carID);
}
