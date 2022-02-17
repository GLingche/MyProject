package com.example.demo.entity;

public class Car {
    private String color,type,code;
    private Integer age;
    private Integer carID;

    public Integer getIsService() {
        return isService;
    }

    public void setIsService(Integer isService) {
        this.isService = isService;
    }

    private Integer isService;

    public String getColor() {
        return color;
    }

    public Integer getCarID() {
        return carID;
    }

    public void setCarID(Integer carID) {
        this.carID = carID;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
