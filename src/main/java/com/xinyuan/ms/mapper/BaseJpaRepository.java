package com.xinyuan.ms.mapper;

import com.xinyuan.ms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * 基础Dao层(放一些通用方法)
 *
 * @author
 * @since 2018-03-05
 */

@NoRepositoryBean
public interface BaseJpaRepository<T, ID extends Serializable> extends JpaRepository<T, ID>,JpaSpecificationExecutor {
    List<User> findUserByDeletedEquals(int i);
    /*一个接口可以继承多个接口*/

}
