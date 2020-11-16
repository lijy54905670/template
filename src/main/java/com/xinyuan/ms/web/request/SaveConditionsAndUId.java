package com.xinyuan.ms.web.request;

import com.xinyuan.ms.common.service.Order;
import com.xinyuan.ms.common.service.PageBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SaveConditionsAndUId {

    @ApiModelProperty(value = "用户id",name = "uId")
    private Long uId;

    @ApiModelProperty(value = "排序属性",name = "order")
    private Order order;

    @ApiModelProperty(value = "分页属性", name = "pageBean")
    private PageBean pageBean;
}
