package com.chris.bullseye.system.pojo

import java.util.Date
import javax.persistence.*
import java.io.Serializable
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-25 19:30
 * 部门/科室
 */
@Table(name = "b_department")
open class Department(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
        @ApiModelProperty(name = "唯一标识")
        var id: String? = null,

        @ApiModelProperty(name = "组织标识")
        @Column(name = "organization_id")
        var organizationId: String? = null,

        @ApiModelProperty(name = "代码")
        @Column(name = "code")
        var code: String? = null,

        @ApiModelProperty(name = "父级ID")
        @Column(name = "parent_id")
        var parentId: String? = null,

        @ApiModelProperty(name = "名称")
        @Column(name = "name")
        var name: String? = null,

        @ApiModelProperty(name = "类型")
        @Column(name = "type_id")
        var typeId: String? = null,

        @ApiModelProperty(name = "联系人")
        @Column(name = "contact_name")
        var contactName: String? = null,

        @ApiModelProperty(name = "联系电话")
        @Column(name = "contact_phone")
        var contactPhone: String? = null,

        @ApiModelProperty(name = "创建人")
        @Column(name = "user_id")
        var userId: String? = null,

        @ApiModelProperty(name = "状态")
        @Column(name = "status")
        var status: Int? = null,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        @ApiModelProperty(name = "创建日期")
        @Column(name = "created")
        var created: Date? = null,

        @ApiModelProperty(name = "描述")
        @Column(name = "remark")
        var remark: String? = null,


        )