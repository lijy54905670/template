package com.xinyuan.ms.service;

import com.xinyuan.ms.entity.Person;
import com.xinyuan.ms.mapper.PersonDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PersonService {

    @Autowired
    PersonDao personDao;

    public void login(){
        Person person = personDao.findPersonByUserNameEquals("123");
        if (person != null){
            log.info(person.getPassword());
        }

    }
}
