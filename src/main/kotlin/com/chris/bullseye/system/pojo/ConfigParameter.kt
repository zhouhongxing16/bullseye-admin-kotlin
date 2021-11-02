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
 * @Date:  2020-12-25 19:25
 * 系统设置
 */
@TableName(value = "b_config_parameter")
open class ConfigParameter(

        @TableId(type = IdType.ASSIGN_ID)
        @ApiModelProperty(name = "唯一标识")
        var id: String? = null,

        @ApiModelProperty(name = "播放设置、富文本设置、课程设置、安全设置")
        @TableField(value = "data_id")
        var dataId: String? = null,

        @ApiModelProperty(name = "编码")
        @TableField(value = "code")
        var code: String? = null,

        @ApiModelProperty(name = "名称")
        @TableField(value = "name")
        var name: String? = null,

        @ApiModelProperty(name = "单选、多选、文本输入")
        @TableField(value = "display_type")
        var displayType: Int? = null,

        @ApiModelProperty(name = "默认值")
        @TableField(value = "default_value")
        var defaultValue: String? = null,

        @ApiModelProperty(name = "设置值")
        @TableField(value = "config_value")
        var configValue: String? = null,

        @ApiModelProperty(name = "创建人ID")
        @TableField(value = "creator_id")
        var creatorId: String? = null,

        @ApiModelProperty(name = "创建人")
        @TableField(value = "creator_name")
        var creatorName: String? = null,

        @ApiModelProperty(name = "状态")
        @TableField(value = "status")
        var status: Int? = null,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        @ApiModelProperty(name = "创建时间")
        @TableField(value = "create_time")
        var createTime: LocalDateTime? = null,


        )