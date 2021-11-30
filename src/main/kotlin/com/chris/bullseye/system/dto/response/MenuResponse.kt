package com.chris.bullseye.system.dto.response

import com.baomidou.mybatisplus.annotation.TableField
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime

/**
 * @author Chris
 * @date 2020 12 08 13:45
 */
data class MenuResponse(
        var open: Boolean = false,

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

        @ApiModelProperty(name = "meta属性")
        var meta: MenuMataResponse? = null,

        @ApiModelProperty(name = "代码")
        var code: String? = null,

        @ApiModelProperty(name = "标题")
        @TableField(value = "title")
        var title: String? = null,

        @ApiModelProperty(name = "图标")
        var icon: String? = null,

        @ApiModelProperty(name = "侧边栏隐藏")
        var hidden: Boolean? = null,

        @ApiModelProperty(name = "一直显示该路由")
        var alwaysShow: Boolean? = null,

        @ApiModelProperty(name = "固定在tags")
        var affix: Boolean? = null,

        @ApiModelProperty(name = "页面缓存")
        var noCache: Boolean? = null,

        @ApiModelProperty(name = "面包屑中显示")
        var breadcrumb: Boolean? = null,

        @ApiModelProperty(name = "高亮相对应地址")
        var activeMenu: String? = null,

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
