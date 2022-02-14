package com.example.demo.controller;

import com.example.demo.service.CarService;
import com.example.demo.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/car")
public class CarController {
    @Autowired
    CarService carService;

    @RequestMapping("/registerInfo")
    public void registerInfo(@RequestBody Map<String,Object> carInfo){
        carService.registerInfo(carInfo);
    }
}
