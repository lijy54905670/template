package com.xinyuan.ms.web.request;

import com.xinyuan.ms.entity.TOption;
import com.xinyuan.ms.entity.Topic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class SaveTopicOptions {

    @ApiModelProperty(value = "题目", name = "topic")
    private Topic topic;

    @ApiModelProperty(value = "题目所属课程id", name = "courseIds")
    private List<Long> courseIds;

    @ApiModelProperty(value = "选项", name = "list")
    private List<TOption> tOptionList;

}
