package com.example.demo.service.impl;

import com.example.demo.controller.bean.ResultBean;
import com.example.demo.entity.Address;
import com.example.demo.entity.Admin;
import com.example.demo.mapper.AdminMapper;
import com.example.demo.mapper.CarMapper;
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
    public ResultBean login(Map<String,Object> map){
        if(adminMapper.login((Integer) map.get("account")) == null){
            return new ResultBean(5002,false,"账号不存在");
        }
            /*
                Integer本身是一个对象，比较两个Integer时不能用 == 要用equals
                但比较Integer与int可用 == （jvm会自动将Integer转成int数值进行比较）
             */
        return  new UserImpl().adminCheckLogin(adminMapper,map,adminMapper.login((Integer) map.get("account")).getPassword());

    }

    @Override
    public ResultBean searchAddress() {
        if(adminMapper.searchAddress()!=null)
            return new ResultBean(5000,adminMapper.searchAddress(),"查询成功");

        return new ResultBean(5001,adminMapper.searchAddress(),"数据为空");
    }

    @Override
    public void updateLoginStatus(Integer account, Integer status) {
        adminMapper.updateLoginStatus(account,status);
    }

}
