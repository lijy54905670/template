package com.xinyuan.ms.web.vo;

import com.xinyuan.ms.entity.Question;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.domain.Page;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
public class TopicVo {

    @ApiModelProperty(value = "主键", name = "id", example = "0")
    private Long id;

    @ApiModelProperty(value = "题干", name = "questionStem", example = "三氯化铁鉴别反应是根据什么基团")
    private String questionStem;

    @ApiModelProperty(value = "所属分类", name = "classifyName", example = "试卷1")
    private String classifyName;

    @ApiModelProperty(value = "所属课程", name = "courseName", example = "语文、数学")
    private List<String> courseName;

    @ApiModelProperty(value = "题型", name = "questionTypeName", example = "选择题")
    private String questionTypeName;

    @ApiModelProperty(value = "难易程度", name = "complexity", example = "0")
    private String complexity;

    @ApiModelProperty(value = "状态", name = "status", example = "停用")
    private String status;

}
