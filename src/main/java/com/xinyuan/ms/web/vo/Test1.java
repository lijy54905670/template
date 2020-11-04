package com.xinyuan.ms.web.vo;

import com.xinyuan.ms.entity.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class Test1 {
    @ApiModelProperty(value = "主键", name = "id", example = "0")
    private Page<User> page;

    @ApiModelProperty(value = "主键", name = "id", example = "0")
    private Survey survey;
}
