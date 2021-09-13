package com.chris.bullseye.system.pojo

import javax.persistence.*
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty
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

        @ApiModelProperty(name = "创建人")
        @Column(name = "user_id")
        var userId: String? = null,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        @ApiModelProperty(name = "创建日期")
        @Column(name = "created")
        var created: Date? = null


)