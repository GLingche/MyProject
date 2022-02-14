package com.example.demo.mapper;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
@Repository
public interface CarMapper {
    void registerInfo(Map<String,Object> carInfo);
}
