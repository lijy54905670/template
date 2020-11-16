package com.xinyuan.ms.web.vo;

import com.xinyuan.ms.entity.Option;
import com.xinyuan.ms.entity.Question;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
//@Component("nextQuestion")
public class NextQuestion {

    @ApiModelProperty(value = "问题", name = "question", example = "question")
    private Page<Question> question;

    @ApiModelProperty(value = "选项", name = "options", example = "options")
    private List<Option> options;

    @ApiModelProperty(value = "本题已做的值", name = "answer", example = "answer")
    private Long answer;


}
