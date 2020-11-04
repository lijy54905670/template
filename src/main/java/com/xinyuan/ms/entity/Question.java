package com.xinyuan.ms.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "tb_question")
public class Question {

    @Id
    @ApiModelProperty(value = "主键", name = "id", example = "0")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ApiModelProperty(value = "题目标题", name = "title", example = "title")
    @Column(name = "title")
    private String title;

    @ApiModelProperty(value = "删除,0表示未删除，1表示删除", name = "deleted", example = "0")
    @Column(name = "deleted")
    private Integer deleted;

}
