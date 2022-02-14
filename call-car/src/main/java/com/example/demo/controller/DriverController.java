package com.example.demo.controller;

import com.example.demo.service.DriverService;
import com.example.demo.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    DriverService driverService;

        @RequestMapping("/selectInfo")
    public HashMap<String,Object> selectInfo(){return driverService.selectInfo();}



      @RequestMapping("/registerInfo")
      public void registerInfo(@RequestBody Map<String,Object> driverInfo){
          driverService.registerInfo(driverInfo);
      }
}
