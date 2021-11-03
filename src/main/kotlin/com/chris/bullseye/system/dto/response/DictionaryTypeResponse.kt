package com.chris.bullseye.system.dto.response

import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime

/**
 * @author Chris
 * @date 2020 12 08 12:00
 */
data class DictionaryTypeResponse(
        @ApiModelProperty(name = "唯一标识")
        var id: String? = null,

        @ApiModelProperty(name = "类型代码")
        var code: String? = null,

        @ApiModelProperty(name = "类型名称")
        var name: String? = null,

        @ApiModelProperty(name = "备注")
        var remark: String? = null,

        @ApiModelProperty(name = "状态")
        var status: Int? = null,

        @ApiModelProperty(name = "创建人ID")
        var creatorId: String? = null,

        @ApiModelProperty(name = "创建人")
        var creatorName: String? = null,

        @ApiModelProperty(name = "创建日期")
        var createTime: LocalDateTime? = null,
)
