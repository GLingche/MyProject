package com.example.demo.service;

import com.example.demo.controller.bean.ResultBean;
import com.example.demo.entity.Driver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 乘客接口
 */
public interface PassengerService {

     ResultBean login(Map<String,Object> map);

     void registerInfo(Map<String,Object> passengerInfo);

     void updateLoginStatus(Integer account,Integer status);
}
