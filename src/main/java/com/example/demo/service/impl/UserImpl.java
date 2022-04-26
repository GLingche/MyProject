package com.example.demo.service.impl;

import com.example.demo.controller.bean.ResultBean;
import com.example.demo.entity.KidCar;
import com.example.demo.mapper.AdminMapper;

import com.example.demo.mapper.DriverMapper;
import com.example.demo.mapper.PassengerMapper;
import com.example.demo.service.UserService;

import com.example.demo.utils.Stack;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

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

    @Override
    public Integer sort(Stack<KidCar> car) {
        //对象数组
        ArrayList<KidCar> carIndexList = new ArrayList();
        int indexWeight = 11;
        int ageWeight = 10;
        int  disWeight = 6;
            while(!car.isEmpty()){
                carIndexList.add(car.pop());
            }

        return carIndexList.size() > 3 ?sort(carIndexList) : sort(carIndexList,indexWeight,ageWeight,disWeight);
    }


    /** 当基数较多的时候
     * 以 接单次序 车龄 车距 三个权值属性进行排序
     * 基于各个权值属性的权重来进行筛选
     * (W)接单次序 > (W)车龄 > (W)车距
     */
    public static Integer sort(ArrayList<KidCar> carIndexList){

            Optional<KidCar> target = carIndexList.stream().skip((long) Math.floor(carIndexList.size() / 2)).sorted((c1, c2) -> {
                return c1.getAge() - c2.getAge();
            }).skip((long) Math.floor(carIndexList.size() / 4)).min((c1, c2) -> {
                if (c1.getDistance() - c2.getDistance() == 0) {
                    return c2.getAge() - c1.getAge();
                }
                return c1.getDistance() - c2.getDistance();
            });

        Integer result = target.get().getIndex();

        return  result;
    }



    /** 当基数较少的时候
     *  (W)接单次序 > (W)车龄 > (W)车距
     *  将每个属性都定义相应的具体权值（依据权重次序及排序次序）最后根据总权值大小来选择目标对象
     *  eg.(age 由大到小的权值为 8 7 6  distance由小到大: 5 4 3 ...)
     */
    public static Integer sort(ArrayList<KidCar> carIndexList,int indexWeight,int ageWeight,int disWeight){
        Integer result = 0;

        for(KidCar item : carIndexList){
          item.setWeight(indexWeight);
           indexWeight++;
        }

        //根据车龄进行排序
        carIndexList.sort((o1, o2) -> {
            return o2.getAge() - o1.getAge();
        });


        for(KidCar item : carIndexList){
            item.setWeight(ageWeight);
            ageWeight -=2;
        }

        //根据车距进行排序
        carIndexList.sort((o1, o2) -> {
            return o1.getDistance() - o2.getDistance();
        });


        for(KidCar item : carIndexList){
            item.setWeight(disWeight);
            disWeight -=2;
        }

        //计算出权值最大的目标对象
        Optional<KidCar> target = carIndexList.stream().max((o1,o2) -> {
            return o1.getWeight() - o2.getWeight();
        });


        result = target.get().getIndex();

        return result;

    }

}
