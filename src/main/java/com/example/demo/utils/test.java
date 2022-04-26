package com.example.demo.utils;

import com.example.demo.entity.KidCar;
import com.example.demo.service.impl.UserImpl;

public class test {
    public static Integer sortCarStack() {
        //获取单例对象
        Stack<KidCar> stack = Stack.getInstance();
        System.out.println(stack.size()+"###################");
        // 如果栈的数量大于等于2则进行排序
        if(stack.size()>=2){
            return new UserImpl().sort(stack);
        }
        //否则返回该元素的下标
        System.out.println(stack.size()+"@@@@@@@@@@@@@@@@@@@@");
        Integer result = stack.pop().getIndex();

        return result;
    }
    public static void main(String[] args) {

        Stack<KidCar> stack = Stack.getInstance();

        stack.push(new KidCar(1,1001,3));
        stack.push(new KidCar(1,1001,3));
        stack.push(new KidCar(1,1001,3));
        stack.push(new KidCar(1,1001,3));


        System.out.println(sortCarStack());

    }
}
