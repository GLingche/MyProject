package com.example.demo.service.impl;

import com.example.demo.controller.bean.ResultBean;
import com.example.demo.entity.Admin;
import com.example.demo.mapper.AdminMapper;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public List<Admin> login(Map<String,Object> map) {


            if(adminMapper.login() == null){
                return null;
            }

            if (map.get("account")==null || map.get("password")==null){
                return null;
            }

            /*
                Integer本身是一个对象，比较两个Integer时不能用 == 要用equals
                但比较Integer与int可用 == （jvm会自动将Integer转成int数值进行比较）
             */

            if(adminMapper.login().get(0).getAccount().equals(map.get("account"))&&adminMapper.login().get(0).getPassword().equals(map.get("password"))){
                return adminMapper.login();
            }
            return null;
    }

}
