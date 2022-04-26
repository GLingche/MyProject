package com.example.demo.service.impl;

import com.example.demo.entity.Car;
import com.example.demo.entity.DriverIntroduce;
import com.example.demo.entity.KidCar;
import com.example.demo.mapper.CarMapper;
import com.example.demo.service.CarService;

import com.example.demo.service.UserService;
import com.example.demo.utils.ListQueue;
import com.example.demo.utils.Stack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Queue;

@Service("carService")
public class CarServiceImpl implements CarService {

    @Autowired
    CarMapper carMapper;

    @Override
    public void registerInfo(Map<String, Object> carInfo) {
        carMapper.registerInfo(carInfo);
    }

    @Override
    public List<Car> searchInfo() {
        return carMapper.searchInfo();
    }

    @Override
    public void updateServiceStatus(Object carID,Object isService) {
         carMapper.updateServiceStatus(carID,isService);
    }

    @Override
    public List<DriverIntroduce> searchIntroduce(Integer carID) {
        return carMapper.searchIntroduce(carID);
    }


    @Override
    public void pushStack(Integer index, Integer carAge, Integer distance) {
        //创建单例对象
        Stack<KidCar> stack = Stack.getInstance();
        stack.push(new KidCar(index,distance,carAge));
    }

    @Override
    public Integer sortCarStack() {
        //获取单例对象
        Stack<KidCar> stack = Stack.getInstance();
       // 如果栈的数量大于等于2则进行排序
        if(stack.size()>=2){
         return new UserImpl().sort(stack);
        }
        //否则返回该元素的下标
        Integer result = stack.pop().getIndex();

        return result;
    }

}
