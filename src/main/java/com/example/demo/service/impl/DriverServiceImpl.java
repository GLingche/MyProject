package com.example.demo.service.impl;

import com.example.demo.controller.bean.ResultBean;
import com.example.demo.entity.Driver;
import com.example.demo.mapper.DriverMapper;
import com.example.demo.service.DriverService;
import com.example.demo.utils.ExportExcel;
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

    @Override
    public void exportDriverInfo() {
        new ExportExcel<String>().exportExcel(new String[]{"姓名","性别","账号","身份证号","年龄","密码","车辆id","状态"},driverMapper.login(),"司机记录");
    }

}
