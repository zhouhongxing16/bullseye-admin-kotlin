package com.chris.bullseye.system.pojo

import java.util.Date
import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.io.Serializable
import com.fasterxml.jackson.annotation.JsonFormat
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

        @ApiModelProperty(name = "代码")
        @TableField(value = "code")
        var code: String? = null,

        @ApiModelProperty(name = "名称")
        @TableField(value = "title")
        var title: String? = null,

        @ApiModelProperty(name = "图标")
        @TableField(value = "icon")
        var icon: String? = null,

        @ApiModelProperty(name = "路径")
        @TableField(value = "path")
        var path: String? = null,

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