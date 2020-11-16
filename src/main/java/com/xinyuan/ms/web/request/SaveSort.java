package com.xinyuan.ms.web.request;

import com.xinyuan.ms.entity.Topic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SaveSort {

    @ApiModelProperty(value = "分类id", name = "id")
    private Long id;

    @ApiModelProperty(value = "排序", name = "sort")
    private Integer sort;
}
