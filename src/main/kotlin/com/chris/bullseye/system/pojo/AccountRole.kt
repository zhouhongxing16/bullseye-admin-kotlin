package com.chris.bullseye.system.pojo

import javax.persistence.*
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime
import java.util.*

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-25 16:22
 * 账号角色
 */
@Table(name = "b_account_role")
open class AccountRole(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
        @ApiModelProperty(name = "唯一标识")
        var id: String? = null,

        @ApiModelProperty(name = "角色外键")
        @Column(name = "role_id")
        var roleId: String? = null,

        @ApiModelProperty(name = "用户外键")
        @Column(name = "account_id")
        var accountId: String? = null,

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
        var create_time: LocalDateTime? = null


)