package com.xinyuan.ms.mapper;

import com.xinyuan.ms.entity.Answer;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerDao extends BaseJpaRepository<Answer,Long> {

    //通过qid和uid查看答题记录
    Answer findAnswerByQIdAndUId(Long qid,Long uId);
}
