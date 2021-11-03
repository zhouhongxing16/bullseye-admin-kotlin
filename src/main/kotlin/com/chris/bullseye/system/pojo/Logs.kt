package com.chris.bullseye.system.pojo

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-25 19:13
 * 日志
 */
@TableName(value = "b_logs")
open class Logs(

        @TableId(type = IdType.ASSIGN_ID)
        @ApiModelProperty(name = "ID")
        var id: String? = null,

        @ApiModelProperty(name = "所属组织")
        @TableField(value = "organization_id")
        var organizationId: String? = null,

        @ApiModelProperty(name = "操作类")
        @TableField(value = "option_name")
        var optionName: String? = null,

        @ApiModelProperty(name = "操作方法名")
        @TableField(value = "option_type")
        var optionType: String? = null,

        @ApiModelProperty(name = "操作方法")
        @TableField(value = "method")
        var method: String? = null,

        @ApiModelProperty(name = "参数")
        @TableField(value = "params")
        var params: String? = null,

        @ApiModelProperty(name = "创建人ID")
        @TableField(value = "creator_id")
        var creatorId: String? = null,

        @ApiModelProperty(name = "创建人")
        @TableField(value = "creator_name")
        var creatorName: String? = null,

        @ApiModelProperty(name = "IP")
        @TableField(value = "ip")
        var ip: String? = null,

        @ApiModelProperty(name = "执行时长")
        @TableField(value = "execution_time")
        var executionTime: Int? = null,

        @ApiModelProperty(name = "状态")
        @TableField(value = "status")
        var status: Int? = null,

        @ApiModelProperty(name = "备注")
        @TableField(value = "remark")
        var remark: String? = null,

        @ApiModelProperty(name = "创建日期")
        @TableField(value = "create_time")
        var createTime: LocalDateTime? = null


        )