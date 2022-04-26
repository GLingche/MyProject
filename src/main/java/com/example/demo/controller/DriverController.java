package com.example.demo.controller;

import com.example.demo.controller.bean.ResultBean;
import com.example.demo.entity.Driver;
import com.example.demo.service.DriverService;
import com.example.demo.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    DriverService driverService;

      @RequestMapping("/registerInfo")
      public void registerInfo(@RequestBody Map<String,Object> driverInfo){
            driverService.registerInfo(driverInfo);
      }

      @RequestMapping("/login")
      public ResultBean login(@RequestBody Map<String,Object> map){
        return driverService.login(map);
    }

      @RequestMapping("/unLogin")
      public void unLogin( @RequestBody Map<String,Object> map){driverService.updateLoginStatus((Integer)map.get("account"), 0);}

      @RequestMapping("/exportDriverInfo")
      public void exportDriverInfo(){ driverService.exportDriverInfo();}
}
