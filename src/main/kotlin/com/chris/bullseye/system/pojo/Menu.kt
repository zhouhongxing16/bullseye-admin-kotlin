package com.chris.bullseye.system.pojo

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-25 19:38
 * 菜单
 */
@TableName(value = "b_menu")
open class Menu(

        @TableId(type = IdType.ASSIGN_UUID)
        @ApiModelProperty(name = "唯一标识")
        var id: String? = null,

        @ApiModelProperty(name = "父菜单id")
        @TableField(value = "parent_id")
        var parentId: String? = null,

        @ApiModelProperty(name = "路径")
        @TableField(value = "path")
        var path: String? = null,

        @ApiModelProperty(name = "路由的名字")
        @TableField(value = "name")
        var name: String? = null,

        @ApiModelProperty(name = "文件路径")
        @TableField(value = "component")
        var component: String? = null,

        @ApiModelProperty(name = "面包屑导航")
        @TableField(value = "redirect")
        var redirect: String? = null,

        @ApiModelProperty(name = "标题")
        @TableField(value = "title")
        var title: String? = null,

        @ApiModelProperty(name = "图标")
        @TableField(value = "icon")
        var icon: String? = null,

        @ApiModelProperty(name = "侧边栏隐藏")
        @TableField(value = "hidden")
        var hidden: Boolean? = null,

        @ApiModelProperty(name = "一直显示该路由")
        @TableField(value = "always_show")
        var alwaysShow: Boolean? = null,

        @ApiModelProperty(name = "固定在tags")
        @TableField(value = "affix")
        var affix: Boolean? = null,

        @ApiModelProperty(name = "页面缓存")
        @TableField(value = "no_cache")
        var noCache: Boolean? = null,

        @ApiModelProperty(name = "面包屑中显示")
        @TableField(value = "breadcrumb")
        var breadcrumb: Boolean? = null,

        @ApiModelProperty(name = "高亮相对应地址")
        @TableField(value = "active_menu")
        var activeMenu: Boolean? = null,

        @ApiModelProperty(name = "代码")
        @TableField(value = "code")
        var code: String? = null,

        @ApiModelProperty(name = "创建人ID")
        @TableField(value = "creator_id")
        var creatorId: String? = null,

        @ApiModelProperty(name = "创建人")
        @TableField(value = "creator_name")
        var creatorName: String? = null,

        @ApiModelProperty(name = "创建日期")
        @TableField(value = "create_time")
        var createTime: LocalDateTime? = null,

        @ApiModelProperty(name = "状态")
        @TableField(value = "status")
        var status: Int? = null,

        @ApiModelProperty(name = "显示顺序")
        @TableField(value = "sort")
        var sort: Int? = null,


        )
