package com.xinyuan.ms.web.controller;

import com.xinyuan.ms.entity.Person;
import com.xinyuan.ms.service.PersonService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

    /**
     * 用户登录
     * @param person 用户信息
     * @return
     */
    @ApiOperation(value = "登录", notes = "登录") //给这个方法添加说明
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestBody Person person){
        //System.out.println(person.getUserName() + person.getPassword() + "1231231");
        personService.login();
        return "ok";
    }
}
