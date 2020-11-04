package com.xinyuan.ms.web.request;

import com.xinyuan.ms.entity.Option;
import com.xinyuan.ms.entity.Question;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class SaveQuestionOption {

    @ApiModelProperty(value = "问题对象", name = "question")
    private Question question;

    @ApiModelProperty(value = "选项对象数组", name = "list")
    private List<Option> optionList;
}
