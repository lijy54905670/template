package com.xinyuan.ms.service;

import com.xinyuan.ms.entity.Status;
import com.xinyuan.ms.mapper.StatusDao;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;

@Service
public class StatusService extends BaseService<StatusDao, Status,Long> {

    /**
     * 根据状态id查找状态名
     */
    public String findNameById(int id){
        Status status = get((long) id);
        return status.getStatus();
    }
}
