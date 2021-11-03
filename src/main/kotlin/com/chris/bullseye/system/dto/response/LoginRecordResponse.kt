package com.chris.bullseye.system.dto.response

import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-25 19:58
 */
data class LoginRecordResponse(
    @ApiModelProperty(name = "访问ID")
    var id: String? = null,

    @ApiModelProperty(name = "登录账号")
    var username: String? = null,

    @ApiModelProperty(name = "登录IP地址")
    var ip: String? = null,

    @ApiModelProperty(name = "登录地点")
    var loginLocation: String? = null,

    @ApiModelProperty(name = "浏览器类型")
    var browser: String? = null,

    @ApiModelProperty(name = "操作系统")
    var os: String? = null,

    @ApiModelProperty(name = "登录状态（1成功 0失败）")
    var status: Int? = null,

    @ApiModelProperty(name = "提示消息")
    var message: String? = null,

    @ApiModelProperty(name = "创建日期")
    var createTime: LocalDateTime? = null,
)