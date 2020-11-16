package com.xinyuan.ms.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "tb_answer")
public class Answer {

    @Id
    @ApiModelProperty(value = "主键", name = "id", example = "0")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ApiModelProperty(value = "用户id", name = "uId", example = "0")
    @Column(name = "u_id")
    private Long uId;

    @ApiModelProperty(value = "问卷id", name = "qId", example = "0")
    @Column(name = "q_id")
    private Long qId;

    @ApiModelProperty(value = "选择id", name = "oId", example = "0")
    @Column(name = "o_id")
    private Long oId;

    @ApiModelProperty(value = "删除标志 0表示未删 1表示删除", name = "deleted", example = "0")
    @Column(name = "deleted")
    private Integer deleted;
}
