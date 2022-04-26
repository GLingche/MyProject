package com.example.demo.service;

import com.example.demo.controller.bean.ResultBean;
import com.example.demo.entity.KidCar;
import com.example.demo.mapper.AdminMapper;
import com.example.demo.mapper.DriverMapper;
import com.example.demo.mapper.PassengerMapper;
import com.example.demo.utils.ListQueue;
import com.example.demo.utils.Stack;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.Queue;

/**
 * 共同调用接口
 */
public interface UserService {



    ResultBean adminCheckLogin(AdminMapper map1,Map<String, Object> map2, String password);

    ResultBean passengerCheckLogin(PassengerMapper map1,Map<String,Object> map2, String password);

    ResultBean DriverCheckLogin(DriverMapper map1, Map<String,Object> map2, String password);

    Integer  sort(Stack<KidCar> car);

}
