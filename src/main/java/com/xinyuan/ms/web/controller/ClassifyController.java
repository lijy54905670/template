package com.xinyuan.ms.web.controller;

import com.xinyuan.ms.common.web.Conditions;
import com.xinyuan.ms.entity.Classify;
import com.xinyuan.ms.service.ClassifyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(description = "ClassifyController")
@RestController
@RequestMapping("/classify")
public class ClassifyController {

    @Autowired
    ClassifyService classifyService;

    /**
     * 显示左侧分类树
     * @return
     */
    @ApiOperation(value = "查询分类树",notes = "查询分类树")
    @RequestMapping(value = "query",method = RequestMethod.POST)
    public ResponseEntity query(@RequestBody List<Conditions> conditions){
        List<Classify> classify = classifyService.query(conditions);
        return ResponseEntity.ok(classify);
    }

    /**
     * 添加分类
     * @param classify 新添加的分类
     */
    @ApiOperation(value = "添加分类树",notes = "添加分类树")
    @RequestMapping(value = "saveClassify",method = RequestMethod.POST)
    public void saveClassify(@RequestBody Classify classify){

        classifyService.addClassify(classify);
    }
}
