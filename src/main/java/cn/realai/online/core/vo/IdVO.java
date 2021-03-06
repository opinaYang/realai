package cn.realai.online.core.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

@ApiModel
public class IdVO {

    @ApiModelProperty(value = "id")
    @NotBlank(message = "id不能为空")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
