package com.chris.bullseye.system.pojo

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

/**
 * @author Chris
 * @date2020 12 07 16:41
 */
@Table(name = "b_organization")
open class Organization(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
        @ApiModelProperty(name = "唯一标识")
        var id: String? = null,

        @ApiModelProperty(name = "LOGO")
        @Column(name = "logo")
        var logo: String? = null,

        @ApiModelProperty(name = "联系人")
        @Column(name = "contact_name")
        var contactName: String? = null,

        @ApiModelProperty(name = "联系电话")
        @Column(name = "contact_phone")
        var contactPhone: String? = null,

        @ApiModelProperty(name = "代码")
        @Column(name = "code")
        var code: String? = null,

        @ApiModelProperty(name = "名称")
        @Column(name = "name")
        var name: String? = null,

        @ApiModelProperty(name = "类型")
        @Column(name = "type_id")
        var typeId: String? = null,

        @ApiModelProperty(name = "描述")
        @Column(name = "brief")
        var brief: String? = null,

        @ApiModelProperty(name = "状态")
        @Column(name = "status")
        var status: Int? = null,

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