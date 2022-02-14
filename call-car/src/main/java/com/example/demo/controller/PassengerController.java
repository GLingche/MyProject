package com.example.demo.controller;

import com.example.demo.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/passenger")
public class PassengerController {

    @Autowired
    PassengerService passengerService;

    @RequestMapping("/selectColor")
    public String selectColor(Integer id){return passengerService.selectColor(id);}

    @RequestMapping("/registerInfo")
    public void insertInfo(@RequestBody Map<String,Object> passengerInfo){
        passengerService.registerInfo(passengerInfo);
    }

}
