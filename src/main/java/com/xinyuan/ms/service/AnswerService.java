package com.xinyuan.ms.service;

import com.xinyuan.ms.entity.Answer;
import com.xinyuan.ms.entity.Question;
import com.xinyuan.ms.mapper.AnswerDao;
import com.xinyuan.ms.mapper.QuestionDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService extends BaseService<AnswerDao, Answer,Long>{

    /**
     * 保存答题记录
     * @param answers
     */
    public void saveAnswer(List<Answer> answers){
        for (Answer answer : answers){
            save(answer);
        }
    }

    /**
     * 通过用户名和题目名查找答题记录
     * @param qId
     * @param uId
     * @return
     */
    public Answer findAnswerByQIdAndUid(Long qId,Long uId){

        Answer answer = bizRepository.findAnswerByQIdAndUId(qId, uId);

        return answer;
    }
}
