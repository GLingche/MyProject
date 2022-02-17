package com.example.demo.mapper;

import com.example.demo.entity.Car;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
@Repository
public interface CarMapper {
    void registerInfo(Map<String,Object> carInfo);

    List<Car>  searchInfo();
}
