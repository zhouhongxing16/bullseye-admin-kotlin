package com.chris.bullseye.system.pojo

import java.util.Date
import javax.persistence.*
import java.io.Serializable
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-25 19:11
 * 登录日志
 */
@Table(name = "b_login_record")
open class LoginRecord (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
        @ApiModelProperty(name = "访问ID")
        var id: String? = null,

        @ApiModelProperty(name = "登录账号")
        @Column(name = "username")
        var username: String? = null,

        @ApiModelProperty(name = "登录IP地址")
        @Column(name = "ip")
        var ip: String? = null,

        @ApiModelProperty(name = "登录地点")
        @Column(name = "login_location")
        var loginLocation: String? = null,

        @ApiModelProperty(name = "浏览器类型")
        @Column(name = "browser")
        var browser: String? = null,

        @ApiModelProperty(name = "操作系统")
        @Column(name = "os")
        var os: String? = null,

        @ApiModelProperty(name = "登录状态（1成功 0失败）")
        @Column(name = "status")
        var status: Int? = null,

        @ApiModelProperty(name = "提示消息")
        @Column(name = "message")
        var message: String? = null,

        @ApiModelProperty(name = "创建日期")
        @Column(name = "create_time")
        var createTime: LocalDateTime? = null,



        )