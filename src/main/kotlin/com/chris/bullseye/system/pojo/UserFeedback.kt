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
 * @Date:  2020-12-25 19:54
 * 用户反馈
 */
@TableName(value = "b_user_feedback")
open class UserFeedback(
        
        @TableId(type = IdType.ASSIGN_UUID)
        @ApiModelProperty(name = "唯一标识")
        var id: String? = null,

        @ApiModelProperty(name = "用户标识")
        @TableField(value = "creator_id")
        var creatorId: String? = null,

        @ApiModelProperty(name = "联系人")
        @TableField(value = "contact_name")
        var contactName: String? = null,

        @ApiModelProperty(name = "联系电话")
        @TableField(value = "contact_phone")
        var contactPhone: String? = null,

        @ApiModelProperty(name = "反馈内容")
        @TableField(value = "content")
        var content: String? = null,

        @ApiModelProperty(name = "附件")
        @TableField(value = "attach")
        var attach: String? = null,

        @ApiModelProperty(name = "状态")
        @TableField(value = "status")
        var status: Int? = null,

        @ApiModelProperty(name = "创建日期")
        @TableField(value = "create_time")
        var createTime: LocalDateTime? = null,

        )