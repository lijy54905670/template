package com.xinyuan.ms.web.vo;

import com.xinyuan.ms.entity.Classify;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class Menu {

    @ApiModelProperty(value = "分类", name = "classify", example = "classify")
    private Classify classify;

    @ApiModelProperty(value = "子节点", name = "children", example = "children")
    private List<Menu> children;
}
