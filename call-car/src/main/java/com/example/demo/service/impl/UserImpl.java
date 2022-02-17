package com.example.demo.service.impl;

import com.example.demo.controller.bean.ResultBean;
import com.example.demo.mapper.AdminMapper;
import com.example.demo.mapper.CarMapper;
import com.example.demo.mapper.DriverMapper;
import com.example.demo.mapper.PassengerMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

public class UserImpl implements UserService {

    @Override
    public ResultBean adminCheckLogin(AdminMapper map1,Map<String, Object> map2, String password) {

       if(map2.get("account")==null ||map2.get("password")==null){
            return new ResultBean(5003, false,"账号或密码不能为空" );
        }

        else if(map2.get("password").equals(password)){
             // 更新用户登录状态
             map1.updateLoginStatus((Integer) map2.get("account"),1);
           return new ResultBean(5000,map1.login((Integer) map2.get("account")),"登录成功");
        }

        else return new ResultBean(5001,false,"账号或密码错误");
    }

    @Override
    public ResultBean passengerCheckLogin(PassengerMapper map1, Map<String, Object> map2, String password) {
        if (map2.get("account")==null ||map2.get("password")==null){
            return new ResultBean(5003, false,"账号或密码不能为空" );
        }

       else if(map2.get("password").equals(password)){
            // 更新用户登录状态
            map1.updateLoginStatus((Integer) map2.get("account"),1);

            return new ResultBean(5000,map1.login((Integer) map2.get("account")),"登录成功");
        }

        else return new ResultBean(5001,false,"账号或密码错误");
    }

    @Override
    public ResultBean DriverCheckLogin(DriverMapper map1, Map<String, Object> map2, String password) {
        if (map2.get("account")==null ||map2.get("password")==null){
            return new ResultBean(5003, false,"账号或密码不能为空" );
        }

        else if(map2.get("password").equals(password)){
            // 更新用户登录状态
            map1.updateLoginStatus((Integer) map2.get("account"),1);
            return new ResultBean(5000,map1.login((Integer) map2.get("account")),"登录成功");
        }

        else return new ResultBean(5001,false,"账号或密码错误");
    }

}
