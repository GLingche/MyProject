package com.example.demo.service;

import com.example.demo.controller.bean.ResultBean;
import com.example.demo.entity.Address;
import com.example.demo.entity.Admin;

import java.util.List;
import java.util.Map;

/**
 * 管理员接口
 */
public interface AdminService {
    ResultBean login(Map<String,Object> map);

    ResultBean searchAddress();

    void updateLoginStatus(Integer account,Integer status);
}


