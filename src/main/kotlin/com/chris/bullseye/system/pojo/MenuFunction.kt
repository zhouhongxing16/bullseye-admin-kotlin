package com.chris.bullseye.system.pojo

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime

/**
 * @author Chris
 * @date2020 12 07 16:44
 */
@TableName(value = "b_menu_function")
open class MenuFunction(

        @TableId(type = IdType.ASSIGN_ID)
        @ApiModelProperty(name = "id")
        var id: String? = null,

        @ApiModelProperty(name = "菜单id")
        @TableField(value = "menu_id")
        var menuId: String? = null,

        @ApiModelProperty(name = "编码")
        @TableField(value = "code")
        var code: String? = null,

        @ApiModelProperty(name = "名称")
        @TableField(value = "name")
        var name: String? = null,

        @ApiModelProperty(name = "路径")
        @TableField(value = "path")
        var path: String? = null,

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