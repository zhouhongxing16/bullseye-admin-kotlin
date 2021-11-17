package com.chris.bullseye.system.dto.response

import io.swagger.annotations.ApiModelProperty

data class MenuMataResponse(
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
)
