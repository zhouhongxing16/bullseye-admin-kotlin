package com.chris.bullseye.system.pojo

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-25 16:19
 * 账号
 */
@TableName(value = "b_account")
open class Account(

        @TableId(type = IdType.ASSIGN_UUID)
        @ApiModelProperty(name = "唯一标识")
        var id: String? = null,

        @ApiModelProperty(name = "姓名")
        @TableField(value = "name")
        var name: String? = null,

        @ApiModelProperty(name = "账户")
        @TableField(value = "username")
        var username: String? = null,

        @ApiModelProperty(name = "昵称")
        @TableField(value = "nick_name")
        var nickName: String? = null,

        @ApiModelProperty(name = "密码")
        @TableField(value = "password")
        var password: String? = null,

        @ApiModelProperty(name = "邮箱")
        @TableField(value = "email")
        var email: String? = null,

        @ApiModelProperty(name = "手机号码")
        @TableField(value = "mobile")
        var mobile: String? = null,

        @ApiModelProperty(name = "是否开通手机号登录")
        @TableField(value = "mobile_login_flag")
        var mobileLoginFlag: Boolean? = null,

        @ApiModelProperty(name = "是否锁定")
        @TableField(value = "account_locked")
        var accountLocked: Boolean? = null,

        @ApiModelProperty(name = "是否过期")
        @TableField(value = "account_expired")
        var accountExpired: Boolean? = null,

        @ApiModelProperty(name = "0:个人，1：机构")
        @TableField(value = "type_flag")
        var typeFlag: Int? = null,

        @ApiModelProperty(name = "账号等级标识")
        @TableField(value = "level_id")
        var levelId: String? = null,

        @ApiModelProperty(name = "人员标识")
        @TableField(value = "staff_id")
        var staffId: String? = null,

        @ApiModelProperty(name = "组织标识")
        @TableField(value = "organization_id")
        var organizationId: String? = null,

        @ApiModelProperty(name = "微信OpenId")
        @TableField(value = "wx_openid")
        var wxOpenid: String? = null,

        @ApiModelProperty(name = "支付宝OpenId")
        @TableField(value = "alipay_openid")
        var alipayOpenid: String? = null,

        @ApiModelProperty(name = "头像")
        @TableField(value = "avatar")
        var avatar: String? = null,

        @ApiModelProperty(name = "状态")
        @TableField(value = "status")
        var status: Int? = null,

        @ApiModelProperty(name = "备注")
        @TableField(value = "remark")
        var remark: String? = null,

        @ApiModelProperty(name = "创建人ID")
        @TableField(value = "creator_id")
        var creatorId: String? = null,

        @ApiModelProperty(name = "创建人")
        @TableField(value = "creator_name")
        var creatorName: String? = null,

        @ApiModelProperty(name = "修改时间")
        @TableField(value = "update_time")
        var updateTime: LocalDateTime? = null,

        @ApiModelProperty(name = "账号过期时间")
        @TableField(value = "expired_date")
        var expiredDate: LocalDateTime? = null,

        @ApiModelProperty(name = "创建日期")
        @TableField(value = "create_time")
        var createTime: LocalDateTime? = null


)