package com.xinyuan.ms.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "tb_t_option")
public class TOption implements Serializable {

    @Id
    @ApiModelProperty(value = "主键", name = "id", example = "0")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ApiModelProperty(value = "题目id", name = "tId", example = "tId")
    @Column(name = "t_id")
    private Long tId;

    @ApiModelProperty(value = "选项内容", name = "content", example = "content")
    @Column(name = "content")
    private String content;

    @ApiModelProperty(value = "是否为正确选项 1表示是，0表示不是", name = "rightAnswer", example = "1")
    @Column(name = "right_answer")
    private Integer rightAnswer;

    @ApiModelProperty(value = "删除标志", name = "deleted", example = "0")
    @Column(name = "deleted")
    private Integer deleted = 0;
}
