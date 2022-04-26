package com.example.demo.service.impl;

import com.example.demo.controller.bean.ResultBean;
import com.example.demo.mapper.PassengerMapper;
import com.example.demo.service.PassengerService;
import com.example.demo.utils.ExportExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public void exportPassengerInfo() {
       new ExportExcel<String>().exportExcel(new String[]{"姓名", "密码","性别","身份证号","账号","年龄","状态","乘客id"},passengerMapper.login(), "乘客记录");
    }

}
