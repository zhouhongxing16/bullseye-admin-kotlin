package com.chris.bullseye.system.dto.response

import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime

/**
 * @author Chris
 * @date 2020 12 08 13:43
 */
data class RoleMenuResponse(
    var menuName: String? = null,

    val menuIcon: String? = null,

    val menuPath: String? = null,

    val menuSort: String? = null,

    @ApiModelProperty(name = "唯一标识")
    var id: String? = null,

    @ApiModelProperty(name = "角色外键")
    var roleId: String? = null,

    @ApiModelProperty(name = "菜单外键")
    var menuId: String? = null,

    @ApiModelProperty(name = "状态")
    var status: Int? = null,

    @ApiModelProperty(name = "创建人ID")
    var creatorId: String? = null,

    @ApiModelProperty(name = "创建人")
    var creatorName: String? = null,

    @ApiModelProperty(name = "创建日期")
    var createTime: LocalDateTime? = null,
)
