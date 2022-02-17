package com.example.demo.controller;


import com.example.demo.controller.bean.ResultBean;
import com.example.demo.entity.Address;
import com.example.demo.entity.Admin;
import com.example.demo.mapper.AdminMapper;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired

    AdminService adminService;


    @RequestMapping("/login")
    public ResultBean login(@RequestBody Map<String,Object> map){
        return adminService.login(map);
    }

    @RequestMapping("/searchAddress")
    public ResultBean  searchAddress(){ return adminService.searchAddress();}


    @RequestMapping("/unLogin")
    public void unLogin( @RequestBody Map<String,Object> map){adminService.updateLoginStatus((Integer)map.get("account"), 0);}
}
