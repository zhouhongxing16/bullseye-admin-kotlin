package com.chris.bullseye.system.dto.response

import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime

/**
 * @author Chris
 * @date 2020 12 08 13:45
 */
data class MenuResponse(
    private var open: Boolean = false,

    val value: String? = null,

    val key: String? = null,

    val type: String? = null,

    val isLeaf: Boolean = false,

    val childNum: Int = 0,

    var userName: String? = null,

    var children: List<MenuResponse>? = null,

    @ApiModelProperty(name = "唯一标识")
    var id: String? = null,

    @ApiModelProperty(name = "父菜单id")
    var parentId: String? = null,

    @ApiModelProperty(name = "代码")
    var code: String? = null,

    @ApiModelProperty(name = "名称")
    var title: String? = null,

    @ApiModelProperty(name = "图标")
    var icon: String? = null,

    @ApiModelProperty(name = "路径")
    var path: String? = null,

    @ApiModelProperty(name = "创建人ID")
    var creatorId: String? = null,

    @ApiModelProperty(name = "创建人")
    var creatorName: String? = null,

    @ApiModelProperty(name = "创建日期")
    var createTime: LocalDateTime? = null,

    @ApiModelProperty(name = "状态")
    var status: Int? = null,

    @ApiModelProperty(name = "显示顺序")
    var sort: Int? = null,
)
