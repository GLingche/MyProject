package com.example.demo.service.impl;

import com.example.demo.controller.bean.ResultBean;
import com.example.demo.mapper.PassengerMapper;
import com.example.demo.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("passengerService")
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    PassengerMapper passengerMapper;


    @Override
    public ResultBean login(Map<String, Object> map) {
        if(passengerMapper.login((Integer) map.get("account")) == null){
            return new ResultBean(5002,false,"账号不存在");
        }
        return  new UserImpl().passengerCheckLogin(passengerMapper,map,passengerMapper.login((Integer) map.get("account")).getPassword());
    }

    @Override
    public void registerInfo(Map<String,Object> passengerInfo) {
        passengerMapper.registerInfo(passengerInfo);
    }


    @Override
    public void updateLoginStatus(Integer account, Integer status) {
        passengerMapper.updateLoginStatus(account,status);
    }

}
