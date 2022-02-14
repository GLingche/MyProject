package com.example.demo.controller.bean;

/**
 * 封装返回类Bean对象
 */
public class ResultBean {
    //状态码
    Integer code;
    //返回的数据
    Object data;
    //状态信息
    String msg;

    public ResultBean(Integer code, Object data,String msg){
        this.code = code;
        this.data = data;
        this.msg = msg;
    }
}
