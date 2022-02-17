package com.example.demo.service.impl;

import com.example.demo.controller.bean.ResultBean;
import com.example.demo.entity.Driver;
import com.example.demo.mapper.DriverMapper;
import com.example.demo.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("driverService")
public class DriverServiceImpl implements DriverService {

    @Autowired
    DriverMapper driverMapper;

    @Override
    public void registerInfo(Map<String,Object> driverInfo) {
        driverMapper.registerInfo(driverInfo);
    }

    @Override
    public ResultBean login(Map<String, Object> map) {
        if(driverMapper.login((Integer) map.get("account")) == null){
            return new ResultBean(5002,false,"账号不存在");
        }
        return  new UserImpl().DriverCheckLogin(driverMapper,map,driverMapper.login((Integer) map.get("account")).getPassword());
    }

    @Override
    public void updateLoginStatus(Integer account, Integer status) {
        driverMapper.updateLoginStatus(account,status);
    }

}
