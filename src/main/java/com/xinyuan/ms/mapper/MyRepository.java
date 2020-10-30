package com.xinyuan.ms.mapper;

import com.xinyuan.ms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * create by: ylj
 * create time: 2020/10/30 9:08
  * @Param: null
 * @return
 * 使用@Query执行自定义SQL
 */

public interface MyRepository extends JpaRepository<User,Long> {
    /**
     * 查询数据库记录数目
     * @return
     */
    @Query(nativeQuery =true ,value = "select count(*) from sys_user")
    Long findById1();

    /**
     * 查询所有
     * @return
     */
    @Query(nativeQuery =true,value = "select * from sys_user")
    List<User> findAll1();
}
