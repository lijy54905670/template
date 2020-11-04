package com.xinyuan.ms.mapper;

import com.xinyuan.ms.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionDao extends JpaRepository<Option,Long> {

    //通过问题id查找选项
    List<Option> findOptionByQIdEquals(Long qId);

}
