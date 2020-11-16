package com.xinyuan.ms.mapper;

import com.xinyuan.ms.entity.Question;
import com.xinyuan.ms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends BaseJpaRepository<Question, Long> {

    //查找没有被删除的全部问题，按id排序
    List<Question> findQuestionsByDeletedOrderById(int deleted);
}
