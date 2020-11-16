package com.xinyuan.ms.web.controller;

import com.xinyuan.ExcelTest.Student;
import com.xinyuan.ms.common.util.ExportExcelUtil;
import com.xinyuan.ms.common.util.ExportExcelWrapper;
import com.xinyuan.ms.common.web.PageBody;
import com.xinyuan.ms.entity.Topic;
import com.xinyuan.ms.entity.User;
import com.xinyuan.ms.service.TopicService;
import com.xinyuan.ms.web.request.SaveIdsState;
import com.xinyuan.ms.web.request.SaveSort;
import com.xinyuan.ms.web.request.SaveTopicOptions;
import com.xinyuan.ms.web.vo.TopicVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Api(description = "TopicController")
@RestController
public class TopicController {

    @Autowired
    TopicService topicService;

    /**
     * 保存题目选项
     * @param saveTopicOptions
     */
    @ApiOperation(value = "保存题目选项", notes = "保存题目选项")
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public void save(@RequestBody SaveTopicOptions saveTopicOptions){
        topicService.saveTopicOptions(saveTopicOptions);
    }

    /**
     * 停用、启用、删除题目
     */
    @ApiOperation(value = "修改题目状态", notes = "修改题目状态")
    @RequestMapping(value = "modify", method = RequestMethod.POST)
    public void modify(@RequestBody SaveIdsState saveIdsState){
        topicService.modify(saveIdsState);
    }


    /**
     * 查询题目
     */
    @ApiModelProperty(value = "查询题目",notes = "查询题目")
    @RequestMapping(value = "query", method = RequestMethod.POST)
    public ResponseEntity query(@RequestBody PageBody pageBody){
        Page page = topicService.queryTopic(pageBody);
        return ResponseEntity.ok(page);
    }


    /**导出
     *
     * @param pageBody
     * @throws FileNotFoundException
     */
    @ApiModelProperty(value = "导出",notes = "导出")
    @RequestMapping(value = "export", method = RequestMethod.POST)
    public void export(@RequestBody PageBody pageBody)  {
        try {
            topicService.export(pageBody);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 分类排序
     * @param saveSorts
     */
    @ApiModelProperty(value = "排序",notes = "排序")
    @RequestMapping(value = "sort",method = RequestMethod.POST)
    public void sort(@RequestBody List<SaveSort> saveSorts){
        topicService.topicSort(saveSorts);
    }








    /**导出到浏览器
     *
     * @throws FileNotFoundException
     */
    @ApiModelProperty(value = "导出到浏览器",notes = "导出到浏览器")
    @RequestMapping(value = "export2", method = RequestMethod.GET)
    public void export2(HttpServletResponse response){
        try {
            topicService.export2(response);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
