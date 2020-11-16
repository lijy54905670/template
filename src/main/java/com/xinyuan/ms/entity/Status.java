package com.xinyuan.ms.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "tb_status")
public class Status implements Serializable {

    @Id
    @ApiModelProperty(value = "主键", name = "id", example = "0")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ApiModelProperty(value = "状态", name = "status", example = "启用")
    @Column(name = "status")
    private String status;

    @ApiModelProperty(value = "删除,0表示未删除，1表示删除", name = "deleted", example = "0")
    @Column(name = "deleted")
    private Integer deleted = 0;
}
