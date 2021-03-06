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
 * @Date:  2020-12-25 19:45
 * 角色
 */
@TableName(value = "b_role")
open class Role (
        
        @TableId(type = IdType.ASSIGN_UUID)
        @ApiModelProperty(name = "唯一标识")
        var id: String? = null,

        @ApiModelProperty(name = "角色代码")
        @TableField(value = "code")
        var code: String? = null,

        @ApiModelProperty(name = "角色名称")
        @TableField(value = "name")
        var name: String? = null,

        @ApiModelProperty(name = "个人（pesonal），部门（department）,组织（organization）,系统（system）")
        @TableField(value = "data_auth_flag")
        var dataAuthFlag: String? = null,

        @ApiModelProperty(name = "角色描述")
        @TableField(value = "remark")
        var remark: String? = null,

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