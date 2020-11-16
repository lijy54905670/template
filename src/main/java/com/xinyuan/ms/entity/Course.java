package com.xinyuan.ms.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "tb_course")
public class Course implements Serializable {

    @Id
    @ApiModelProperty(value = "主键", name = "id", example = "0")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ApiModelProperty(value = "題型", name = "name", example = "语文")
    @Column(name = "name")
    private String name;

    @ApiModelProperty(value = "删除标志 0表示未删 1表示删除", name = "deleted", example = "0")
    @Column(name = "deleted")
    private Integer deleted = 0;
}
