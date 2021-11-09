package com.chris.bullseye.system.pojo

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-25 16:25
 * 字典数据
 */
@TableName(value = "b_dictionary_type_data")
open class DictionaryTypeData(
        @TableId(type = IdType.ASSIGN_UUID)
        @ApiModelProperty(name = "唯一标识")
        var id: String? = null,

        @ApiModelProperty(name = "类型标识")
        @TableField(value = "type_id")
        var typeId: String? = null,

        @ApiModelProperty(name = "类型名称")
        @TableField(value = "type_name")
        var typeName: String? = null,

        @ApiModelProperty(name = "字典编码")
        @TableField(value = "code")
        var code: String? = null,

        @ApiModelProperty(name = "字典名称")
        @TableField(value = "name")
        var name: String? = null,

        @ApiModelProperty(name = "字典备注")
        @TableField(value = "remark")
        var remark: String? = null,

        @ApiModelProperty(name = "排序")
        @TableField(value = "sort")
        var sort: Int? = null,

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