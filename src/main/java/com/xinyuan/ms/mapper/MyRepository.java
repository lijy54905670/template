package com.xinyuan.ms.mapper;

import com.xinyuan.ms.entity.User;

/**
 * create by: ylj
 * create time: 2020/10/30 9:08
  * @Param: null
 * @return
 * 使用@Query执行自定义SQL
 */
public interface MyRepository extends BaseJpaRepository<User,Long>{
}
