package com.xinyuan.ms.service;

import com.xinyuan.ms.entity.QuestionType;
import com.xinyuan.ms.mapper.QuestionTypeDao;
import org.springframework.stereotype.Service;

@Service
public class QuestionTypeService extends BaseService<QuestionTypeDao, QuestionType,Long> {

    /**
     * 查询题目题型
     */
    public String findTypeById(Long id){
        QuestionType questionType = get(id);
        return questionType.getName();
    }

}
