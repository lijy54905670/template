package com.xinyuan.ms.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "tb_topic_course")
public class TopicCourse implements Serializable {

    @Id
    @ApiModelProperty(value = "主键", name = "id", example = "0")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ApiModelProperty(value = "题目id", name = "tId", example = "1")
    @Column(name = "tId")
    private Long tId;

    @ApiModelProperty(value = "课程id", name = "cId", example = "1")
    @Column(name = "cId")
    private Long cId;

    @ApiModelProperty(value = "删除标志", name = "deleted", example = "0")
    @Column(name = "deleted")
    private Integer deleted = 0;
}
