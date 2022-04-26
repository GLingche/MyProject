package com.example.demo.entity;

public class KidCar extends Car{
    //车辆的唯一标识
    private Integer index;
    //车辆离乘客的距离
    private Integer distance;
    //总权值
    private int weight = 0;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight += weight;
    }

    public KidCar(){};

    public KidCar(Integer index, Integer distance,Integer age) {
        super();
        this.index = index;
        this.distance = distance;
        super.setAge(age);
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    //车龄
    public  Integer getAge(){
        return super.getAge();
    }


    @Override
    public String toString() {
        return "KidCar{" +
                "index=" + index +
                ", distance=" + distance +
              ", age=" + getAge() + ", weight=" + weight +
                '}';
    }
}
