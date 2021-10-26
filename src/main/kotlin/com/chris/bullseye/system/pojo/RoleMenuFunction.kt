package com.chris.bullseye.system.pojo

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime
import java.util.*
import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName

/**
 * @author Chris
 * @date 2020 12 08 13:41
 */
@TableName(value = "b_role_menu_function")
open class RoleMenuFunction(
        
        @TableId(type = IdType.ASSIGN_ID)
        @ApiModelProperty(name = "唯一标识")
        var id: String? = null,

        @ApiModelProperty(name = "角色ID")
        @TableField(value = "role_id")
        var roleId: String? = null,

        @ApiModelProperty(name = "授权功能ID")
        @TableField(value = "menu_function_id")
        var menuFunctionId: String? = null,

        @ApiModelProperty(name = "状态")
        @TableField(value = "status")
        var status: String? = null,


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
