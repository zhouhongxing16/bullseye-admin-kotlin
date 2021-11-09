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
 * @Date:  2020-12-25 19:30
 * 部门/科室
 */
@TableName(value = "b_department")
open class Department(
        
        @TableId(type = IdType.ASSIGN_UUID)
        @ApiModelProperty(name = "唯一标识")
        var id: String? = null,

        @ApiModelProperty(name = "组织标识")
        @TableField(value = "organization_id")
        var organizationId: String? = null,

        @ApiModelProperty(name = "代码")
        @TableField(value = "code")
        var code: String? = null,

        @ApiModelProperty(name = "父级ID")
        @TableField(value = "parent_id")
        var parentId: String? = null,

        @ApiModelProperty(name = "名称")
        @TableField(value = "name")
        var name: String? = null,

        @ApiModelProperty(name = "类型")
        @TableField(value = "type_id")
        var typeId: String? = null,

        @ApiModelProperty(name = "联系人")
        @TableField(value = "contact_name")
        var contactName: String? = null,

        @ApiModelProperty(name = "联系电话")
        @TableField(value = "contact_phone")
        var contactPhone: String? = null,

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

        @ApiModelProperty(name = "描述")
        @TableField(value = "remark")
        var remark: String? = null,


        )