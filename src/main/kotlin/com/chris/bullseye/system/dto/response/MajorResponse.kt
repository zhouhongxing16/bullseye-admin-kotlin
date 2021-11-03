package com.chris.bullseye.system.dto.response

import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-25 19:36
 */
data class MajorResponse(
    @ApiModelProperty(name = "唯一标识")
    var id: String? = null,

    @ApiModelProperty(name = "代码")
    var code: String? = null,

    @ApiModelProperty(name = "名称")
    var name: String? = null,

    @ApiModelProperty(name = "专业类型")
    var typeId: String? = null,

    @ApiModelProperty(name = "状态")
    var status: Int? = null,

    @ApiModelProperty(name = "创建人ID")
    var creatorId: String? = null,

    @ApiModelProperty(name = "创建人")
    var creatorName: String? = null,

    @ApiModelProperty(name = "创建日期")
    var createTime: LocalDateTime? = null,

    @ApiModelProperty(name = "备注")
    var remark: String? = null,
)