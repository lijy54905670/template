package com.xinyuan.ms.web.request;

import com.xinyuan.ms.entity.Option;
import com.xinyuan.ms.entity.Question;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class SaveIdsState {

    @ApiModelProperty(value = "勾选的id", name = "ids")
    private List<Long> ids;

    @ApiModelProperty(value = "执行操作", name = "state")
    private Integer state;

}
