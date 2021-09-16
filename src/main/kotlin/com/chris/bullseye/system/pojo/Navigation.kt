package com.chris.bullseye.system.pojo

import java.util.Date
import javax.persistence.*
import java.io.Serializable
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-28 11:25
 * 导航栏
 */
@Table(name = "b_navigation")
open class Navigation(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
        @ApiModelProperty(name = "唯一标识")
        var id: String? = null,

        @ApiModelProperty(name = "名称")
        @Column(name = "name")
        var name: String? = null,

        @ApiModelProperty(name = "级别")
        @Column(name = "level")
        var level: Int? = null,

        @ApiModelProperty(name = "父标识")
        @Column(name = "parent_id")
        var parentId: String? = null,

        @ApiModelProperty(name = "显示顺序")
        @Column(name = "sort")
        var sort: Int? = null,

        @ApiModelProperty(name = "访问路径")
        @Column(name = "path")
        var path: String? = null,

        @ApiModelProperty(name = "状态")
        @Column(name = "status")
        var status: Int? = null,

        @ApiModelProperty(name = "创建日期")
        @Column(name = "created")
        var created: LocalDateTime? = null,

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