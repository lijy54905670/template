//package com.xinyuan.ms.web.controller;
//
//import com.xinyuan.ms.entity.User;
//import com.xinyuan.ms.service.MyRepositoryImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/my")
//public class MyController {
//
//    @Autowired
//    MyRepositoryImpl myRepository;
//
//    @RequestMapping("/count")
//    public Long query(){
//
//        return myRepository.count();
//    }
//
//    @RequestMapping("/findAll")
//    public List<User> queryAll(){
//
//        return myRepository.findAll();
//    }
//
//}
