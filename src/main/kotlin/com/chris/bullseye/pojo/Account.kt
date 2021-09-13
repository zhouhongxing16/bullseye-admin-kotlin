package com.chris.bullseye.pojo

import java.util.Date
import javax.persistence.*
import java.math.BigDecimal
import java.io.Serializable
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.core.JsonProcessingException
import io.swagger.annotations.ApiModelProperty

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-25 16:19
 * 账号
 */
@Table(name = "kb_accounts")
open class Account(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
        @ApiModelProperty(name = "唯一标识")
        var id: String? = null,

        @ApiModelProperty(name = "账户")
        @Column(name = "username")
        var username: String? = null,

        @ApiModelProperty(name = "昵称")
        @Column(name = "nick_name")
        var nickName: String? = null,

        @ApiModelProperty(name = "密码")
        @Column(name = "password")
        var password: String? = null,

        @ApiModelProperty(name = "邮箱")
        @Column(name = "email")
        var email: String? = null,

        @ApiModelProperty(name = "手机号码")
        @Column(name = "mobile")
        var mobile: String? = null,

        @ApiModelProperty(name = "是否开通手机号登录")
        @Column(name = "mobile_login_flag")
        var mobileLoginFlag: Boolean? = null,

        @ApiModelProperty(name = "是否锁定")
        @Column(name = "account_locked")
        var accountLocked: Boolean? = null,

        @ApiModelProperty(name = "是否过期")
        @Column(name = "account_expired")
        var accountExpired: Boolean? = null,

        @ApiModelProperty(name = "0:个人，1：机构")
        @Column(name = "type_flag")
        var typeFlag: Int? = null,

        @ApiModelProperty(name = "账号等级标识")
        @Column(name = "level_id")
        var levelId: String? = null,

        @ApiModelProperty(name = "人员标识")
        @Column(name = "staff_id")
        var staffId: String? = null,

        @ApiModelProperty(name = "组织标识")
        @Column(name = "organization_id")
        var organizationId: String? = null,

        @ApiModelProperty(name = "微信OpenId")
        @Column(name = "wx_openid")
        var wxOpenid: String? = null,

        @ApiModelProperty(name = "支付宝OpenId")
        @Column(name = "alipay_openid")
        var alipayOpenid: String? = null,

        @ApiModelProperty(name = "头像")
        @Column(name = "avatar")
        var avatar: String? = null,

        @ApiModelProperty(name = "状态")
        @Column(name = "status")
        var status: Int? = null,

        @ApiModelProperty(name = "备注")
        @Column(name = "remark")
        var remark: String? = null,

        @ApiModelProperty(name = "创建人")
        @Column(name = "user_id")
        var userId: String? = null,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        @ApiModelProperty(name = "修改时间")
        @Column(name = "modified")
        var modified: Date? = null,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        @ApiModelProperty(name = "账号过期时间")
        @Column(name = "expired_date")
        var expiredDate: Date? = null,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        @ApiModelProperty(name = "创建时间")
        @Column(name = "created")
        var created: Date? = null


)