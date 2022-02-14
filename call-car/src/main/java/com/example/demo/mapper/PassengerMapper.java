package com.example.demo.mapper;

import com.example.demo.entity.Driver;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface PassengerMapper {

    String  selectColor(Integer id);

    HashMap<String,Object> selectInfo();

    void registerInfo(Map<String,Object> passengerInfo);
}
