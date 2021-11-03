package com.chris.bullseye.system.dto.response

import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime

/**
 * @author Chris
 * @date 2020 12 08 13:46
 */
data class MenuFunctionResponse(
    @ApiModelProperty(name = "id")
    var id: String? = null,

    @ApiModelProperty(name = "菜单id")
    var menuId: String? = null,

    @ApiModelProperty(name = "编码")
    var code: String? = null,

    @ApiModelProperty(name = "名称")
    var name: String? = null,

    @ApiModelProperty(name = "路径")
    var path: String? = null,

    @ApiModelProperty(name = "状态")
    var status: String? = null,

    @ApiModelProperty(name = "创建人ID")
    var creatorId: String? = null,

    @ApiModelProperty(name = "创建人")
    var creatorName: String? = null,

    @ApiModelProperty(name = "创建日期")
    var createTime: LocalDateTime? = null,
)
