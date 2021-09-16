package com.chris.bullseye.system.entity.request

import io.swagger.annotations.ApiModelProperty

/**
 * @author Chris
 * @date 2021-01-08 16:18
 */
data class LoginRequest(

        @ApiModelProperty(name = "用户名")
        var username: String? = null,

        @ApiModelProperty(name = "密码")
        var password: String? = null,

        @ApiModelProperty(name = "验证码")
        var validateCode : String? = null,

        @ApiModelProperty(name = "手机号")
        var mobile : String? = null,

        @ApiModelProperty(name = "客户端类型")
        var type: Int? = 0,

        @ApiModelProperty(name = "组织标识")
        var organizationId: String? = null,

        @ApiModelProperty(name = "旧密码")
        var oldPassword: String? = null,

        @ApiModelProperty(name = "新密码")
        var newPassword: String? = null,
)