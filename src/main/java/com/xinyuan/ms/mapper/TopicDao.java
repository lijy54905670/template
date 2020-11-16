package com.xinyuan.ms.mapper;

import com.xinyuan.ms.entity.Topic;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TopicDao extends BaseJpaRepository<Topic,Long> {

    /**
     * 分类排序
     */
    @Modifying
    @Query(value = "update tb_classify set sort = :sort where id = :id ",nativeQuery = true)
    void updateSorts(@Param("id") Long id,@Param("sort") Integer sort);
}
