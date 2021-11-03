package com.chris.bullseye.system.dto.response

import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-25 19:43
 */
data class OrganizationResponse(
    @ApiModelProperty(name = "唯一标识")
    var id: String? = null,

    @ApiModelProperty(name = "LOGO")
    var logo: String? = null,

    @ApiModelProperty(name = "联系人")
    var contactName: String? = null,

    @ApiModelProperty(name = "联系电话")
    var contactPhone: String? = null,

    @ApiModelProperty(name = "代码")
    var code: String? = null,

    @ApiModelProperty(name = "名称")
    var name: String? = null,

    @ApiModelProperty(name = "类型")
    var typeId: String? = null,

    @ApiModelProperty(name = "描述")
    var brief: String? = null,

    @ApiModelProperty(name = "状态")
    var status: Int? = null,

    @ApiModelProperty(name = "创建人ID")
    var creatorId: String? = null,

    @ApiModelProperty(name = "创建人")
    var creatorName: String? = null,

    @ApiModelProperty(name = "创建日期")
    var createTime: LocalDateTime? = null,
)