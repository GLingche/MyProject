package com.example.demo.mapper;

import com.example.demo.entity.Car;
import com.example.demo.entity.DriverIntroduce;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.Queue;


@Repository
public interface CarMapper {
    void registerInfo(Map<String,Object> carInfo);

    List<Car>  searchInfo();

    void updateServiceStatus(Object carID,Object isService);

    List<DriverIntroduce> searchIntroduce(Integer carID);


}
