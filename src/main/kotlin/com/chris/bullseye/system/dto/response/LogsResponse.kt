package com.chris.bullseye.system.dto.response

import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-25 19:09
 */
data class LogsResponse(
    @ApiModelProperty(name = "ID")
    var id: String? = null,

    @ApiModelProperty(name = "所属组织")
    var organizationId: String? = null,

    @ApiModelProperty(name = "操作类")
    var optionName: String? = null,

    @ApiModelProperty(name = "操作方法名")
    var optionType: String? = null,

    @ApiModelProperty(name = "操作方法")
    var method: String? = null,

    @ApiModelProperty(name = "参数")
    var params: String? = null,

    @ApiModelProperty(name = "创建人ID")
    var creatorId: String? = null,

    @ApiModelProperty(name = "创建人")
    var creatorName: String? = null,

    @ApiModelProperty(name = "IP")
    var ip: String? = null,

    @ApiModelProperty(name = "执行时长")
    var executionTime: Int? = null,

    @ApiModelProperty(name = "状态")
    var status: Int? = null,

    @ApiModelProperty(name = "备注")
    var remark: String? = null,

    @ApiModelProperty(name = "创建日期")
    var createTime: LocalDateTime? = null
)