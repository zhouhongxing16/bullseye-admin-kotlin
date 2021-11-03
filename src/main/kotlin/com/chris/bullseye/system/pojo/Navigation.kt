package com.chris.bullseye.system.pojo

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-28 11:25
 * 导航栏
 */
@TableName(value = "b_navigation")
open class Navigation(

        @TableId(type = IdType.ASSIGN_ID)
        @ApiModelProperty(name = "唯一标识")
        var id: String? = null,

        @ApiModelProperty(name = "名称")
        @TableField(value = "name")
        var name: String? = null,

        @ApiModelProperty(name = "级别")
        @TableField(value = "level")
        var level: Int? = null,

        @ApiModelProperty(name = "父标识")
        @TableField(value = "parent_id")
        var parentId: String? = null,

        @ApiModelProperty(name = "显示顺序")
        @TableField(value = "sort")
        var sort: Int? = null,

        @ApiModelProperty(name = "访问路径")
        @TableField(value = "path")
        var path: String? = null,

        @ApiModelProperty(name = "状态")
        @TableField(value = "status")
        var status: Int? = null,

        @ApiModelProperty(name = "创建人ID")
        @TableField(value = "creator_id")
        var creatorId: String? = null,

        @ApiModelProperty(name = "创建人")
        @TableField(value = "creator_name")
        var creatorName: String? = null,

        @ApiModelProperty(name = "创建日期")
        @TableField(value = "create_time")
        var createTime: LocalDateTime? = null,


        )