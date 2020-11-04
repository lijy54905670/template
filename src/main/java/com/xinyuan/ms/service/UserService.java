package com.xinyuan.ms.service;

import com.xinyuan.ms.entity.User;
import com.xinyuan.ms.mapper.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @author hzx
 * @date 2019/7/10 10:11
 */
@Service          // 一般用于修饰service层的组件
public class UserService extends BaseService<UserRepository, User,Long> {

    /**
     * 删除
     *
     * @param ids
     */
    public void removeList(List<Long> ids) {
        for (Long i : ids) {    //因为可能需要删除多个，，就需要一个循环，逐个删除
            remove(i);          //使用remove（)删除指定的记录（这个remove（）是他的父类BaseService中的方法）
        }
    }

}
