package com.xinyuan.ms.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "tb_classify")
public class Classify implements Serializable {

    @Id
    @ApiModelProperty(value = "主键", name = "id", example = "0")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ApiModelProperty(value = "分类标题", name = "name", example = "试卷1")
    @Column(name = "name")
    private String name;

    @ApiModelProperty(value = "删除标志", name = "deleted", example = "0")
    @Column(name = "deleted")
    private int deleted;

    @ApiModelProperty(value = "父节点id", name = "sId", example = "0")
    @Column(name = "s_id")
    private Long sId;

    @ApiModelProperty(value = "序号", name = "sort", example = "0")
    @Column(name = "sort")
    private int sort;

    @ApiModelProperty(value = "是否是末级节点", name = "last", example = "0")
    @Column(name = "last")
    private int last;

}
