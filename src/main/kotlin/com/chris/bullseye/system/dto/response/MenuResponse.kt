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

    @ApiModelProperty(name = "路径")
    var path: String? = null,

    @ApiModelProperty(name = "路由的名字")
    var name: String? = null,

    @ApiModelProperty(name = "文件路径")
    var component: String? = null,

    @ApiModelProperty(name = "面包屑导航，不需要则noRedirect")
    var redirect: String? = null,

    @ApiModelProperty(name = "名称")
    var title: String? = null,

    @ApiModelProperty(name = "图标")
    var icon: String? = null,

    @ApiModelProperty(name = "当设置 true 的时候该路由不会在侧边栏出现")
    var hidden: Boolean? = null,

    @ApiModelProperty(name = "一直显示该路由")
    var alwaysShow: Boolean? = null,

    @ApiModelProperty(name = "固定在tags")
    var affix: Boolean? = null,

    @ApiModelProperty(name = "如果设置为true，则不会被 <keep-alive> 缓存(默认 false)")
    var noCache: Boolean? = null,

    @ApiModelProperty(name = "如果设置为false，则不会在breadcrumb面包屑中显示(默认 true)")
    var breadcrumb: Boolean? = null,

    @ApiModelProperty(name = "高亮相对应地址的侧边栏，如/article/list")
    var activeMenu: Boolean? = null,

    @ApiModelProperty(name = "代码")
    var code: String? = null,

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
