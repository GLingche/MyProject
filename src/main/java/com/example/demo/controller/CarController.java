package com.example.demo.controller;

import com.example.demo.entity.Car;
import com.example.demo.entity.DriverIntroduce;
import com.example.demo.entity.KidCar;
import com.example.demo.service.CarService;
import com.example.demo.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Queue;


@RestController
@RequestMapping("/car")
public class CarController {
    @Autowired
    CarService carService;

    @RequestMapping("/registerInfo")
    public void registerInfo(@RequestBody Map<String,Object> carInfo){
        carService.registerInfo(carInfo);
    }

    @RequestMapping("/searchInfo")
    public List<Car> searchInfo(){return carService.searchInfo();}

    @RequestMapping("/updateServiceStatus")
    public void updateServiceStatus(@RequestBody Map<String,Object> map){carService.updateServiceStatus(map.get("carID"),map.get("isService"));}

    @RequestMapping("/searchIntroduce")
    public List<DriverIntroduce> searchIntroduce(Integer carID){return carService.searchIntroduce(carID);}


    @RequestMapping("/pushStack")
    public void deQueue(Integer index, Integer carAge, Integer distance) {carService.pushStack(index,carAge,distance);}

    @RequestMapping("/sortCarStack")
    public Integer sortCarQueue(){
        return carService.sortCarStack();
    }
}
