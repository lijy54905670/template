package com.xinyuan.ms.service;

import com.xinyuan.ms.entity.User;
import com.xinyuan.ms.mapper.MyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyRepositoryImpl {
    @Autowired
    MyRepository myRepository;

    public Long count(){
       return myRepository.findById1();
    }

    public List<User> findAll(){
        return myRepository.findAll1();
    }

}
