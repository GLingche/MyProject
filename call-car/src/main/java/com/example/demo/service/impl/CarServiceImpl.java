package com.example.demo.service.impl;

import com.example.demo.entity.Car;
import com.example.demo.mapper.CarMapper;
import com.example.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service("carService")
public class CarServiceImpl implements CarService {

    @Autowired
    CarMapper carMapper;

    @Override
    public void registerInfo(Map<String, Object> carInfo) {
        carMapper.registerInfo(carInfo);
    }

    @Override
    public List<Car> searchInfo() {
        return carMapper.searchInfo();
    }
}
