package com.example.demo.service;

import com.example.demo.controller.bean.ResultBean;
import com.example.demo.entity.Driver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 司机接口
 */
public interface DriverService {
    void registerInfo(Map<String,Object> driverInfo);

    ResultBean login(Map<String,Object> map);

    void updateLoginStatus(Integer account,Integer status);
}
