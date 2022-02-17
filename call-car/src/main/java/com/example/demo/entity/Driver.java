package com.example.demo.entity;

public class Driver {
    private String name,sex,password;
    private Integer account,age,identity_id,carID,status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getIdentity_id() {
        return identity_id;
    }

    public void setIdentity_id(Integer identity_id) {
        this.identity_id = identity_id;
    }

    public Integer getCarID() {
        return carID;
    }

    public void setCarID(Integer carID) {
        this.carID = carID;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
