package com.xinyuan.ms.entity;

import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "tb_topic")
public class Topic implements Serializable {

    @Id
    @ApiModelProperty(value = "主键", name = "id", example = "0")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ApiModelProperty(value = "题干", name = "questionStem", example = "三氯化铁鉴别反应是根据什么基团")
    @Column(name = "question_stem")
    private String questionStem;

    @ApiModelProperty(value = "所属分类", name = "classifyId", example = "0")
    @Column(name = "classify_id")
    private Long classifyId;


    @ApiModelProperty(value = "题型", name = "questionTypeId", example = "1")
    @Column(name = "question_type_id")
    private Long questionTypeId;

    @ApiModelProperty(value = "难易程度", name = "complexity", example = "简单")
    @Column(name = "complexity")
    private String complexity;

    @ApiModelProperty(value = "状态", name = "states", example = "0")
    @Column(name = "status")
    private Integer status = 1;

    @ApiModelProperty(value = "答案", name = "answer", example = "0")
    @Column(name = "answer")
    private String answer;

    @ApiModelProperty(value = "解析", name = "analysis", example = "0")
    @Column(name = "analysis")
    private String analysis;

    @ApiModelProperty(value = "删除标志", name = "deleted", example = "0")
    @Column(name = "deleted")
    private Integer deleted = 0;



}

