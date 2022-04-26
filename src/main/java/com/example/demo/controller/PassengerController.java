package com.example.demo.controller;

import com.example.demo.controller.bean.ResultBean;
import com.example.demo.entity.Driver;
import com.example.demo.entity.Passenger;
import com.example.demo.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/passenger")
public class PassengerController {

    @Autowired
    PassengerService passengerService;


    @RequestMapping("/registerInfo")
    public void insertInfo(@RequestBody Map<String,Object> passengerInfo){
        passengerService.registerInfo(passengerInfo);
    }

    @RequestMapping("/login")
    public ResultBean login(@RequestBody Map<String,Object> map){
        return passengerService.login(map);
    }

    @RequestMapping("/unLogin")
    public void unLogin( @RequestBody Map<String,Object> map){
        passengerService.updateLoginStatus((Integer)map.get("account"), 0);
    }

    @RequestMapping("/exportPassengerInfo")
    public void exportDriverInfo(){ passengerService.exportPassengerInfo();}
}
