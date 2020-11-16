package com.xinyuan.ms.mapper;

import com.xinyuan.ms.entity.Classify;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClassifyDao extends BaseJpaRepository<Classify,Long> {

    /**
     * 查询父节点下最大的排序号
     * @param sId
     * @return
     */
    @Query(value = "select max(sort) from tb_classify where s_id = :sId",nativeQuery = true)
    Integer maxSort(@Param("sId") Long sId);
}
