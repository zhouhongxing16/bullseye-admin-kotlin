package com.chris.bullseye.system.pojo

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-25 19:11
 * 登录日志
 */
@TableName(value = "b_login_record")
open class LoginRecord (

        @TableId(type = IdType.ASSIGN_UUID)
        @ApiModelProperty(name = "访问ID")
        var id: String? = null,

        @ApiModelProperty(name = "登录账号")
        @TableField(value = "username")
        var username: String? = null,

        @ApiModelProperty(name = "登录IP地址")
        @TableField(value = "ip")
        var ip: String? = null,

        @ApiModelProperty(name = "登录地点")
        @TableField(value = "login_location")
        var loginLocation: String? = null,

        @ApiModelProperty(name = "浏览器类型")
        @TableField(value = "browser")
        var browser: String? = null,

        @ApiModelProperty(name = "操作系统")
        @TableField(value = "os")
        var os: String? = null,

        @ApiModelProperty(name = "登录状态（1成功 0失败）")
        @TableField(value = "status")
        var status: Int? = null,

        @ApiModelProperty(name = "提示消息")
        @TableField(value = "message")
        var message: String? = null,

        @ApiModelProperty(name = "创建日期")
        @TableField(value = "create_time")
        var createTime: LocalDateTime? = null,



        )