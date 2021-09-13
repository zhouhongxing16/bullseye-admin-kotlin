package com.chris.bullseye.pojo

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty
import java.util.*
import javax.persistence.*

/**
 * @author Chris
 * @date 2020 12 08 13:39
 */
@Table(name = "kb_role_menu")
open class RoleMenu(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
        @ApiModelProperty(name = "唯一标识")
        var id: String? = null,

        @ApiModelProperty(name = "角色外键")
        @Column(name = "role_id")
        var roleId: String? = null,

        @ApiModelProperty(name = "菜单外键")
        @Column(name = "menu_id")
        var menuId: String? = null,

        @ApiModelProperty(name = "状态")
        @Column(name = "status")
        var status: Int? = null,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        @ApiModelProperty(name = "创建日期")
        @Column(name = "created")
        var created: Date? = null
)
