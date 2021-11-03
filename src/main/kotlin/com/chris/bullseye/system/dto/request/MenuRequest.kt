package com.chris.bullseye.system.dto.request

import io.swagger.annotations.ApiModelProperty

/**
 * @author Chris
 * @date 2021-10-21 15:32
 */
data class MenuRequest(
        @ApiModelProperty(name = "用户名")
        var username: String? = null,

        @ApiModelProperty(name = "密码")
        var password: String? = null,

        @ApiModelProperty(name = "验证码")
        var captcha : String? = null,
)
