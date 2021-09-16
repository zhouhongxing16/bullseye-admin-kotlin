package com.chris.bullseye.system.pojo

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

/**
 * @author Chris
 * @date 2020 12 08 13:41
 */
@Table(name = "b_role_menu_function")
open class RoleMenuFunction(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
        @ApiModelProperty(name = "唯一标识")
        var id: String? = null,

        @ApiModelProperty(name = "角色ID")
        @Column(name = "role_id")
        var roleId: String? = null,

        @ApiModelProperty(name = "授权功能ID")
        @Column(name = "menu_function_id")
        var menuFunctionId: String? = null,

        @ApiModelProperty(name = "状态")
        @Column(name = "status")
        var status: String? = null,


        @ApiModelProperty(name = "创建人ID")
        @Column(name = "creator_id")
        var creatorId: String? = null,

        @ApiModelProperty(name = "创建人")
        @Column(name = "creator_name")
        var creatorName: String? = null,

        @ApiModelProperty(name = "创建日期")
        @Column(name = "create_time")
        var createTime: LocalDateTime? = null,
)
