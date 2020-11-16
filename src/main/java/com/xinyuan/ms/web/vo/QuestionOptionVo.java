package com.xinyuan.ms.web.vo;

import com.xinyuan.ms.entity.Option;
import com.xinyuan.ms.entity.Question;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class QuestionOptionVo {

    @ApiModelProperty(value = "问题", name = "question", example = "question")
    private Question question;
    @ApiModelProperty(value = "选项", name = "options", example = "options")
    private List<Option> options;
}
