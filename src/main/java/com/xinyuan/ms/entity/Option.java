package com.xinyuan.ms.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "tb_option")
public class Option {

    @Id
    @ApiModelProperty(value = "主键", name = "id", example = "0")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ApiModelProperty(value = "题目id", name = "qId", example = "qId")
    @Column(name = "q_id")
    private Long qId;

    @ApiModelProperty(value = "选项内容", name = "content", example = "content")
    @Column(name = "content")
    private String content;

    @ApiModelProperty(value = "是否为正确选项 1表示是，0表示不是", name = "rightAnswer", example = "1")
    @Column(name = "right_answer")
    private Integer rightAnswer;

    @ApiModelProperty(value = "删除标志 0表示未删 1表示删除", name = "deleted", example = "0")
    @Column(name = "deleted")
    private Integer deleted;

}
