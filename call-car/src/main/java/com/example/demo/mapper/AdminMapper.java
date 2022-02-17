package com.example.demo.mapper;

import com.example.demo.entity.Address;
import com.example.demo.entity.Admin;
import com.example.demo.entity.Passenger;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AdminMapper {
    Admin login(Integer account);

    List<Address> searchAddress();

     void updateLoginStatus(Integer account,Integer status);
}
