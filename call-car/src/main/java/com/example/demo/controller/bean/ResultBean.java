package com.example.demo.controller.bean;

/**
 * 封装返回类Bean对象
 */
public class ResultBean {
    //状态码
    Integer code;
    //返回的数据
    Object values;
    //状态信息
    String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getValues() {
        return values;
    }

    public void setValues(Object values) {
        this.values = values;
    }

    public ResultBean(Integer code, Object values, String msg){
        this.code = code;
        this.values = values;
        this.msg = msg;
    }
}
