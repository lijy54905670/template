package com.xinyuan.ms.web.controller;

import com.xinyuan.ms.entity.Option;
import com.xinyuan.ms.entity.Question;
import com.xinyuan.ms.mapper.OptionDao;
import com.xinyuan.ms.service.OptionService;
import com.xinyuan.ms.service.QuestionService;
import com.xinyuan.ms.web.request.SaveQuestionOption;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    
    @Autowired
    QuestionService questionService;

    @Autowired
    OptionService optionService;

    /**
     * 问题选项保存
     * @param saveQuestionOptionList  问题选项列表
     * @return
     */
    @ApiOperation(value = "保存", notes = "保存") //给这个方法添加说明
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@RequestBody List<SaveQuestionOption> saveQuestionOptionList){

        if (!saveQuestionOptionList.isEmpty()) {
            for (SaveQuestionOption saveQuestionAnswer:saveQuestionOptionList) {
                Question question = saveQuestionAnswer.getQuestion();       //获取Question对象
                List<Option> list = saveQuestionAnswer.getOptionList();     //获取Option对象列表

                Long qId = questionService.save(question);                   //如果是新增问题则返回问题id
                optionService.save(question,list,qId);
            }

        }
        return "ok";
    }
}
