package com.chris.bullseye.system.pojo

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

/**
 * @author Chris
 * @date2020 12 07 16:44
 */
@Table(name = "b_menu_function")
open class MenuFunction(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
        @ApiModelProperty(name = "id")
        var id: String? = null,

        @ApiModelProperty(name = "菜单id")
        @Column(name = "menu_id")
        var menuId: String? = null,

        @ApiModelProperty(name = "编码")
        @Column(name = "code")
        var code: String? = null,

        @ApiModelProperty(name = "名称")
        @Column(name = "name")
        var name: String? = null,

        @ApiModelProperty(name = "路径")
        @Column(name = "path")
        var path: String? = null,

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