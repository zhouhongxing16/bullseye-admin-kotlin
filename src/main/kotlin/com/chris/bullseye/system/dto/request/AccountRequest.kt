package com.chris.bullseye.system.dto.request

import io.swagger.annotations.ApiModelProperty

/**
 * @author Chris
 * @date 2021-10-26 10:48
 */
data class AccountRequest(

        @ApiModelProperty(name = "用户名")
        var username: String? = null,

        @ApiModelProperty(name = "别名")
        var nickName: String? = null,

        @ApiModelProperty(name = "密码")
        var password: String? = null,

        @ApiModelProperty(name = "分页页码")
        var pageNum: Long? = null,

        @ApiModelProperty(name = "分页大小")
        var pageSize: Long? = null,

)
