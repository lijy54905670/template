package com.xinyuan.ms.web.controller;

import com.xinyuan.ms.common.util.ResultUtil;
import com.xinyuan.ms.common.web.Message;
import com.xinyuan.ms.service.OptionService;
import com.xinyuan.ms.service.QuestionService;
import com.xinyuan.ms.web.request.SaveConditionsAndUId;
import com.xinyuan.ms.web.request.SaveQuestionOption;
import com.xinyuan.ms.web.vo.NextQuestion;
import com.xinyuan.ms.web.vo.QuestionOptionVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Message> save(@RequestBody List<SaveQuestionOption> saveQuestionOptionList){
        questionService.saveQuestionOption(saveQuestionOptionList);
        return ResponseEntity.ok(ResultUtil.success());
    }


    /**
     *     下一题
     * @param saveConditionsAndUId
     * @return
     */
    @ApiOperation(value = "下一题", notes = "下一题")  //给方法添加说明
    @RequestMapping(value = "next", method = RequestMethod.POST)
    public ResponseEntity<NextQuestion> next(@RequestBody SaveConditionsAndUId saveConditionsAndUId) {
        NextQuestion next = questionService.next(saveConditionsAndUId); //返回一个封装了问题，选项，答题记录的对象
        return ResponseEntity.ok(next);
    }

    /**
     *     查询全部题目选项
     * @return
     */
    @ApiOperation(value = "查询题目选项", notes = "查询题目选项")  //给方法添加说明
    @RequestMapping(value = "query", method = RequestMethod.POST)
    public ResponseEntity<List<QuestionOptionVo>> query() {
        List<QuestionOptionVo> questionOptionVos = questionService.queryAll();
        return ResponseEntity.ok(questionOptionVos);
    }
}
