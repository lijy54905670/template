package com.xinyuan.ms.web.controller;

import com.xinyuan.ms.common.util.ResultUtil;
import com.xinyuan.ms.common.web.Message;
import com.xinyuan.ms.entity.Answer;
import com.xinyuan.ms.service.AnswerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    AnswerService answerService;

    /**
     * 保存答题记录
     * @param answers
     * @return
     */
    @ApiOperation(value = "保存", notes = "保存") //给这个方法添加说明
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ResponseEntity<Message> save(@RequestBody List<Answer> answers){

        answerService.saveAnswer(answers);
        return ResponseEntity.ok(ResultUtil.success());
    }
}
