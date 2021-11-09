package com.chris.bullseye.system.pojo

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime
import java.util.*

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-25 16:22
 * 账号角色
 */
@TableName(value = "b_account_role")
open class AccountRole(
        
        @TableId(type = IdType.ASSIGN_UUID)
        @ApiModelProperty(name = "唯一标识")
        var id: String? = null,

        @ApiModelProperty(name = "角色外键")
        @TableField(value = "role_id")
        var roleId: String? = null,

        @ApiModelProperty(name = "用户外键")
        @TableField(value = "account_id")
        var accountId: String? = null,

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
        var createTime: LocalDateTime? = null


)