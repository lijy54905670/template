package com.xinyuan.ms.mapper;

import com.xinyuan.ms.entity.Person;
import com.xinyuan.ms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDao extends JpaRepository<Person,Long> {

    //通过用户名查找用户
    Person findPersonByUserNameEquals(String userName);
}
